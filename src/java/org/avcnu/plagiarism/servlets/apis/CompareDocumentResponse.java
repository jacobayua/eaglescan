/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

import org.avcnu.plagiarism.util.SimilarDocuments;

/**
 *
 * @author eaglescan
 */
public class CompareDocumentResponse {
    private String responseCode;
    private String responseDescription;
    private SimilarDocuments data;

    public CompareDocumentResponse() {
    }

    public CompareDocumentResponse(String responseCode, String responseDescription, SimilarDocuments data) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.data = data;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseDescription
     */
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * @param responseDescription the responseDescription to set
     */
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    /**
     * @return the data
     */
    public SimilarDocuments getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(SimilarDocuments data) {
        this.data = data;
    }
    
    
}
