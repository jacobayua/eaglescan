/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.sessions;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.Local;
import org.apache.tika.exception.TikaException;
import org.avcnu.plagiarism.entities.Activationcodebatches;
import org.avcnu.plagiarism.entities.Activationcodes;
import org.avcnu.plagiarism.entities.Apiauthentication;
import org.avcnu.plagiarism.entities.Apicalls;
import org.avcnu.plagiarism.entities.Institutions;
import org.avcnu.plagiarism.entities.Institutiontype;
import org.avcnu.plagiarism.entities.Membership;
import org.avcnu.plagiarism.entities.Paymentreference;
import org.avcnu.plagiarism.util.GoogleSearchData;
import org.avcnu.plagiarism.util.SimilarDocuments;
import org.avcnu.plagiarism.util.SimilarSentences;
import org.avcnu.plagiarism.util.SimilarityResult;
import org.avcnu.plagiarism.entities.Document;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;
import org.xml.sax.SAXException;

/**
 *
 * @author eaglescan
 */
@Local
public interface MainSessionLocal {

    public List<GoogleSearchData> googleSearch(String sentence);

    public String extractText(InputStream stream) throws IOException, SAXException, TikaException;

    public String identifyLanguage(String text);

    public Object getSingleObject(Class<?> ent, Object pk);

    public void updateRecord(Object obj);

    public void deleteObject(Object obj);

    public void newEntry(Object obj);

    public SimilarityResult checkSimilarity(String document, List<String> potentialSimilarDocuments, double percentage);

    public List<SimilarSentences> getSimilarSentences(String sentence, String document, double percentage);

    public List<SimilarDocuments> getSimilarDocuments(String document, List<String> potentialdocuments, double percentage);

    public SimilarDocuments compareDocuments(String originalDocument, String suspiciousDocument, double percentage);

    public SimilarityResult checkLocalRepository(String suspicious, double percentageSimilarity);

    public List<RuleMatch> checkGrammer(String document, String omittedwords, String omittedphrases, Language lan);

    public List<SimilarDocuments> getSimilarDocumentsBrief(String document, List<String> potentialdocuments, double percentage);

    public boolean authenticateAPICall(String apikey, String ipaddress, String service);

    public List<Institutions> getInstitutionsByType(String type);

    public List<Membership> getMembershipByInstitution(String institutionid, String membershipttatus);

    public List<Institutiontype> getAllInstitutiontypes();

    public List<Activationcodebatches> getActivationcodebatchesByInstitution(String institutionid);

    public List<Activationcodes> getActivationcodesByBatchAndStatus(String batchid, String activationstatus);

    public List<Activationcodes> getActivationcodesByUser(String username, String activationstatus);

    public List<Activationcodebatches> getActivationcodebatchesByDaterange(String datefrom, String dateto);

    public List<Activationcodes> getActivationcodesByDategeneratedrange(String datefrom, String dateto);

    public List<Institutions> getInstitutionsByDateregisteredrange(String datefrom, String dateto);

    public List<Apiauthentication> getApiauthenticationByAllowedIP(String ip);

    public List<Apiauthentication> getApiauthenticationByDateaddedrange(String datefrom, String dateto);

    public List<Apiauthentication> getApiauthenticationByContactemail(String email);

    public List<Apiauthentication> getApiauthenticationByExpiryStatus(String status);

    public List<Apicalls> getApicallsByApikey(String apikey);

    public List<Apicalls> getApicallsByServicecalled(String service);

    public List<Apicalls> getApicallsByIPaddress(String ip);

    public List<Apicalls> getApicallsByDatecalledrange(String datefrom, String dateto);

    public List<Apicalls> getApicallsByApikeyAndDatecalledrange(String apikey, String datefrom, String dateto);

    public List<String> getApicallsAllIpaddresses();

    public List<Membership> getMembershipByInstitutionid(String instid);

    public List<Membership> getMembershipByMembershipright(String right);

    public List<Membership> getMembershipByStatus(String status);

    public List<Membership> getMembershipByStatusAndInstitutionid(String status, String institutionid);

    public List<Membership> getMembershipByRightAndInstitutionid(String right, String institutionid);

    public List<Membership> getMembershipByDateregisteredrange(String datefrom, String dateto);

    public List<Document> getDocumentByUsername(String username);

    public List<Document> getDocumentByPostdaterange(String datefrom, String dateto);

    public List<Document> getDocumentByUsernameAndPostdaterange(String username, String datefrom, String dateto);

    public List<Document> getDocumentByInstitutionid(String instid);

    public List<Document> getDocumentByInstitutionidAndPostdaterange(String institutionid, String datefrom, String dateto);

    public List<Document> getDocumentByTitle(String title);

    public List<Document> getDocumentByAuthor(String author);

    public List<String> getDocumentAllTitles();

    public List<String> getDocumentAllAuthors();

    public List<Document> getDocumentByPublisheddaterange(String yearfrom, String yearto);

    public Paymentreference generatePaymentReference(String activationcode);

    public Activationcodes generateActivationCode(String batchcode, String userid);

    public boolean assignUserToActivationCode(String userid, String activationcode);
    
}
