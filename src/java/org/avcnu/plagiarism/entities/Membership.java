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
@Table(name = "Membership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m"),
    @NamedQuery(name = "Membership.findByMembershipid", query = "SELECT m FROM Membership m WHERE m.membershipid = :membershipid"),
    @NamedQuery(name = "Membership.findBySurname", query = "SELECT m FROM Membership m WHERE m.surname = :surname"),
    @NamedQuery(name = "Membership.findByOthernames", query = "SELECT m FROM Membership m WHERE m.othernames = :othernames"),
    @NamedQuery(name = "Membership.findByInstitutionid", query = "SELECT m FROM Membership m WHERE m.institutionid = :institutionid"),
    @NamedQuery(name = "Membership.findByContactaddress", query = "SELECT m FROM Membership m WHERE m.contactaddress = :contactaddress"),
    @NamedQuery(name = "Membership.findByPhoneno", query = "SELECT m FROM Membership m WHERE m.phoneno = :phoneno"),
    @NamedQuery(name = "Membership.findByMemershiprights", query = "SELECT m FROM Membership m WHERE m.memershiprights = :memershiprights"),
    @NamedQuery(name = "Membership.findByPassword", query = "SELECT m FROM Membership m WHERE m.password = :password"),
    @NamedQuery(name = "Membership.findByMembershipstatus", query = "SELECT m FROM Membership m WHERE m.membershipstatus = :membershipstatus")})
public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "membershipid")
    private String membershipid;
    @Size(max = 100)
    @Column(name = "surname")
    private String surname;
    @Size(max = 200)
    @Column(name = "othernames")
    private String othernames;
    @Size(max = 100)
    @Column(name = "institutionid")
    private String institutionid;
    @Size(max = 200)
    @Column(name = "contactaddress")
    private String contactaddress;
    @Size(max = 45)
    @Column(name = "phoneno")
    private String phoneno;
    @Size(max = 200)
    @Column(name = "memershiprights")
    private String memershiprights;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "membershipstatus")
    private String membershipstatus;

    public Membership(String membershipid, String surname, String othernames, String institutionid, String contactaddress, String phoneno, String memershiprights, String password, String membershipstatus) {
        this.membershipid = membershipid;
        this.surname = surname;
        this.othernames = othernames;
        this.institutionid = institutionid;
        this.contactaddress = contactaddress;
        this.phoneno = phoneno;
        this.memershiprights = memershiprights;
        this.password = password;
        this.membershipstatus = membershipstatus;
    }

    public Membership() {
    }

    public Membership(String membershipid) {
        this.membershipid = membershipid;
    }

    public String getMembershipid() {
        return membershipid;
    }

    public void setMembershipid(String membershipid) {
        this.membershipid = membershipid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMemershiprights() {
        return memershiprights;
    }

    public void setMemershiprights(String memershiprights) {
        this.memershiprights = memershiprights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMembershipstatus() {
        return membershipstatus;
    }

    public void setMembershipstatus(String membershipstatus) {
        this.membershipstatus = membershipstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membershipid != null ? membershipid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.membershipid == null && other.membershipid != null) || (this.membershipid != null && !this.membershipid.equals(other.membershipid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Membership[ membershipid=" + membershipid + " ]";
    }
    
}
