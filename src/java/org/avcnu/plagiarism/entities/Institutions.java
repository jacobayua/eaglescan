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
@Table(name = "Institutions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institutions.findAll", query = "SELECT i FROM Institutions i"),
    @NamedQuery(name = "Institutions.findByInstitutionid", query = "SELECT i FROM Institutions i WHERE i.institutionid = :institutionid"),
    @NamedQuery(name = "Institutions.findByInstitutiontype", query = "SELECT i FROM Institutions i WHERE i.institutiontype = :institutiontype"),
    @NamedQuery(name = "Institutions.findByInstitutionname", query = "SELECT i FROM Institutions i WHERE i.institutionname = :institutionname"),
    @NamedQuery(name = "Institutions.findByPhysicaladdress", query = "SELECT i FROM Institutions i WHERE i.physicaladdress = :physicaladdress"),
    @NamedQuery(name = "Institutions.findByContactname", query = "SELECT i FROM Institutions i WHERE i.contactname = :contactname"),
    @NamedQuery(name = "Institutions.findByContactphone", query = "SELECT i FROM Institutions i WHERE i.contactphone = :contactphone"),
    @NamedQuery(name = "Institutions.findByContactemailaddress", query = "SELECT i FROM Institutions i WHERE i.contactemailaddress = :contactemailaddress"),
    @NamedQuery(name = "Institutions.findByYearestablished", query = "SELECT i FROM Institutions i WHERE i.yearestablished = :yearestablished"),
    @NamedQuery(name = "Institutions.findByDateadded", query = "SELECT i FROM Institutions i WHERE i.dateadded = :dateadded"),
    @NamedQuery(name = "Institutions.findByAddedby", query = "SELECT i FROM Institutions i WHERE i.addedby = :addedby")})
public class Institutions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "institutionid")
    private String institutionid;
    @Size(max = 100)
    @Column(name = "institutiontype")
    private String institutiontype;
    @Size(max = 200)
    @Column(name = "institutionname")
    private String institutionname;
    @Size(max = 200)
    @Column(name = "physicaladdress")
    private String physicaladdress;
    @Size(max = 150)
    @Column(name = "contactname")
    private String contactname;
    @Size(max = 13)
    @Column(name = "contactphone")
    private String contactphone;
    @Size(max = 100)
    @Column(name = "contactemailaddress")
    private String contactemailaddress;
    @Column(name = "yearestablished")
    private Integer yearestablished;
    @Size(max = 20)
    @Column(name = "dateadded")
    private String dateadded;
    @Size(max = 100)
    @Column(name = "addedby")
    private String addedby;

    public Institutions() {
    }

    public Institutions(String institutionid, String institutiontype, String institutionname, String physicaladdress, String contactname, String contactphone, String contactemailaddress, Integer yearestablished, String dateadded, String addedby) {
        this.institutionid = institutionid;
        this.institutiontype = institutiontype;
        this.institutionname = institutionname;
        this.physicaladdress = physicaladdress;
        this.contactname = contactname;
        this.contactphone = contactphone;
        this.contactemailaddress = contactemailaddress;
        this.yearestablished = yearestablished;
        this.dateadded = dateadded;
        this.addedby = addedby;
    }

    public Institutions(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getInstitutiontype() {
        return institutiontype;
    }

    public void setInstitutiontype(String institutiontype) {
        this.institutiontype = institutiontype;
    }

    public String getInstitutionname() {
        return institutionname;
    }

    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

    public String getPhysicaladdress() {
        return physicaladdress;
    }

    public void setPhysicaladdress(String physicaladdress) {
        this.physicaladdress = physicaladdress;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getContactemailaddress() {
        return contactemailaddress;
    }

    public void setContactemailaddress(String contactemailaddress) {
        this.contactemailaddress = contactemailaddress;
    }

    public Integer getYearestablished() {
        return yearestablished;
    }

    public void setYearestablished(Integer yearestablished) {
        this.yearestablished = yearestablished;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (institutionid != null ? institutionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institutions)) {
            return false;
        }
        Institutions other = (Institutions) object;
        if ((this.institutionid == null && other.institutionid != null) || (this.institutionid != null && !this.institutionid.equals(other.institutionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Institutions[ institutionid=" + institutionid + " ]";
    }
    
}
