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
@Table(name = "Activationcodebatches")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activationcodebatches.findAll", query = "SELECT a FROM Activationcodebatches a"),
    @NamedQuery(name = "Activationcodebatches.findByBatchid", query = "SELECT a FROM Activationcodebatches a WHERE a.batchid = :batchid"),
    @NamedQuery(name = "Activationcodebatches.findByBatchname", query = "SELECT a FROM Activationcodebatches a WHERE a.batchname = :batchname"),
    @NamedQuery(name = "Activationcodebatches.findByDategenerated", query = "SELECT a FROM Activationcodebatches a WHERE a.dategenerated = :dategenerated"),
    @NamedQuery(name = "Activationcodebatches.findByDurationdays", query = "SELECT a FROM Activationcodebatches a WHERE a.durationdays = :durationdays"),
    @NamedQuery(name = "Activationcodebatches.findByAmount", query = "SELECT a FROM Activationcodebatches a WHERE a.amount = :amount"),
    @NamedQuery(name = "Activationcodebatches.findByInstitutionid", query = "SELECT a FROM Activationcodebatches a WHERE a.institutionid = :institutionid"),
    @NamedQuery(name = "Activationcodebatches.findByNumbergenerated", query = "SELECT a FROM Activationcodebatches a WHERE a.numbergenerated = :numbergenerated"),
    @NamedQuery(name = "Activationcodebatches.findByTotalamountreceived", query = "SELECT a FROM Activationcodebatches a WHERE a.totalamountreceived = :totalamountreceived"),
    @NamedQuery(name = "Activationcodebatches.findByGeneratedby", query = "SELECT a FROM Activationcodebatches a WHERE a.generatedby = :generatedby")})
public class Activationcodebatches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "batchid")
    private String batchid;
    @Size(max = 200)
    @Column(name = "batchname")
    private String batchname;
    @Size(max = 20)
    @Column(name = "dategenerated")
    private String dategenerated;
    @Column(name = "durationdays")
    private Integer durationdays;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 50)
    @Column(name = "institutionid")
    private String institutionid;
    @Column(name = "numbergenerated")
    private Integer numbergenerated;
    @Column(name = "totalamountreceived")
    private Double totalamountreceived;
    @Size(max = 200)
    @Column(name = "generatedby")
    private String generatedby;

    public Activationcodebatches() {
    }

    public Activationcodebatches(String batchid, String batchname, String dategenerated, Integer durationdays, Double amount, String institutionid, Integer numbergenerated, Double totalamountreceived, String generatedby) {
        this.batchid = batchid;
        this.batchname = batchname;
        this.dategenerated = dategenerated;
        this.durationdays = durationdays;
        this.amount = amount;
        this.institutionid = institutionid;
        this.numbergenerated = numbergenerated;
        this.totalamountreceived = totalamountreceived;
        this.generatedby = generatedby;
    }

    public Activationcodebatches(String batchid) {
        this.batchid = batchid;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getDategenerated() {
        return dategenerated;
    }

    public void setDategenerated(String dategenerated) {
        this.dategenerated = dategenerated;
    }

    public Integer getDurationdays() {
        return durationdays;
    }

    public void setDurationdays(Integer durationdays) {
        this.durationdays = durationdays;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public Integer getNumbergenerated() {
        return numbergenerated;
    }

    public void setNumbergenerated(Integer numbergenerated) {
        this.numbergenerated = numbergenerated;
    }

    public Double getTotalamountreceived() {
        return totalamountreceived;
    }

    public void setTotalamountreceived(Double totalamountreceived) {
        this.totalamountreceived = totalamountreceived;
    }

    public String getGeneratedby() {
        return generatedby;
    }

    public void setGeneratedby(String generatedby) {
        this.generatedby = generatedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchid != null ? batchid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activationcodebatches)) {
            return false;
        }
        Activationcodebatches other = (Activationcodebatches) object;
        if ((this.batchid == null && other.batchid != null) || (this.batchid != null && !this.batchid.equals(other.batchid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Activationcodebatches[ batchid=" + batchid + " ]";
    }
    
}
