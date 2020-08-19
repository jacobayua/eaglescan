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
@Table(name = "Payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
    @NamedQuery(name = "Payments.findByCurstreference", query = "SELECT p FROM Payments p WHERE p.curstreference = :curstreference"),
    @NamedQuery(name = "Payments.findByAmount", query = "SELECT p FROM Payments p WHERE p.amount = :amount"),
    @NamedQuery(name = "Payments.findByDatepaid", query = "SELECT p FROM Payments p WHERE p.datepaid = :datepaid"),
    @NamedQuery(name = "Payments.findByTimepaid", query = "SELECT p FROM Payments p WHERE p.timepaid = :timepaid"),
    @NamedQuery(name = "Payments.findByAccesscode", query = "SELECT p FROM Payments p WHERE p.accesscode = :accesscode"),
    @NamedQuery(name = "Payments.findByBankcode", query = "SELECT p FROM Payments p WHERE p.bankcode = :bankcode"),
    @NamedQuery(name = "Payments.findByBankname", query = "SELECT p FROM Payments p WHERE p.bankname = :bankname"),
    @NamedQuery(name = "Payments.findByBranchname", query = "SELECT p FROM Payments p WHERE p.branchname = :branchname"),
    @NamedQuery(name = "Payments.findByUserid", query = "SELECT p FROM Payments p WHERE p.userid = :userid"),
    @NamedQuery(name = "Payments.findByFullname", query = "SELECT p FROM Payments p WHERE p.fullname = :fullname"),
    @NamedQuery(name = "Payments.findByPaymentoption", query = "SELECT p FROM Payments p WHERE p.paymentoption = :paymentoption")})
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "curstreference")
    private String curstreference;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 45)
    @Column(name = "datepaid")
    private String datepaid;
    @Size(max = 45)
    @Column(name = "timepaid")
    private String timepaid;
    @Size(max = 100)
    @Column(name = "accesscode")
    private String accesscode;
    @Size(max = 45)
    @Column(name = "bankcode")
    private String bankcode;
    @Size(max = 200)
    @Column(name = "bankname")
    private String bankname;
    @Size(max = 200)
    @Column(name = "branchname")
    private String branchname;
    @Size(max = 100)
    @Column(name = "userid")
    private String userid;
    @Size(max = 200)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 200)
    @Column(name = "paymentoption")
    private String paymentoption;

    public Payments() {
    }

    public Payments(String curstreference, Double amount, String datepaid, String timepaid, String accesscode, String bankcode, String bankname, String branchname, String userid, String fullname, String paymentoption) {
        this.curstreference = curstreference;
        this.amount = amount;
        this.datepaid = datepaid;
        this.timepaid = timepaid;
        this.accesscode = accesscode;
        this.bankcode = bankcode;
        this.bankname = bankname;
        this.branchname = branchname;
        this.userid = userid;
        this.fullname = fullname;
        this.paymentoption = paymentoption;
    }

    public Payments(String curstreference) {
        this.curstreference = curstreference;
    }

    public String getCurstreference() {
        return curstreference;
    }

    public void setCurstreference(String curstreference) {
        this.curstreference = curstreference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(String datepaid) {
        this.datepaid = datepaid;
    }

    public String getTimepaid() {
        return timepaid;
    }

    public void setTimepaid(String timepaid) {
        this.timepaid = timepaid;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPaymentoption() {
        return paymentoption;
    }

    public void setPaymentoption(String paymentoption) {
        this.paymentoption = paymentoption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curstreference != null ? curstreference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.curstreference == null && other.curstreference != null) || (this.curstreference != null && !this.curstreference.equals(other.curstreference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Payments[ curstreference=" + curstreference + " ]";
    }
    
}
