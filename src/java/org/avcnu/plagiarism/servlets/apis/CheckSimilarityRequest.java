/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

import java.util.List;

/**
 *
 * @author eaglescan
 */
public class CheckSimilarityRequest {
    private String document;
    private List<String> potentialSimilarDocuments;
    private double percentage;

    public CheckSimilarityRequest(String document, List<String> potentialSimilarDocuments, double percentage) {
        this.document = document;
        this.potentialSimilarDocuments = potentialSimilarDocuments;
        this.percentage = percentage;
    }

    public CheckSimilarityRequest() {
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
     * @return the potentialSimilarDocuments
     */
    public List<String> getPotentialSimilarDocuments() {
        return potentialSimilarDocuments;
    }

    /**
     * @param potentialSimilarDocuments the potentialSimilarDocuments to set
     */
    public void setPotentialSimilarDocuments(List<String> potentialSimilarDocuments) {
        this.potentialSimilarDocuments = potentialSimilarDocuments;
    }

    /**
     * @return the percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
}
