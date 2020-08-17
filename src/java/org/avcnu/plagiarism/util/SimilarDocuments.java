/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import java.util.List;

/**
 *
 * @author eaglescan
 */
public class SimilarDocuments {
    private double similarityPercentage;
    private double maxPercentageSimilarity;
    private String document;
    private String documentid;
    private String documentfullname;
    private List<SimilarSentences> similarSentences;

    public SimilarDocuments(double similarityPercentage, double maxPercentageSimilarity, String document, String documentid, String documentfullname, List<SimilarSentences> similarSentences) {
        this.similarityPercentage = similarityPercentage;
        this.maxPercentageSimilarity = maxPercentageSimilarity;
        this.document = document;
        this.documentid = documentid;
        this.documentfullname = documentfullname;
        this.similarSentences = similarSentences;
    }
    
    public SimilarDocuments(double similarityPercentage, double maxPercentageSimilarity, String document, List<SimilarSentences> similarSentences) {
        this.similarityPercentage = similarityPercentage;
        this.maxPercentageSimilarity = maxPercentageSimilarity;
        this.document = document;
        this.similarSentences = similarSentences;
    }
    
    public SimilarDocuments(double similarityPercentage, List<SimilarSentences> similarSentences) {
        this.similarityPercentage = similarityPercentage;
        this.similarSentences = similarSentences;
    }

    public SimilarDocuments() {
    }

    /**
     * @return the similarityPercentage
     */
    public double getSimilarityPercentage() {
        return similarityPercentage;
    }

    /**
     * @param similarityPercentage the similarityPercentage to set
     */
    public void setSimilarityPercentage(double similarityPercentage) {
        this.similarityPercentage = similarityPercentage;
    }

    /**
     * @return the document
     */
    public String getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @return the similarSentences
     */
    public List<SimilarSentences> getSimilarSentences() {
        return similarSentences;
    }

    /**
     * @param similarSentences the similarSentences to set
     */
    public void setSimilarSentences(List<SimilarSentences> similarSentences) {
        this.similarSentences = similarSentences;
    }

    /**
     * @return the maxPercentageSimilarity
     */
    public double getMaxPercentageSimilarity() {
        return maxPercentageSimilarity;
    }

    /**
     * @param maxPercentageSimilarity the maxPercentageSimilarity to set
     */
    public void setMaxPercentageSimilarity(double maxPercentageSimilarity) {
        this.maxPercentageSimilarity = maxPercentageSimilarity;
    }

    /**
     * @return the documentid
     */
    public String getDocumentid() {
        return documentid;
    }

    /**
     * @param documentid the documentid to set
     */
    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    /**
     * @return the documentfullname
     */
    public String getDocumentfullname() {
        return documentfullname;
    }

    /**
     * @param documentfullname the documentfullname to set
     */
    public void setDocumentfullname(String documentfullname) {
        this.documentfullname = documentfullname;
    }
    
}
