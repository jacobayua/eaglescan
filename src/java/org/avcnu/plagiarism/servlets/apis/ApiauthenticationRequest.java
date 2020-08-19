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
public class ApiauthenticationRequest {
    private String allowedips;
    private String addedby;
    private String organizationname;
    private String contactemail;
    private String contactno;
    private String services;
    private String expirydate;
    private Long maxapicalls;

    public ApiauthenticationRequest(String allowedips, String addedby, String organizationname, String contactemail, String contactno, String services, String expirydate, Long maxapicalls) {
        this.allowedips = allowedips;
        this.addedby = addedby;
        this.organizationname = organizationname;
        this.contactemail = contactemail;
        this.contactno = contactno;
        this.services = services;
        this.expirydate = expirydate;
        this.maxapicalls = maxapicalls;
    }

    /**
     * @return the allowedips
     */
    public String getAllowedips() {
        return allowedips;
    }

    /**
     * @param allowedips the allowedips to set
     */
    public void setAllowedips(String allowedips) {
        this.allowedips = allowedips;
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

    /**
     * @return the organizationname
     */
    public String getOrganizationname() {
        return organizationname;
    }

    /**
     * @param organizationname the organizationname to set
     */
    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    /**
     * @return the contactemail
     */
    public String getContactemail() {
        return contactemail;
    }

    /**
     * @param contactemail the contactemail to set
     */
    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    /**
     * @return the contactno
     */
    public String getContactno() {
        return contactno;
    }

    /**
     * @param contactno the contactno to set
     */
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    /**
     * @return the services
     */
    public String getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(String services) {
        this.services = services;
    }

    /**
     * @return the expirydate
     */
    public String getExpirydate() {
        return expirydate;
    }

    /**
     * @param expirydate the expirydate to set
     */
    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    /**
     * @return the maxapicalls
     */
    public Long getMaxapicalls() {
        return maxapicalls;
    }

    /**
     * @param maxapicalls the maxapicalls to set
     */
    public void setMaxapicalls(Long maxapicalls) {
        this.maxapicalls = maxapicalls;
    }
    
}
