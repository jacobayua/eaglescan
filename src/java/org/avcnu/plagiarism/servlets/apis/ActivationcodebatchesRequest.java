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
public class ActivationcodebatchesRequest {
    private String batchname;
    private Integer durationdays;
    private Double amount;
    private String institutionid;
    private Integer numbergenerated;
    private String generatedby;

    public ActivationcodebatchesRequest(String batchname, Integer durationdays, Double amount, String institutionid, Integer numbergenerated, String generatedby) {
        this.batchname = batchname;
        this.durationdays = durationdays;
        this.amount = amount;
        this.institutionid = institutionid;
        this.numbergenerated = numbergenerated;
        this.generatedby = generatedby;
    }

    /**
     * @return the batchname
     */
    public String getBatchname() {
        return batchname;
    }

    /**
     * @param batchname the batchname to set
     */
    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    /**
     * @return the durationdays
     */
    public Integer getDurationdays() {
        return durationdays;
    }

    /**
     * @param durationdays the durationdays to set
     */
    public void setDurationdays(Integer durationdays) {
        this.durationdays = durationdays;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the institutionid
     */
    public String getInstitutionid() {
        return institutionid;
    }

    /**
     * @param institutionid the institutionid to set
     */
    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    /**
     * @return the numbergenerated
     */
    public Integer getNumbergenerated() {
        return numbergenerated;
    }

    /**
     * @param numbergenerated the numbergenerated to set
     */
    public void setNumbergenerated(Integer numbergenerated) {
        this.numbergenerated = numbergenerated;
    }

    /**
     * @return the generatedby
     */
    public String getGeneratedby() {
        return generatedby;
    }

    /**
     * @param generatedby the generatedby to set
     */
    public void setGeneratedby(String generatedby) {
        this.generatedby = generatedby;
    }
    
}
