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
import org.avcnu.plagiarism.util.GoogleSearchData;
import org.avcnu.plagiarism.util.SimilarDocuments;
import org.avcnu.plagiarism.util.SimilarSentences;
import org.avcnu.plagiarism.util.SimilarityResult;
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
    
}
