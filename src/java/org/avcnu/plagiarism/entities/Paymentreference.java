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
@Table(name = "Paymentreference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentreference.findAll", query = "SELECT p FROM Paymentreference p"),
    @NamedQuery(name = "Paymentreference.findByCustReference", query = "SELECT p FROM Paymentreference p WHERE p.custReference = :custReference"),
    @NamedQuery(name = "Paymentreference.findByStatus", query = "SELECT p FROM Paymentreference p WHERE p.status = :status"),
    @NamedQuery(name = "Paymentreference.findByCustomerReferenceAlternate", query = "SELECT p FROM Paymentreference p WHERE p.customerReferenceAlternate = :customerReferenceAlternate"),
    @NamedQuery(name = "Paymentreference.findByCustomerReferenceDescription", query = "SELECT p FROM Paymentreference p WHERE p.customerReferenceDescription = :customerReferenceDescription"),
    @NamedQuery(name = "Paymentreference.findByThirdPartyCode", query = "SELECT p FROM Paymentreference p WHERE p.thirdPartyCode = :thirdPartyCode"),
    @NamedQuery(name = "Paymentreference.findByAmount", query = "SELECT p FROM Paymentreference p WHERE p.amount = :amount"),
    @NamedQuery(name = "Paymentreference.findByProductName", query = "SELECT p FROM Paymentreference p WHERE p.productName = :productName"),
    @NamedQuery(name = "Paymentreference.findByUserid", query = "SELECT p FROM Paymentreference p WHERE p.userid = :userid"),
    @NamedQuery(name = "Paymentreference.findByAccesscode", query = "SELECT p FROM Paymentreference p WHERE p.accesscode = :accesscode"),
    @NamedQuery(name = "Paymentreference.findByDategenerated", query = "SELECT p FROM Paymentreference p WHERE p.dategenerated = :dategenerated"),
    @NamedQuery(name = "Paymentreference.findByDatepaid", query = "SELECT p FROM Paymentreference p WHERE p.datepaid = :datepaid"),
    @NamedQuery(name = "Paymentreference.findByDiscount", query = "SELECT p FROM Paymentreference p WHERE p.discount = :discount"),
    @NamedQuery(name = "Paymentreference.findByCustStatus", query = "SELECT p FROM Paymentreference p WHERE p.custStatus = :custStatus"),
    @NamedQuery(name = "Paymentreference.findBySurname", query = "SELECT p FROM Paymentreference p WHERE p.surname = :surname"),
    @NamedQuery(name = "Paymentreference.findByOthernames", query = "SELECT p FROM Paymentreference p WHERE p.othernames = :othernames"),
    @NamedQuery(name = "Paymentreference.findByEmailaddress", query = "SELECT p FROM Paymentreference p WHERE p.emailaddress = :emailaddress"),
    @NamedQuery(name = "Paymentreference.findByPhoneno", query = "SELECT p FROM Paymentreference p WHERE p.phoneno = :phoneno")})
public class Paymentreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "custReference")
    private String custReference;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 200)
    @Column(name = "customerReferenceAlternate")
    private String customerReferenceAlternate;
    @Size(max = 200)
    @Column(name = "CustomerReferenceDescription")
    private String customerReferenceDescription;
    @Size(max = 45)
    @Column(name = "thirdPartyCode")
    private String thirdPartyCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 200)
    @Column(name = "productName")
    private String productName;
    @Size(max = 100)
    @Column(name = "userid")
    private String userid;
    @Size(max = 100)
    @Column(name = "accesscode")
    private String accesscode;
    @Size(max = 45)
    @Column(name = "dategenerated")
    private String dategenerated;
    @Size(max = 45)
    @Column(name = "datepaid")
    private String datepaid;
    @Column(name = "discount")
    private Double discount;
    @Size(max = 45)
    @Column(name = "custStatus")
    private String custStatus;
    @Size(max = 45)
    @Column(name = "surname")
    private String surname;
    @Size(max = 100)
    @Column(name = "othernames")
    private String othernames;
    @Size(max = 45)
    @Column(name = "emailaddress")
    private String emailaddress;
    @Size(max = 45)
    @Column(name = "phoneno")
    private String phoneno;

    public Paymentreference() {
    }

    public Paymentreference(String custReference, String status, String customerReferenceAlternate, String customerReferenceDescription, String thirdPartyCode, Double amount, String productName, String userid, String accesscode, String dategenerated, String datepaid, Double discount, String custStatus, String surname, String othernames, String emailaddress, String phoneno) {
        this.custReference = custReference;
        this.status = status;
        this.customerReferenceAlternate = customerReferenceAlternate;
        this.customerReferenceDescription = customerReferenceDescription;
        this.thirdPartyCode = thirdPartyCode;
        this.amount = amount;
        this.productName = productName;
        this.userid = userid;
        this.accesscode = accesscode;
        this.dategenerated = dategenerated;
        this.datepaid = datepaid;
        this.discount = discount;
        this.custStatus = custStatus;
        this.surname = surname;
        this.othernames = othernames;
        this.emailaddress = emailaddress;
        this.phoneno = phoneno;
    }

    public Paymentreference(String custReference) {
        this.custReference = custReference;
    }

    public String getCustReference() {
        return custReference;
    }

    public void setCustReference(String custReference) {
        this.custReference = custReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerReferenceAlternate() {
        return customerReferenceAlternate;
    }

    public void setCustomerReferenceAlternate(String customerReferenceAlternate) {
        this.customerReferenceAlternate = customerReferenceAlternate;
    }

    public String getCustomerReferenceDescription() {
        return customerReferenceDescription;
    }

    public void setCustomerReferenceDescription(String customerReferenceDescription) {
        this.customerReferenceDescription = customerReferenceDescription;
    }

    public String getThirdPartyCode() {
        return thirdPartyCode;
    }

    public void setThirdPartyCode(String thirdPartyCode) {
        this.thirdPartyCode = thirdPartyCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getDategenerated() {
        return dategenerated;
    }

    public void setDategenerated(String dategenerated) {
        this.dategenerated = dategenerated;
    }

    public String getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(String datepaid) {
        this.datepaid = datepaid;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custReference != null ? custReference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentreference)) {
            return false;
        }
        Paymentreference other = (Paymentreference) object;
        if ((this.custReference == null && other.custReference != null) || (this.custReference != null && !this.custReference.equals(other.custReference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Paymentreference[ custReference=" + custReference + " ]";
    }
    
}
