/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.sessions;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.avcnu.plagiarism.entities.Activationcodebatches;
import org.avcnu.plagiarism.entities.Activationcodes;
import org.avcnu.plagiarism.entities.Apiauthentication;
import org.avcnu.plagiarism.entities.Apicalls;
import org.avcnu.plagiarism.entities.Document;
import org.avcnu.plagiarism.entities.Institutions;
import org.avcnu.plagiarism.entities.Institutiontype;
import org.avcnu.plagiarism.entities.Membership;
import org.avcnu.plagiarism.entities.Paymentreference;
import org.avcnu.plagiarism.util.GoogleSearchData;
import org.avcnu.plagiarism.util.SimilarDocuments;
import org.avcnu.plagiarism.util.SimilarSentences;
import org.avcnu.plagiarism.util.SimilarityResult;
import org.avcnu.plagiarism.util.Utilities;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.languagetool.JLanguageTool;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;
import org.xml.sax.SAXException;

/**
 *
 * @author eaglescan
 */
@Stateless
public class MainSession implements MainSessionLocal {
    
    Utilities util = new Utilities();
    
    @PersistenceContext(unitName = "EagleScanPU")
    private EntityManager em;
    
    private final int SIMILARITY_DEFAULT_PERCENTAGE = 25;
    private final int GOOGLE_SEARCH_DEPT = 1;
    private final String SENTENCE_SEPARATOR = "(?<=[.!?])\\s*";
    private final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    //private final int[] SIMILARITY_PERCENTAGE_RANGE = new int[]{25, 65};
    //private final org.languagetool.Language DEFAULT_LANGUAGE = new BritishEnglish();

    private final String server = "154.68.225.25";
    private final int port = 21;
    private final String user = "root";
    private final String pass = "p@ssw0rd";

    ////Facade methods
    public void persist(Object object) {
        em.persist(object);
    }
    
    public void remove(Object object) {
        try {
            em.remove(em.contains(object) ? object : em.merge(object));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
        }
    }
    
    public void merge(Object object) {
        try {
            em.merge(object);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
        }
    }
    
    @Override
    public void newEntry(Object obj) {
        try {
            persist(obj);
        } catch (Exception j) {
        }
    }
    
    @Override
    public void deleteObject(Object obj) {
        try {
            remove(obj);
        } catch (Exception j) {
        }
    }
    
    @Override
    public void updateRecord(Object obj) {
        try {
            merge(obj);
        } catch (Exception ja) {
        }
    }
    
    @Override
    public Object getSingleObject(Class<?> ent, Object pk) {
        Object obj = null;
        try {
            obj = em.find(ent, pk);
        } catch (Exception ja) {
        }
        return obj;
    }
    ///facade methods ends here

    @Override
    public List<GoogleSearchData> googleSearch(String sentence) {
        List<GoogleSearchData> response = new ArrayList();
        try {
            String searchURL = GOOGLE_SEARCH_URL + "?q=" + sentence + "&num=" + GOOGLE_SEARCH_DEPT;
            org.jsoup.nodes.Document doc = Jsoup.connect(searchURL).userAgent("Jsoup client").get();
            Elements results = doc.select("a[href]");
            results.forEach(result -> {
                String attr2 = result.attr("class");
                String url = result.attr("href");
                if (!attr2.startsWith("_Zkb") && url.startsWith("/url?q=")) {
                    url = util.getDomainName(url);
                    String title = result.text();
                    response.add(new GoogleSearchData(url, title));
                }
            });
        } catch (IOException js) {
        }
        return response;
    }
    
