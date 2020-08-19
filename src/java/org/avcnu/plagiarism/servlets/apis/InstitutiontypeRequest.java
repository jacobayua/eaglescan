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
public class InstitutiontypeRequest {
    private String typename;
    private String description;
    private Double subscriptionamount;
    private Integer durationdays;
    private String addedby;

    public InstitutiontypeRequest(String typename, String description, Double subscriptionamount, Integer durationdays, String addedby) {
        this.typename = typename;
        this.description = description;
        this.subscriptionamount = subscriptionamount;
        this.durationdays = durationdays;
        this.addedby = addedby;
    }

    /**
     * @return the typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename the typename to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the subscriptionamount
     */
    public Double getSubscriptionamount() {
        return subscriptionamount;
    }

    /**
     * @param subscriptionamount the subscriptionamount to set
     */
    public void setSubscriptionamount(Double subscriptionamount) {
        this.subscriptionamount = subscriptionamount;
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
     * @return the addedby
     */
    public String getAddedby() {
        return addedby;
    }

    /**
     * @param addedby the addedby to set
     */
    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }
    
}
