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
@Table(name = "Institutiontype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institutiontype.findAll", query = "SELECT i FROM Institutiontype i"),
    @NamedQuery(name = "Institutiontype.findByTypeid", query = "SELECT i FROM Institutiontype i WHERE i.typeid = :typeid"),
    @NamedQuery(name = "Institutiontype.findByTypename", query = "SELECT i FROM Institutiontype i WHERE i.typename = :typename"),
    @NamedQuery(name = "Institutiontype.findByDescription", query = "SELECT i FROM Institutiontype i WHERE i.description = :description"),
    @NamedQuery(name = "Institutiontype.findBySubscriptionamount", query = "SELECT i FROM Institutiontype i WHERE i.subscriptionamount = :subscriptionamount"),
    @NamedQuery(name = "Institutiontype.findByDurationdays", query = "SELECT i FROM Institutiontype i WHERE i.durationdays = :durationdays"),
    @NamedQuery(name = "Institutiontype.findByDateadded", query = "SELECT i FROM Institutiontype i WHERE i.dateadded = :dateadded"),
    @NamedQuery(name = "Institutiontype.findByAddedby", query = "SELECT i FROM Institutiontype i WHERE i.addedby = :addedby")})
public class Institutiontype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "typeid")
    private String typeid;
    @Size(max = 200)
    @Column(name = "typename")
    private String typename;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subscriptionamount")
    private Double subscriptionamount;
    @Column(name = "durationdays")
    private Integer durationdays;
    @Size(max = 20)
    @Column(name = "dateadded")
    private String dateadded;
    @Size(max = 200)
    @Column(name = "addedby")
    private String addedby;

    public Institutiontype() {
    }

    public Institutiontype(String typeid, String typename, String description, Double subscriptionamount, Integer durationdays, String dateadded, String addedby) {
        this.typeid = typeid;
        this.typename = typename;
        this.description = description;
        this.subscriptionamount = subscriptionamount;
        this.durationdays = durationdays;
        this.dateadded = dateadded;
        this.addedby = addedby;
    }

    public Institutiontype(String typeid) {
        this.typeid = typeid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSubscriptionamount() {
        return subscriptionamount;
    }

    public void setSubscriptionamount(Double subscriptionamount) {
        this.subscriptionamount = subscriptionamount;
    }

    public Integer getDurationdays() {
        return durationdays;
    }

    public void setDurationdays(Integer durationdays) {
        this.durationdays = durationdays;
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
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institutiontype)) {
            return false;
        }
        Institutiontype other = (Institutiontype) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Institutiontype[ typeid=" + typeid + " ]";
    }
    
}
