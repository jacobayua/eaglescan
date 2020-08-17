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
import org.avcnu.plagiarism.util.GoogleSearchData;
import org.avcnu.plagiarism.util.SimilarDocuments;
import org.avcnu.plagiarism.util.SimilarSentences;
import org.avcnu.plagiarism.util.SimilarityResult;
import org.avcnu.plagiarism.util.Utilities;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.languagetool.JLanguageTool;
import org.languagetool.rules.Rule;
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
            Document doc = Jsoup.connect(searchURL).userAgent("Jsoup client").get();
            Elements results = doc.select("a[href]");
            for (Element result : results) {
                String attr2 = result.attr("class");
                String url = result.attr("href");
                if (!attr2.startsWith("_Zkb") && url.startsWith("/url?q=")) {
                    url = util.getDomainName(url);
                    String title = result.text();
                    response.add(new GoogleSearchData(url, title));
                }
            }
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
            for (String similarDocument : potentialdocuments) {
                try{
                Map<String, Integer> documentTokensCount = getTokensCount(document);
                double similar = getSimilarity(documentTokensCount, similarDocument);
                if (similar >= percentage) {
                   SimilarDocuments simdoc = new SimilarDocuments(similar, similar, similarDocument, null);
                            documents.add(simdoc);
                }
                 } catch (Exception j) {
        }
            }
        } catch (Exception j) {
        }
        return documents;
    }
    
    @Override
    public List<SimilarDocuments> getSimilarDocuments(String document, List<String> potentialdocuments, double percentage) {
        List<SimilarDocuments> documents = new ArrayList();
        try {
            for (String similarDocument : potentialdocuments) {
                try{
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
            }
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
        for (String token : tokens) {
            if (!tokensCount.containsKey(token)) {
                tokensCount.put(token, 1);
            } else {
                tokensCount.put(token, tokensCount.get(token) + 1);
            }
        }
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
                for (Rule rule : langTool.getAllActiveRules()) {
                    if (rule instanceof SpellingCheckRule) {
                        List<String> wordsToIgnore = Arrays.asList(wordsplit);
                        ((SpellingCheckRule) rule).addIgnoreTokens(wordsToIgnore);
                    }
                }
            }
            if (omittedphrases != null) {
                String[] phrases = omittedphrases.split("::");
                for (Rule rule : langTool.getAllActiveRules()) {
                    if (rule instanceof SpellingCheckRule) {
                        ((SpellingCheckRule) rule).acceptPhrases(Arrays.asList(phrases));
                    }
                }
            }

            matches = langTool.check(document);

        } catch (Exception ja) {
        }
        return matches;
    }    

}
