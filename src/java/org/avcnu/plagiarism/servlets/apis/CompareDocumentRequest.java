/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

/**
 *
 * @author eaglescan
 */
public class CompareDocumentRequest {
    private String originalDocument;
    private String suspiciousDocument;
    private double percentage;

    public CompareDocumentRequest() {
    }

    public CompareDocumentRequest(String originalDocument, String suspiciousDocument, double percentage) {
        this.originalDocument = originalDocument;
        this.suspiciousDocument = suspiciousDocument;
        this.percentage = percentage;
    }

    /**
     * @return the originalDocument
     */
    public String getOriginalDocument() {
        return originalDocument;
    }

    /**
     * @param originalDocument the originalDocument to set
     */
    public void setOriginalDocument(String originalDocument) {
        this.originalDocument = originalDocument;
    }

    /**
     * @return the suspiciousDocument
     */
    public String getSuspiciousDocument() {
        return suspiciousDocument;
    }

    /**
     * @param suspiciousDocument the suspiciousDocument to set
     */
    public void setSuspiciousDocument(String suspiciousDocument) {
        this.suspiciousDocument = suspiciousDocument;
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