    @Override
    public String extractText(InputStream stream) throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try {
            parser.parse(stream, handler, metadata);
        } catch (IOException | TikaException | SAXException j) {
        }
        String text = handler.toString();
        text = text.trim();
        if (text.length() > 0) {
            try {
                text = text.replaceAll("\t\t\t", " ");
                text = text.replaceAll("\t\t", " ");
                text = text.replaceAll("\t", " ");
                text = text.replaceAll("\n\n\n", " ");
                text = text.replaceAll("\n\n", " ");
                text = text.replaceAll("\n", " ");
                text = text.replaceAll("    ", " ");
                text = text.replaceAll("   ", " ");
                text = text.replaceAll("  ", " ");
            } catch (Exception j) {
            }
        }
        return text;
    }
    
    @Override
    public String identifyLanguage(String text) {
        LanguageIdentifier identifier = new LanguageIdentifier(text);
        return identifier.getLanguage();
    }
    
    @Override
    public SimilarityResult checkSimilarity(String document, List<String> potentialSimilarDocuments, double percentage) {
        SimilarityResult result;
        double max = 0;
        double av = 0;
        double sum = 0;
        List<SimilarDocuments> list = getSimilarDocumentsBrief(document, potentialSimilarDocuments, percentage);
        for (SimilarDocuments doc : list) {
            sum += doc.getSimilarityPercentage();
            max = Math.max(max, doc.getSimilarityPercentage());
        }
        
        try {
            av = sum / list.size();
        } catch (Exception j) {
        }
        result = new SimilarityResult(av, max, list);
        return result;
    }
    
    @Override
    public List<SimilarDocuments> getSimilarDocumentsBrief(String document, List<String> potentialdocuments, double percentage) {
        List<SimilarDocuments> documents = new ArrayList();
        try {
            potentialdocuments.forEach(similarDocument -> {
                try {
                    Map<String, Integer> documentTokensCount = getTokensCount(document);
                    double similar = getSimilarity(documentTokensCount, similarDocument);
                    if (similar >= percentage) {
                        SimilarDocuments simdoc = new SimilarDocuments(similar, similar, similarDocument, null);
                        documents.add(simdoc);
                    }
                } catch (Exception j) {
                }
            });
        } catch (Exception j) {
        }
        return documents;
    }
    
    @Override
    public List<SimilarDocuments> getSimilarDocuments(String document, List<String> potentialdocuments, double percentage) {
        List<SimilarDocuments> documents = new ArrayList();
        try {
            potentialdocuments.forEach(similarDocument -> {
                try {
                    Map<String, Integer> documentTokensCount = getTokensCount(document);
                    double similar = getSimilarity(documentTokensCount, similarDocument);
                    if (similar >= percentage) {
                        StringTokenizer sentences = new StringTokenizer(document, SENTENCE_SEPARATOR);
                        while (sentences.hasMoreElements()) {
                            String sen1 = sentences.nextToken().trim();
                            List<SimilarSentences> similarSentences = getSimilarSentences(sen1, similarDocument, percentage);
                            if (similarSentences.size() > 0) {
                                double per = 0;
                                for (SimilarSentences simsen : similarSentences) {
                                    per = Math.max(per, simsen.getSimilarityPercentage());
                                }
                                SimilarDocuments simdoc = new SimilarDocuments(similar, per, similarDocument, similarSentences);
                                documents.add(simdoc);
                            }
                        }
                    }
                } catch (Exception j) {
                }
            });
        } catch (Exception j) {
        }
        return documents;
    }
    
    @Override
    public List<SimilarSentences> getSimilarSentences(String sentence, String document, double percentage) {
        List<SimilarSentences> sentences = new ArrayList();
        try {
            Map<String, Integer> documentTokensCount = getTokensCount(sentence);
            StringTokenizer t1 = new StringTokenizer(document, SENTENCE_SEPARATOR);
            while (t1.hasMoreElements()) {
                String sen1 = t1.nextToken().trim();
                double similar = getSimilarity(documentTokensCount, sen1);
                if (similar >= percentage) {
                    SimilarSentences docs = new SimilarSentences(similar, sen1);
                    sentences.add(docs);
                }
            }
        } catch (Exception j) {
        }
        return sentences;
    }
    
    @Override
    public SimilarDocuments compareDocuments(String originalDocument, String suspiciousDocument, double percentage) {
        SimilarDocuments similarity = new SimilarDocuments();
        try {
            Map<String, Integer> documentTokensCount = getTokensCount(originalDocument);
            double similar = getSimilarity(documentTokensCount, suspiciousDocument);
            if (similar >= percentage) {
                similarity.setSimilarityPercentage(similar);
                StringTokenizer t1 = new StringTokenizer(originalDocument, SENTENCE_SEPARATOR);
                List<SimilarSentences> sens = new ArrayList();
                while (t1.hasMoreElements()) {
                    String sen1 = t1.nextToken().trim();
                    if (sen1.split(" ").length > 3) {
                        Map<String, Integer> documentTokensCount2 = getTokensCount(sen1);
                        StringTokenizer t2 = new StringTokenizer(suspiciousDocument, SENTENCE_SEPARATOR);
                        while (t2.hasMoreElements()) {
                            String sen2 = t2.nextToken().trim();
                            if (sen2.split(" ").length > 3) {
                                double similarx = getSimilarity(documentTokensCount2, sen2);
                                if (similarx >= percentage) {
                                    SimilarSentences docs = new SimilarSentences(similarx, sen1);
                                    sens.add(docs);
                                    break;
                                }
                            }
                        }
                    }
                    
                }
                similarity.setSimilarSentences(sens);
                similarity.setDocument("");
                similarity.setMaxPercentageSimilarity(similar);
            }
        } catch (Exception j) {
        }
        return similarity;
    }
    
    private double getSimilarity(Map<String, Integer> tokensCountText1, String text2) {
        Map<String, Integer> tokensCountText2 = getTokensCount(text2);
        if (tokensCountText2.isEmpty()) {
            return 0;
        }
        Set<String> vocabulary = new HashSet<>();
        vocabulary.addAll(tokensCountText1.keySet());
        vocabulary.addAll(tokensCountText2.keySet());
        
        double commonsWordsNumber = 0;
        double termsText1Number = 0;
        double termsText2Number = 0;
        
        for (String word : vocabulary) {
            int wordCount1 = tokensCountText1.get(word) != null ? tokensCountText1.get(word) : 0;
            int wordCount2 = tokensCountText2.get(word) != null ? tokensCountText2.get(word) : 0;
            commonsWordsNumber += Math.min(wordCount1, wordCount2);
            termsText1Number += wordCount1;
            termsText2Number += wordCount2;
        }
        double ratio = (2 * commonsWordsNumber) / (termsText1Number + termsText2Number);
        double percent = Round((ratio * 100), 2);
        return percent;
    }
    
    private double Round(double number, int decimalPlaces) {
        double modifier = Math.pow(10.0, decimalPlaces);
        return Math.round(number * modifier) / modifier;
    }
    
    private Map<String, Integer> getTokensCount(String text) {
        Map<String, Integer> tokensCount = new HashMap<>();
        List<String> tokens = tokenizeString(text);
        tokens.forEach(token -> {
            if (!tokensCount.containsKey(token)) {
                tokensCount.put(token, 1);
            } else {
                tokensCount.put(token, tokensCount.get(token) + 1);
            }
        });
        return tokensCount;
    }
    
    private List<String> tokenizeString(String string) {
        List<String> result = new ArrayList<>();
        try (Analyzer analizer = new StandardAnalyzer()) {
            TokenStream stream = analizer.tokenStream(null, new StringReader(string));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (Exception s) {
        }
        return result;
    }
    
    @Override
    public SimilarityResult checkLocalRepository(String suspicious, double percentageSimilarity) {
        SimilarityResult result = new SimilarityResult();
        result.setMaxSimilarity(0);
        result.setSimilarTexts(null);
        result.setSimilarityPercantage(0);
        
        return result;
    }
    
    @Override
    public List<RuleMatch> checkGrammer(String document, String omittedwords, String omittedphrases, org.languagetool.Language lan) {
        //words are 'Space delimited'
        //phrases are '::' delimited
        List<RuleMatch> matches = new ArrayList();
        try {
            JLanguageTool langTool = new JLanguageTool(lan);
            if (omittedwords != null) {
                String[] wordsplit = omittedwords.split(" ");
                langTool.getAllActiveRules().stream().filter(rule -> (rule instanceof SpellingCheckRule)).forEachOrdered(rule -> {
                    List<String> wordsToIgnore = Arrays.asList(wordsplit);
                    ((SpellingCheckRule) rule).addIgnoreTokens(wordsToIgnore);
                });
            }
            if (omittedphrases != null) {
                String[] phrases = omittedphrases.split("::");
                langTool.getAllActiveRules().stream().filter(rule -> (rule instanceof SpellingCheckRule)).forEachOrdered(rule -> {
                    ((SpellingCheckRule) rule).acceptPhrases(Arrays.asList(phrases));
                });
            }
            
            matches = langTool.check(document);
            
        } catch (IOException ja) {
        }
        return matches;
    }
    
    @Override
    public boolean authenticateAPICall(String apikey, String ipaddress, String service) {
        boolean validate = false;
        String todaydate = util.getTodaysdate();
        try {
            Apiauthentication auth = (Apiauthentication) this.getSingleObject(Apiauthentication.class, apikey);
            if (auth != null) {
                if (auth.getAllowedips().contains(ipaddress) && auth.getServices().contains(service) && (auth.getExpirydate().compareTo(todaydate) >= 0 || auth.getExpirydate().equalsIgnoreCase("-1")) && auth.getExpirystatus().equals("ACTIVE") && (auth.getMaxapicalls() >= auth.getNoofapicalls() || auth.getMaxapicalls() == -1)) {
                    try {
                        if (auth.getMaxapicalls() != -1) {
                            auth.setNoofapicalls(auth.getNoofapicalls() + 1);
                            this.updateRecord(auth);
                        }
                        Apicalls calls = new Apicalls(apikey + util.getPincodes(6), apikey, service, util.getTodaysdate(), util.getCurrentTime(), ipaddress);
                        this.newEntry(calls);
                    } catch (Exception j) {
                    }
                    validate = true;
                }
            }
        } catch (Exception j) {
        }
        return validate;
    }
    
    @Override
    public List<Apiauthentication> getApiauthenticationByAllowedIP(String ip) {
        List<Apiauthentication> list = new ArrayList();
        ip = "%" + ip + "%";
        try {
            list = (List<Apiauthentication>) em.createQuery("SELECT i FROM Apiauthentication i WHERE i.allowedips LIKE :ip ORDER BY i.dateadded ASC")
                    .setParameter("ip", ip).getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apiauthentication> getApiauthenticationByContactemail(String email) {
        List<Apiauthentication> list = new ArrayList();
        try {
            list = (List<Apiauthentication>) em.createQuery("SELECT i FROM Apiauthentication i WHERE i.contactemail = :email ORDER BY i.dateadded ASC")
                    .setParameter("email", email).getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apiauthentication> getApiauthenticationByDateaddedrange(String datefrom, String dateto) {
        List<Apiauthentication> list = new ArrayList();
        try {
            list = (List<Apiauthentication>) em.createQuery("SELECT a FROM Apiauthentication a WHERE a.dateadded >= :datefrom AND a.dateadded <= :dateto ORDER BY a.dateadded DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apiauthentication> getApiauthenticationByExpiryStatus(String status) {
        List<Apiauthentication> list = new ArrayList();
        try {
            if (status.equalsIgnoreCase("ALL")) {
                list = (List<Apiauthentication>) em.createQuery("SELECT i FROM Apiauthentication i ORDER BY i.expirystatus ASC, i.dateadded DESC").getResultList();
            } else {
                list = (List<Apiauthentication>) em.createQuery("SELECT i FROM Apiauthentication i WHERE i.expirystatus = :status ORDER BY i.dateadded DESC")
                        .setParameter("status", status).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apicalls> getApicallsByApikey(String apikey) {
        List<Apicalls> list = new ArrayList();
        try {
            list = (List<Apicalls>) em.createQuery("SELECT i FROM Apicalls i WHERE i.apikey = :apikey ORDER BY i.datecalled DESC, i.timecalled DESC")
                    .setParameter("apikey", apikey).getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apicalls> getApicallsByServicecalled(String service) {
        List<Apicalls> list = new ArrayList();
        try {
            list = (List<Apicalls>) em.createQuery("SELECT i FROM Apicalls i WHERE i.servicecalled = :service ORDER BY i.datecalled DESC, i.timecalled DESC")
                    .setParameter("service", service).getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apicalls> getApicallsByIPaddress(String ip) {
        List<Apicalls> list = new ArrayList();
        try {
            list = (List<Apicalls>) em.createQuery("SELECT i FROM Apicalls i WHERE i.ipaddresscall = :ip ORDER BY i.datecalled DESC, i.timecalled DESC")
                    .setParameter("ip", ip).getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apicalls> getApicallsByDatecalledrange(String datefrom, String dateto) {
        List<Apicalls> list = new ArrayList();
        try {
            list = (List<Apicalls>) em.createQuery("SELECT a FROM Apicalls a WHERE a.datecalled >= :datefrom AND a.datecalled <= :dateto ORDER BY a.apikey ASC, a.timecalled DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Apicalls> getApicallsByApikeyAndDatecalledrange(String apikey, String datefrom, String dateto) {
        List<Apicalls> list = new ArrayList();
        try {
            list = (List<Apicalls>) em.createQuery("SELECT a FROM Apicalls a WHERE a.apikey = :apikey AND a.datecalled >= :datefrom AND a.datecalled <= :dateto ORDER BY a.apikey ASC, a.timecalled DESC")
                    .setParameter("apikey", apikey).setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<String> getApicallsAllIpaddresses() {
        List<String> list = new ArrayList();
        try {
            list = (List<String>) em.createQuery("SELECT DISTINCT a.ipaddresscall FROM Apicalls a ORDER BY a.ipaddresscall ASC")
                    .getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Institutiontype> getAllInstitutiontypes() {
        List<Institutiontype> list = new ArrayList();
        try {
            list = (List<Institutiontype>) em.createQuery("SELECT i FROM Institutiontype i ORDER BY i.typename ASC").getResultList();
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Institutions> getInstitutionsByType(String type) {
        List<Institutions> list = new ArrayList();
        try {
            if (type.equalsIgnoreCase("ALL")) {
                list = (List<Institutions>) em.createQuery("SELECT i FROM Institutions i ORDER BY i.institutionname ASC").getResultList();
            } else {
                list = (List<Institutions>) em.createQuery("SELECT i FROM Institutions i WHERE i.institutiontype = :itype ORDER BY i.institutionname ASC")
                        .setParameter("itype", type).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Institutions> getInstitutionsByDateregisteredrange(String datefrom, String dateto) {
        List<Institutions> list = new ArrayList();
        try {
            list = (List<Institutions>) em.createQuery("SELECT a FROM Institutions a WHERE a.dateadded >= :datefrom AND a.dateadded <= :dateto ORDER BY a.institutionname ASC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Activationcodebatches> getActivationcodebatchesByInstitution(String institutionid) {
        List<Activationcodebatches> list = new ArrayList();
        try {
            if (institutionid.equalsIgnoreCase("ALL")) {
                list = (List<Activationcodebatches>) em.createQuery("SELECT a FROM Activationcodebatches a ORDER BY a.dategenerated DESC").getResultList();
            } else {
                list = (List<Activationcodebatches>) em.createQuery("SELECT a FROM Activationcodebatches a WHERE a.institutionid = :institutionid ORDER BY a.dategenerated DESC")
                        .setParameter("institutionid", institutionid).getResultList();
            }
            
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Activationcodebatches> getActivationcodebatchesByDaterange(String datefrom, String dateto) {
        List<Activationcodebatches> list = new ArrayList();
        try {
            list = (List<Activationcodebatches>) em.createQuery("SELECT a FROM Activationcodebatches a WHERE a.dategenerated >= :datefrom AND a.dategenerated <= :dateto ORDER BY a.dategenerated DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Activationcodes> getActivationcodesByDategeneratedrange(String datefrom, String dateto) {
        List<Activationcodes> list = new ArrayList();
        try {
            list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.dategenerated >= :datefrom AND a.dategenerated <= :dateto ORDER BY a.dategenerated DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Activationcodes> getActivationcodesByBatchAndStatus(String batchid, String activationstatus) {
        List<Activationcodes> list = new ArrayList();
        try {
            if (activationstatus.equalsIgnoreCase("ALL")) {
                if (batchid.equalsIgnoreCase("ALL")) {
                    list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a ORDER BY a.activationstatus ASC, a.dategenerated DESC").getResultList();
                } else {
                    list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.batchid = :batchid ORDER BY a.activationstatus ASC, a.dategenerated DESC")
                            .setParameter("batchid", batchid).getResultList();
                }
            } else {
                if (batchid.equalsIgnoreCase("ALL")) {
                    list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.activationstatus = :activationstatus ORDER BY a.dategenerated DESC")
                            .setParameter("activationstatus", activationstatus).getResultList();
                } else {
                    list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.batchid = :batchid AND a.activationstatus = :activationstatus ORDER BY a.dategenerated DESC")
                            .setParameter("batchid", batchid).setParameter("activationstatus", activationstatus).getResultList();
                }
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Activationcodes> getActivationcodesByUser(String username, String activationstatus) {
        List<Activationcodes> list = new ArrayList();
        try {
            if (activationstatus.equalsIgnoreCase("ALL")) {
                list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.username = :username ORDER BY a.activationstatus ASC, a.dategenerated DESC")
                        .setParameter("username", username).getResultList();
            } else {
                list = (List<Activationcodes>) em.createQuery("SELECT a FROM Activationcodes a WHERE a.username = :username AND a.activationstatus = :activationstatus ORDER BY a.activationstatus ASC, a.dategenerated DESC")
                        .setParameter("activationstatus", activationstatus).setParameter("username", username).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByInstitution(String institutionid, String membershipttatus) {
        List<Membership> list = new ArrayList();
        try {
            if (institutionid.equalsIgnoreCase("ALL")) {
                if (membershipttatus.equalsIgnoreCase("ALL")) {
                    list = (List<Membership>) em.createQuery("SELECT m FROM Membership m ORDER BY m.surname ASC, m.othernames ASC").getResultList();
                } else {
                    list = (List<Membership>) em.createQuery("SELECT m FROM Membership m WHERE m.membershipstatus = :status ORDER BY m.surname ASC, m.othernames ASC")
                            .setParameter("status", membershipttatus).getResultList();
                }
            } else {
                if (membershipttatus.equalsIgnoreCase("ALL")) {
                    list = (List<Membership>) em.createQuery("SELECT m FROM Membership m WHERE m.institutionid = :institutionid ORDER BY m.surname ASC, m.othernames ASC")
                            .setParameter("institutionid", institutionid).getResultList();
                } else {
                    list = (List<Membership>) em.createQuery("SELECT m FROM Membership m WHERE m.institutionid = :institutionid AND m.membershipstatus = :status ORDER BY m.surname ASC, m.othernames ASC")
                            .setParameter("status", membershipttatus).setParameter("institutionid", institutionid).getResultList();
                }
                
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByInstitutionid(String instid) {
        List<Membership> list = new ArrayList();
        try {
            if (instid.equalsIgnoreCase("ALL")) {
                list = (List<Membership>) em.createQuery("SELECT i FROM Membership i ORDER BY i.institutionid ASC, i.surname ASC, i.othernames ASC").getResultList();
            } else {
                list = (List<Membership>) em.createQuery("SELECT i FROM Membership i WHERE i.institutionid = :instid ORDER BY i.surname ASC, i.othernames ASC")
                        .setParameter("instid", instid).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByMembershipright(String right) {
        right = "%" + right + "%";
        List<Membership> list = new ArrayList();
        try {
            list = (List<Membership>) em.createQuery("SELECT i FROM Membership i WHERE i.memershiprights LIKE :right ORDER BY i.surname ASC, i.othernames ASC")
                    .setParameter("right", right).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByStatus(String status) {
        List<Membership> list = new ArrayList();
        try {
            if (status.equalsIgnoreCase("ALL")) {
                list = (List<Membership>) em.createQuery("SELECT i FROM Membership i ORDER BY i.institutionid ASC, i.surname ASC, i.othernames ASC").getResultList();
            } else {
                list = (List<Membership>) em.createQuery("SELECT i FROM Membership i WHERE i.membershipstatus = :status ORDER BY i.institutionid ASC, i.surname ASC, i.othernames ASC")
                        .setParameter("status", status).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByStatusAndInstitutionid(String status, String institutionid) {
        List<Membership> list = new ArrayList();
        try {
            list = (List<Membership>) em.createQuery("SELECT i FROM Membership i WHERE i.membershipstatus = :status AND i.institutionid = :institutionid ORDER BY i.surname ASC, i.othernames ASC")
                    .setParameter("status", status).setParameter("institutionid", institutionid).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByRightAndInstitutionid(String right, String institutionid) {
        right = "%" + right + "%";
        List<Membership> list = new ArrayList();
        try {
            list = (List<Membership>) em.createQuery("SELECT i FROM Membership i WHERE i.memershiprights LIKE :right AND i.institutionid = :institutionid ORDER BY i.surname ASC, i.othernames ASC")
                    .setParameter("right", right).setParameter("institutionid", institutionid).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Membership> getMembershipByDateregisteredrange(String datefrom, String dateto) {
        List<Membership> list = new ArrayList();
        try {
            list = (List<Membership>) em.createQuery("SELECT a FROM Membership a WHERE a.dateregistered >= :datefrom AND a.dateregistered <= :dateto ORDER BY a.institutionid ASC, a.dateregistered DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByUsername(String username) {
        List<Document> list = new ArrayList();
        try {
            if (username.equalsIgnoreCase("ALL")) {
                list = (List<Document>) em.createQuery("SELECT d FROM Document d ORDER BY d.title ASC, d.publishedYear DESC").getResultList();
            } else {
                list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.userId = :username ORDER BY d.title ASC, d.publishedYear DESC")
                        .setParameter("username", username).getResultList();
            }
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByPostdaterange(String datefrom, String dateto) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.postDate >= :datefrom AND d.postDate <= :dateto ORDER BY d.title ASC, d.publishedYear DESC")
                    .setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByUsernameAndPostdaterange(String username, String datefrom, String dateto) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.userId = :username AND d.postDate >= :datefrom AND d.postDate <= :dateto ORDER BY d.title ASC, d.publishedYear DESC")
                    .setParameter("username", username).setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByInstitutionid(String instid) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.instId = :instid ORDER BY d.title ASC, d.publishedYear DESC")
                    .setParameter("instid", instid).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByInstitutionidAndPostdaterange(String institutionid, String datefrom, String dateto) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.instId = :institutionid AND d.postDate >= :datefrom AND d.postDate <= :dateto ORDER BY d.title ASC, d.publishedYear DESC")
                    .setParameter("institutionid", institutionid).setParameter("datefrom", datefrom).setParameter("dateto", dateto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByTitle(String title) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.title = :title ORDER BY d.publishedYear DESC")
                    .setParameter("title", title).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByAuthor(String author) {
        author = "%" + author + "%";
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.author LIKE :author ORDER BY d.title ASC, d.publishedYear DESC")
                    .setParameter("author", author).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<String> getDocumentAllTitles() {
        List<String> list = new ArrayList();
        try {
            list = (List<String>) em.createQuery("SELECT DISTINCT d.title FROM Document d ORDER BY d.title ASC")
                    .getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<String> getDocumentAllAuthors() {
        List<String> list = new ArrayList();
        try {
            list = (List<String>) em.createQuery("SELECT DISTINCT d.author FROM Document d ORDER BY d.author ASC")
                    .getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public List<Document> getDocumentByPublisheddaterange(String yearfrom, String yearto) {
        List<Document> list = new ArrayList();
        try {
            list = (List<Document>) em.createQuery("SELECT d FROM Document d WHERE d.publishedYear >= :datefrom AND d.publishedYear <= :dateto ORDER BY d.publishedYear DESC, d.title ASC")
                    .setParameter("datefrom", yearfrom).setParameter("dateto", yearto).getResultList();
        } catch (Exception j) {
        }
        return list;
    }
    
    @Override
    public Activationcodes generateActivationCode(String batchcode, String userid) {
        Activationcodes act = null;
        try {
            Membership mem = (Membership) this.getSingleObject(Membership.class, userid);
            Activationcodebatches batch = (Activationcodebatches) this.getSingleObject(Activationcodebatches.class, batchcode);
            if (mem != null && batch != null) {
                if (mem.getInstitutionid().equalsIgnoreCase(batch.getInstitutionid()) || batch.getInstitutionid().equalsIgnoreCase("000")) {
                    act = new Activationcodes(batchcode + util.getPincodes(6), batchcode, "", userid, util.getTodaysdate(), batch.getAmount(),
                            batch.getDurationdays(), "INACTIVE");
                    this.newEntry(act);
                }
            }
        } catch (Exception j) {
        }
        return act;
    }
    
    @Override
    public Paymentreference generatePaymentReference(String activationcode) {
        Paymentreference pr = null;
        try {
            Activationcodes act = (Activationcodes) this.getSingleObject(Activationcodes.class, activationcode);
            if (act != null) {
                if (act.getActivationstatus().equalsIgnoreCase("INACTIVE") && !act.getUsername().isEmpty()) {
                    Membership mem = (Membership) this.getSingleObject(Membership.class, act.getUsername());
                    Activationcodebatches batch = (Activationcodebatches) this.getSingleObject(Activationcodebatches.class, act.getBatchid());
                    if (mem != null && batch != null) {
                        String year = util.getTodaysdate().split("-")[0];
                        pr = new Paymentreference(year + activationcode, "0", "", batch.getBatchname(), "", act.getAmount(),
                                batch.getBatchname(), act.getUsername(), activationcode, util.getTodaysdate(), "Not Paid", 0d, "0",
                                mem.getSurname(), mem.getOthernames(), act.getUsername(), mem.getPhoneno());
                        this.newEntry(pr);
                    }
                }
            }
        } catch (Exception j) {
            
        }
        return pr;
    }
    
    @Override
    public boolean assignUserToActivationCode(String userid, String activationcode) {
        boolean assigned = false;
        try {
            Activationcodes act = (Activationcodes) this.getSingleObject(Activationcodes.class, activationcode);
            if (act != null) {
                if (act.getActivationstatus().equalsIgnoreCase("INACTIVE") && act.getUsername().isEmpty()) {
                    Membership mem = (Membership) this.getSingleObject(Membership.class, userid);
                    Activationcodebatches batch = (Activationcodebatches) this.getSingleObject(Activationcodebatches.class, act.getBatchid());
                    if (mem != null && batch != null) {
                        if (batch.getInstitutionid().equalsIgnoreCase(mem.getInstitutionid())) {
                            act.setUsername(userid);
                            this.updateRecord(act);
                        }
                    }
                }
            }
        } catch (Exception j) {
        }
        return assigned;
    }
}
