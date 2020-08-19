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
@Table(name = "Webpayattempts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Webpayattempts.findAll", query = "SELECT w FROM Webpayattempts w"),
    @NamedQuery(name = "Webpayattempts.findByTransid", query = "SELECT w FROM Webpayattempts w WHERE w.transid = :transid"),
    @NamedQuery(name = "Webpayattempts.findByCustref", query = "SELECT w FROM Webpayattempts w WHERE w.custref = :custref"),
    @NamedQuery(name = "Webpayattempts.findByDateattempt", query = "SELECT w FROM Webpayattempts w WHERE w.dateattempt = :dateattempt"),
    @NamedQuery(name = "Webpayattempts.findByTimeattempt", query = "SELECT w FROM Webpayattempts w WHERE w.timeattempt = :timeattempt"),
    @NamedQuery(name = "Webpayattempts.findByResponsestatus", query = "SELECT w FROM Webpayattempts w WHERE w.responsestatus = :responsestatus"),
    @NamedQuery(name = "Webpayattempts.findByRetref", query = "SELECT w FROM Webpayattempts w WHERE w.retref = :retref"),
    @NamedQuery(name = "Webpayattempts.findByCustipadd", query = "SELECT w FROM Webpayattempts w WHERE w.custipadd = :custipadd"),
    @NamedQuery(name = "Webpayattempts.findByCardno", query = "SELECT w FROM Webpayattempts w WHERE w.cardno = :cardno"),
    @NamedQuery(name = "Webpayattempts.findByAmount", query = "SELECT w FROM Webpayattempts w WHERE w.amount = :amount"),
    @NamedQuery(name = "Webpayattempts.findByResponsedesc", query = "SELECT w FROM Webpayattempts w WHERE w.responsedesc = :responsedesc"),
    @NamedQuery(name = "Webpayattempts.findByBankreference", query = "SELECT w FROM Webpayattempts w WHERE w.bankreference = :bankreference"),
    @NamedQuery(name = "Webpayattempts.findBySettlementdate", query = "SELECT w FROM Webpayattempts w WHERE w.settlementdate = :settlementdate")})
public class Webpayattempts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "transid")
    private String transid;
    @Size(max = 45)
    @Column(name = "custref")
    private String custref;
    @Size(max = 45)
    @Column(name = "dateattempt")
    private String dateattempt;
    @Size(max = 45)
    @Column(name = "timeattempt")
    private String timeattempt;
    @Size(max = 45)
    @Column(name = "responsestatus")
    private String responsestatus;
    @Size(max = 45)
    @Column(name = "retref")
    private String retref;
    @Size(max = 45)
    @Column(name = "custipadd")
    private String custipadd;
    @Size(max = 45)
    @Column(name = "cardno")
    private String cardno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 200)
    @Column(name = "responsedesc")
    private String responsedesc;
    @Size(max = 200)
    @Column(name = "bankreference")
    private String bankreference;
    @Size(max = 45)
    @Column(name = "settlementdate")
    private String settlementdate;

    public Webpayattempts() {
    }

    public Webpayattempts(String transid, String custref, String dateattempt, String timeattempt, String responsestatus, String retref, String custipadd, String cardno, Double amount, String responsedesc, String bankreference, String settlementdate) {
        this.transid = transid;
        this.custref = custref;
        this.dateattempt = dateattempt;
        this.timeattempt = timeattempt;
        this.responsestatus = responsestatus;
        this.retref = retref;
        this.custipadd = custipadd;
        this.cardno = cardno;
        this.amount = amount;
        this.responsedesc = responsedesc;
        this.bankreference = bankreference;
        this.settlementdate = settlementdate;
    }

    public Webpayattempts(String transid) {
        this.transid = transid;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getCustref() {
        return custref;
    }

    public void setCustref(String custref) {
        this.custref = custref;
    }

    public String getDateattempt() {
        return dateattempt;
    }

    public void setDateattempt(String dateattempt) {
        this.dateattempt = dateattempt;
    }

    public String getTimeattempt() {
        return timeattempt;
    }

    public void setTimeattempt(String timeattempt) {
        this.timeattempt = timeattempt;
    }

    public String getResponsestatus() {
        return responsestatus;
    }

    public void setResponsestatus(String responsestatus) {
        this.responsestatus = responsestatus;
    }

    public String getRetref() {
        return retref;
    }

    public void setRetref(String retref) {
        this.retref = retref;
    }

    public String getCustipadd() {
        return custipadd;
    }

    public void setCustipadd(String custipadd) {
        this.custipadd = custipadd;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getResponsedesc() {
        return responsedesc;
    }

    public void setResponsedesc(String responsedesc) {
        this.responsedesc = responsedesc;
    }

    public String getBankreference() {
        return bankreference;
    }

    public void setBankreference(String bankreference) {
        this.bankreference = bankreference;
    }

    public String getSettlementdate() {
        return settlementdate;
    }

    public void setSettlementdate(String settlementdate) {
        this.settlementdate = settlementdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transid != null ? transid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Webpayattempts)) {
            return false;
        }
        Webpayattempts other = (Webpayattempts) object;
        if ((this.transid == null && other.transid != null) || (this.transid != null && !this.transid.equals(other.transid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Webpayattempts[ transid=" + transid + " ]";
    }
    
}
