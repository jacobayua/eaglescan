/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eaglescan
 */
@Entity
@Table(name = "Apiauthentication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apiauthentication.findAll", query = "SELECT a FROM Apiauthentication a"),
    @NamedQuery(name = "Apiauthentication.findByApikey", query = "SELECT a FROM Apiauthentication a WHERE a.apikey = :apikey"),
    @NamedQuery(name = "Apiauthentication.findByAllowedips", query = "SELECT a FROM Apiauthentication a WHERE a.allowedips = :allowedips"),
    @NamedQuery(name = "Apiauthentication.findByDateadded", query = "SELECT a FROM Apiauthentication a WHERE a.dateadded = :dateadded"),
    @NamedQuery(name = "Apiauthentication.findByAddedby", query = "SELECT a FROM Apiauthentication a WHERE a.addedby = :addedby"),
    @NamedQuery(name = "Apiauthentication.findByOrganizationname", query = "SELECT a FROM Apiauthentication a WHERE a.organizationname = :organizationname"),
    @NamedQuery(name = "Apiauthentication.findByContactemail", query = "SELECT a FROM Apiauthentication a WHERE a.contactemail = :contactemail"),
    @NamedQuery(name = "Apiauthentication.findByContactno", query = "SELECT a FROM Apiauthentication a WHERE a.contactno = :contactno"),
    @NamedQuery(name = "Apiauthentication.findByServices", query = "SELECT a FROM Apiauthentication a WHERE a.services = :services"),
    @NamedQuery(name = "Apiauthentication.findByExpirydate", query = "SELECT a FROM Apiauthentication a WHERE a.expirydate = :expirydate"),
    @NamedQuery(name = "Apiauthentication.findByExpirystatus", query = "SELECT a FROM Apiauthentication a WHERE a.expirystatus = :expirystatus"),
    @NamedQuery(name = "Apiauthentication.findByMaxapicalls", query = "SELECT a FROM Apiauthentication a WHERE a.maxapicalls = :maxapicalls"),
    @NamedQuery(name = "Apiauthentication.findByNoofapicalls", query = "SELECT a FROM Apiauthentication a WHERE a.noofapicalls = :noofapicalls")})
public class Apiauthentication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "apikey")
    private String apikey;
    @Size(max = 200)
    @Column(name = "allowedips")
    private String allowedips;
    @Size(max = 45)
    @Column(name = "dateadded")
    private String dateadded;
    @Size(max = 45)
    @Column(name = "addedby")
    private String addedby;
    @Size(max = 200)
    @Column(name = "organizationname")
    private String organizationname;
    @Size(max = 100)
    @Column(name = "contactemail")
    private String contactemail;
    @Size(max = 100)
    @Column(name = "contactno")
    private String contactno;
    @Size(max = 200)
    @Column(name = "services")
    private String services;
    @Size(max = 45)
    @Column(name = "expirydate")
    private String expirydate;
    @Size(max = 45)
    @Column(name = "expirystatus")
    private String expirystatus;
    @Column(name = "maxapicalls")
    private Long maxapicalls;
    @Column(name = "noofapicalls")
    private Long noofapicalls;

    public Apiauthentication(String apikey, String allowedips, String dateadded, String addedby, String organizationname, String contactemail, String contactno, String services, String expirydate, String expirystatus, Long maxapicalls, Long noofapicalls) {
        this.apikey = apikey;
        this.allowedips = allowedips;
        this.dateadded = dateadded;
        this.addedby = addedby;
        this.organizationname = organizationname;
        this.contactemail = contactemail;
        this.contactno = contactno;
        this.services = services;
        this.expirydate = expirydate;
        this.expirystatus = expirystatus;
        this.maxapicalls = maxapicalls;
        this.noofapicalls = noofapicalls;
    }

    public Apiauthentication() {
    }

    public Apiauthentication(String apikey) {
        this.apikey = apikey;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getAllowedips() {
        return allowedips;
    }

    public void setAllowedips(String allowedips) {
        this.allowedips = allowedips;
    }

    public String getDateadded() {
        return dateadded;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }

    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getExpirystatus() {
        return expirystatus;
    }

    public void setExpirystatus(String expirystatus) {
        this.expirystatus = expirystatus;
    }

    public Long getMaxapicalls() {
        return maxapicalls;
    }

    public void setMaxapicalls(Long maxapicalls) {
        this.maxapicalls = maxapicalls;
    }

    public Long getNoofapicalls() {
        return noofapicalls;
    }

    public void setNoofapicalls(Long noofapicalls) {
        this.noofapicalls = noofapicalls;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apikey != null ? apikey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apiauthentication)) {
            return false;
        }
        Apiauthentication other = (Apiauthentication) object;
        if ((this.apikey == null && other.apikey != null) || (this.apikey != null && !this.apikey.equals(other.apikey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Apiauthentication[ apikey=" + apikey + " ]";
    }
    
}
