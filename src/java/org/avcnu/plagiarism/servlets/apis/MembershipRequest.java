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
public class MembershipRequest {
    private String membershipid;
    private String surname;
    private String othernames;
    private String institutionid;
    private String contactaddress;
    private String phoneno;
    private String memershiprights;
    private String password;

    public MembershipRequest(String membershipid, String surname, String othernames, String institutionid, String contactaddress, String phoneno, String memershiprights, String password) {
        this.membershipid = membershipid;
        this.surname = surname;
        this.othernames = othernames;
        this.institutionid = institutionid;
        this.contactaddress = contactaddress;
        this.phoneno = phoneno;
        this.memershiprights = memershiprights;
        this.password = password;
    }

    /**
     * @return the membershipid
     */
    public String getMembershipid() {
        return membershipid;
    }

    /**
     * @param membershipid the membershipid to set
     */
    public void setMembershipid(String membershipid) {
        this.membershipid = membershipid;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the othernames
     */
    public String getOthernames() {
        return othernames;
    }

    /**
     * @param othernames the othernames to set
     */
    public void setOthernames(String othernames) {
        this.othernames = othernames;
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
     * @return the contactaddress
     */
    public String getContactaddress() {
        return contactaddress;
    }

    /**
     * @param contactaddress the contactaddress to set
     */
    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    /**
     * @return the phoneno
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * @param phoneno the phoneno to set
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * @return the memershiprights
     */
    public String getMemershiprights() {
        return memershiprights;
    }

    /**
     * @param memershiprights the memershiprights to set
     */
    public void setMemershiprights(String memershiprights) {
        this.memershiprights = memershiprights;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
