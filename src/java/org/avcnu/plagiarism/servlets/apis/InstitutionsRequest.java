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
public class InstitutionsRequest {
    private String institutiontype;
    private String institutionname;
    private String physicaladdress;
    private String contactname;
    private String contactphone;
    private String contactemailaddress;
    private Integer yearestablished;
    private String addedby;

    public InstitutionsRequest(String institutiontype, String institutionname, String physicaladdress, String contactname, String contactphone, String contactemailaddress, Integer yearestablished, String addedby) {
        this.institutiontype = institutiontype;
        this.institutionname = institutionname;
        this.physicaladdress = physicaladdress;
        this.contactname = contactname;
        this.contactphone = contactphone;
        this.contactemailaddress = contactemailaddress;
        this.yearestablished = yearestablished;
        this.addedby = addedby;
    }

    /**
     * @return the institutiontype
     */
    public String getInstitutiontype() {
        return institutiontype;
    }

    /**
     * @param institutiontype the institutiontype to set
     */
    public void setInstitutiontype(String institutiontype) {
        this.institutiontype = institutiontype;
    }

    /**
     * @return the institutionname
     */
    public String getInstitutionname() {
        return institutionname;
    }

    /**
     * @param institutionname the institutionname to set
     */
    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

    /**
     * @return the physicaladdress
     */
    public String getPhysicaladdress() {
        return physicaladdress;
    }

    /**
     * @param physicaladdress the physicaladdress to set
     */
    public void setPhysicaladdress(String physicaladdress) {
        this.physicaladdress = physicaladdress;
    }

    /**
     * @return the contactname
     */
    public String getContactname() {
        return contactname;
    }

    /**
     * @param contactname the contactname to set
     */
    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    /**
     * @return the contactphone
     */
    public String getContactphone() {
        return contactphone;
    }

    /**
     * @param contactphone the contactphone to set
     */
    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    /**
     * @return the contactemailaddress
     */
    public String getContactemailaddress() {
        return contactemailaddress;
    }

    /**
     * @param contactemailaddress the contactemailaddress to set
     */
    public void setContactemailaddress(String contactemailaddress) {
        this.contactemailaddress = contactemailaddress;
    }

    /**
     * @return the yearestablished
     */
    public Integer getYearestablished() {
        return yearestablished;
    }

    /**
     * @param yearestablished the yearestablished to set
     */
    public void setYearestablished(Integer yearestablished) {
        this.yearestablished = yearestablished;
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
