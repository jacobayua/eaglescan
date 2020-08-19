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
public class GeneralResponse {
    private String responseCode;
    private String responseDetails;

    public GeneralResponse() {
    }

    public GeneralResponse(String responseCode, String responseDetails) {
        this.responseCode = responseCode;
        this.responseDetails = responseDetails;
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
     * @return the responseDetails
     */
    public String getResponseDetails() {
        return responseDetails;
    }

    /**
     * @param responseDetails the responseDetails to set
     */
    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }
    
}
