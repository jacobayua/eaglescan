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
@Table(name = "Activationcodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activationcodes.findAll", query = "SELECT a FROM Activationcodes a"),
    @NamedQuery(name = "Activationcodes.findByActivationcode", query = "SELECT a FROM Activationcodes a WHERE a.activationcode = :activationcode"),
    @NamedQuery(name = "Activationcodes.findByBatchid", query = "SELECT a FROM Activationcodes a WHERE a.batchid = :batchid"),
    @NamedQuery(name = "Activationcodes.findByDateactivated", query = "SELECT a FROM Activationcodes a WHERE a.dateactivated = :dateactivated"),
    @NamedQuery(name = "Activationcodes.findByUsername", query = "SELECT a FROM Activationcodes a WHERE a.username = :username"),
    @NamedQuery(name = "Activationcodes.findByDategenerated", query = "SELECT a FROM Activationcodes a WHERE a.dategenerated = :dategenerated"),
    @NamedQuery(name = "Activationcodes.findByAmount", query = "SELECT a FROM Activationcodes a WHERE a.amount = :amount"),
    @NamedQuery(name = "Activationcodes.findByDurationdays", query = "SELECT a FROM Activationcodes a WHERE a.durationdays = :durationdays"),
    @NamedQuery(name = "Activationcodes.findByActivationstatus", query = "SELECT a FROM Activationcodes a WHERE a.activationstatus = :activationstatus")})
public class Activationcodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "activationcode")
    private String activationcode;
    @Size(max = 200)
    @Column(name = "batchid")
    private String batchid;
    @Size(max = 20)
    @Column(name = "dateactivated")
    private String dateactivated;
    @Size(max = 200)
    @Column(name = "username")
    private String username;
    @Size(max = 20)
    @Column(name = "dategenerated")
    private String dategenerated;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "durationdays")
    private Integer durationdays;
    @Size(max = 50)
    @Column(name = "activationstatus")
    private String activationstatus;

    public Activationcodes() {
    }

    public Activationcodes(String activationcode, String batchid, String dateactivated, String username, String dategenerated, Double amount, Integer durationdays, String activationstatus) {
        this.activationcode = activationcode;
        this.batchid = batchid;
        this.dateactivated = dateactivated;
        this.username = username;
        this.dategenerated = dategenerated;
        this.amount = amount;
        this.durationdays = durationdays;
        this.activationstatus = activationstatus;
    }

    public Activationcodes(String activationcode) {
        this.activationcode = activationcode;
    }

    public String getActivationcode() {
        return activationcode;
    }

    public void setActivationcode(String activationcode) {
        this.activationcode = activationcode;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getDateactivated() {
        return dateactivated;
    }

    public void setDateactivated(String dateactivated) {
        this.dateactivated = dateactivated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDategenerated() {
        return dategenerated;
    }

    public void setDategenerated(String dategenerated) {
        this.dategenerated = dategenerated;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDurationdays() {
        return durationdays;
    }

    public void setDurationdays(Integer durationdays) {
        this.durationdays = durationdays;
    }

    public String getActivationstatus() {
        return activationstatus;
    }

    public void setActivationstatus(String activationstatus) {
        this.activationstatus = activationstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activationcode != null ? activationcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activationcodes)) {
            return false;
        }
        Activationcodes other = (Activationcodes) object;
        if ((this.activationcode == null && other.activationcode != null) || (this.activationcode != null && !this.activationcode.equals(other.activationcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Activationcodes[ activationcode=" + activationcode + " ]";
    }
    
}
