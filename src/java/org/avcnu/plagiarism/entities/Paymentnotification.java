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
@Table(name = "Paymentnotification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentnotification.findAll", query = "SELECT p FROM Paymentnotification p"),
    @NamedQuery(name = "Paymentnotification.findByTransid", query = "SELECT p FROM Paymentnotification p WHERE p.transid = :transid"),
    @NamedQuery(name = "Paymentnotification.findByPaymentLogId", query = "SELECT p FROM Paymentnotification p WHERE p.paymentLogId = :paymentLogId"),
    @NamedQuery(name = "Paymentnotification.findByProductGroupCode", query = "SELECT p FROM Paymentnotification p WHERE p.productGroupCode = :productGroupCode"),
    @NamedQuery(name = "Paymentnotification.findByCustReference", query = "SELECT p FROM Paymentnotification p WHERE p.custReference = :custReference"),
    @NamedQuery(name = "Paymentnotification.findByAlternateCustReference", query = "SELECT p FROM Paymentnotification p WHERE p.alternateCustReference = :alternateCustReference"),
    @NamedQuery(name = "Paymentnotification.findByAmount", query = "SELECT p FROM Paymentnotification p WHERE p.amount = :amount"),
    @NamedQuery(name = "Paymentnotification.findByPaymentStatus", query = "SELECT p FROM Paymentnotification p WHERE p.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Paymentnotification.findByPaymentMethod", query = "SELECT p FROM Paymentnotification p WHERE p.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "Paymentnotification.findByPaymentReference", query = "SELECT p FROM Paymentnotification p WHERE p.paymentReference = :paymentReference"),
    @NamedQuery(name = "Paymentnotification.findByChannelName", query = "SELECT p FROM Paymentnotification p WHERE p.channelName = :channelName"),
    @NamedQuery(name = "Paymentnotification.findByLocation", query = "SELECT p FROM Paymentnotification p WHERE p.location = :location"),
    @NamedQuery(name = "Paymentnotification.findByIsReversal", query = "SELECT p FROM Paymentnotification p WHERE p.isReversal = :isReversal"),
    @NamedQuery(name = "Paymentnotification.findByPaymentDate", query = "SELECT p FROM Paymentnotification p WHERE p.paymentDate = :paymentDate"),
    @NamedQuery(name = "Paymentnotification.findBySettlementDate", query = "SELECT p FROM Paymentnotification p WHERE p.settlementDate = :settlementDate"),
    @NamedQuery(name = "Paymentnotification.findByInstitutionId", query = "SELECT p FROM Paymentnotification p WHERE p.institutionId = :institutionId"),
    @NamedQuery(name = "Paymentnotification.findByInstitutionName", query = "SELECT p FROM Paymentnotification p WHERE p.institutionName = :institutionName"),
    @NamedQuery(name = "Paymentnotification.findByBranchName", query = "SELECT p FROM Paymentnotification p WHERE p.branchName = :branchName"),
    @NamedQuery(name = "Paymentnotification.findByCustomerName", query = "SELECT p FROM Paymentnotification p WHERE p.customerName = :customerName"),
    @NamedQuery(name = "Paymentnotification.findByOtherCustomerInfo", query = "SELECT p FROM Paymentnotification p WHERE p.otherCustomerInfo = :otherCustomerInfo"),
    @NamedQuery(name = "Paymentnotification.findByReceiptNo", query = "SELECT p FROM Paymentnotification p WHERE p.receiptNo = :receiptNo"),
    @NamedQuery(name = "Paymentnotification.findByCollectionsAccount", query = "SELECT p FROM Paymentnotification p WHERE p.collectionsAccount = :collectionsAccount"),
    @NamedQuery(name = "Paymentnotification.findByThirdPartyCode", query = "SELECT p FROM Paymentnotification p WHERE p.thirdPartyCode = :thirdPartyCode"),
    @NamedQuery(name = "Paymentnotification.findByItemName", query = "SELECT p FROM Paymentnotification p WHERE p.itemName = :itemName"),
    @NamedQuery(name = "Paymentnotification.findByItemCode", query = "SELECT p FROM Paymentnotification p WHERE p.itemCode = :itemCode"),
    @NamedQuery(name = "Paymentnotification.findByItemAmount", query = "SELECT p FROM Paymentnotification p WHERE p.itemAmount = :itemAmount"),
    @NamedQuery(name = "Paymentnotification.findByLeadBankCode", query = "SELECT p FROM Paymentnotification p WHERE p.leadBankCode = :leadBankCode"),
    @NamedQuery(name = "Paymentnotification.findByLeadBankCbnCode", query = "SELECT p FROM Paymentnotification p WHERE p.leadBankCbnCode = :leadBankCbnCode"),
    @NamedQuery(name = "Paymentnotification.findByLeadBankName", query = "SELECT p FROM Paymentnotification p WHERE p.leadBankName = :leadBankName"),
    @NamedQuery(name = "Paymentnotification.findByBankCode", query = "SELECT p FROM Paymentnotification p WHERE p.bankCode = :bankCode"),
    @NamedQuery(name = "Paymentnotification.findByCustomerPhoneNumber", query = "SELECT p FROM Paymentnotification p WHERE p.customerPhoneNumber = :customerPhoneNumber"),
    @NamedQuery(name = "Paymentnotification.findByDepositorName", query = "SELECT p FROM Paymentnotification p WHERE p.depositorName = :depositorName"),
    @NamedQuery(name = "Paymentnotification.findByDepositSlipNumber", query = "SELECT p FROM Paymentnotification p WHERE p.depositSlipNumber = :depositSlipNumber"),
    @NamedQuery(name = "Paymentnotification.findByPaymentCurrency", query = "SELECT p FROM Paymentnotification p WHERE p.paymentCurrency = :paymentCurrency"),
    @NamedQuery(name = "Paymentnotification.findByPaymentStatusMsg", query = "SELECT p FROM Paymentnotification p WHERE p.paymentStatusMsg = :paymentStatusMsg"),
    @NamedQuery(name = "Paymentnotification.findByInhouseStatus", query = "SELECT p FROM Paymentnotification p WHERE p.inhouseStatus = :inhouseStatus")})
public class Paymentnotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "transid")
    private String transid;
    @Size(max = 200)
    @Column(name = "paymentLogId")
    private String paymentLogId;
    @Size(max = 45)
    @Column(name = "productGroupCode")
    private String productGroupCode;
    @Size(max = 45)
    @Column(name = "custReference")
    private String custReference;
    @Size(max = 45)
    @Column(name = "alternateCustReference")
    private String alternateCustReference;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 45)
    @Column(name = "paymentStatus")
    private String paymentStatus;
    @Size(max = 200)
    @Column(name = "paymentMethod")
    private String paymentMethod;
    @Size(max = 200)
    @Column(name = "paymentReference")
    private String paymentReference;
    @Size(max = 200)
    @Column(name = "channelName")
    private String channelName;
    @Size(max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 45)
    @Column(name = "isReversal")
    private String isReversal;
    @Size(max = 45)
    @Column(name = "paymentDate")
    private String paymentDate;
    @Size(max = 45)
    @Column(name = "settlementDate")
    private String settlementDate;
    @Size(max = 100)
    @Column(name = "institutionId")
    private String institutionId;
    @Size(max = 200)
    @Column(name = "institutionName")
    private String institutionName;
    @Size(max = 200)
    @Column(name = "branchName")
    private String branchName;
    @Size(max = 200)
    @Column(name = "customerName")
    private String customerName;
    @Size(max = 200)
    @Column(name = "otherCustomerInfo")
    private String otherCustomerInfo;
    @Size(max = 45)
    @Column(name = "receiptNo")
    private String receiptNo;
    @Size(max = 45)
    @Column(name = "collectionsAccount")
    private String collectionsAccount;
    @Size(max = 45)
    @Column(name = "thirdPartyCode")
    private String thirdPartyCode;
    @Size(max = 200)
    @Column(name = "itemName")
    private String itemName;
    @Size(max = 45)
    @Column(name = "itemCode")
    private String itemCode;
    @Column(name = "itemAmount")
    private Double itemAmount;
    @Size(max = 45)
    @Column(name = "leadBankCode")
    private String leadBankCode;
    @Size(max = 45)
    @Column(name = "leadBankCbnCode")
    private String leadBankCbnCode;
    @Size(max = 200)
    @Column(name = "leadBankName")
    private String leadBankName;
    @Size(max = 45)
    @Column(name = "bankCode")
    private String bankCode;
    @Size(max = 45)
    @Column(name = "customerPhoneNumber")
    private String customerPhoneNumber;
    @Size(max = 200)
    @Column(name = "depositorName")
    private String depositorName;
    @Size(max = 45)
    @Column(name = "depositSlipNumber")
    private String depositSlipNumber;
    @Size(max = 45)
    @Column(name = "paymentCurrency")
    private String paymentCurrency;
    @Size(max = 200)
    @Column(name = "paymentStatusMsg")
    private String paymentStatusMsg;
    @Size(max = 45)
    @Column(name = "inhouseStatus")
    private String inhouseStatus;

    public Paymentnotification() {
    }

    public Paymentnotification(String transid, String paymentLogId, String productGroupCode, String custReference, String alternateCustReference, Double amount, String paymentStatus, String paymentMethod, String paymentReference, String channelName, String location, String isReversal, String paymentDate, String settlementDate, String institutionId, String institutionName, String branchName, String customerName, String otherCustomerInfo, String receiptNo, String collectionsAccount, String thirdPartyCode, String itemName, String itemCode, Double itemAmount, String leadBankCode, String leadBankCbnCode, String leadBankName, String bankCode, String customerPhoneNumber, String depositorName, String depositSlipNumber, String paymentCurrency, String paymentStatusMsg, String inhouseStatus) {
        this.transid = transid;
        this.paymentLogId = paymentLogId;
        this.productGroupCode = productGroupCode;
        this.custReference = custReference;
        this.alternateCustReference = alternateCustReference;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.paymentReference = paymentReference;
        this.channelName = channelName;
        this.location = location;
        this.isReversal = isReversal;
        this.paymentDate = paymentDate;
        this.settlementDate = settlementDate;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.branchName = branchName;
        this.customerName = customerName;
        this.otherCustomerInfo = otherCustomerInfo;
        this.receiptNo = receiptNo;
        this.collectionsAccount = collectionsAccount;
        this.thirdPartyCode = thirdPartyCode;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemAmount = itemAmount;
        this.leadBankCode = leadBankCode;
        this.leadBankCbnCode = leadBankCbnCode;
        this.leadBankName = leadBankName;
        this.bankCode = bankCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.depositorName = depositorName;
        this.depositSlipNumber = depositSlipNumber;
        this.paymentCurrency = paymentCurrency;
        this.paymentStatusMsg = paymentStatusMsg;
        this.inhouseStatus = inhouseStatus;
    }

    public Paymentnotification(String transid) {
        this.transid = transid;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getPaymentLogId() {
        return paymentLogId;
    }

    public void setPaymentLogId(String paymentLogId) {
        this.paymentLogId = paymentLogId;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    public String getCustReference() {
        return custReference;
    }

    public void setCustReference(String custReference) {
        this.custReference = custReference;
    }

    public String getAlternateCustReference() {
        return alternateCustReference;
    }

    public void setAlternateCustReference(String alternateCustReference) {
        this.alternateCustReference = alternateCustReference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsReversal() {
        return isReversal;
    }

    public void setIsReversal(String isReversal) {
        this.isReversal = isReversal;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOtherCustomerInfo() {
        return otherCustomerInfo;
    }

    public void setOtherCustomerInfo(String otherCustomerInfo) {
        this.otherCustomerInfo = otherCustomerInfo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getCollectionsAccount() {
        return collectionsAccount;
    }

    public void setCollectionsAccount(String collectionsAccount) {
        this.collectionsAccount = collectionsAccount;
    }

    public String getThirdPartyCode() {
        return thirdPartyCode;
    }

    public void setThirdPartyCode(String thirdPartyCode) {
        this.thirdPartyCode = thirdPartyCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getLeadBankCode() {
        return leadBankCode;
    }

    public void setLeadBankCode(String leadBankCode) {
        this.leadBankCode = leadBankCode;
    }

    public String getLeadBankCbnCode() {
        return leadBankCbnCode;
    }

    public void setLeadBankCbnCode(String leadBankCbnCode) {
        this.leadBankCbnCode = leadBankCbnCode;
    }

    public String getLeadBankName() {
        return leadBankName;
    }

    public void setLeadBankName(String leadBankName) {
        this.leadBankName = leadBankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDepositSlipNumber() {
        return depositSlipNumber;
    }

    public void setDepositSlipNumber(String depositSlipNumber) {
        this.depositSlipNumber = depositSlipNumber;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getPaymentStatusMsg() {
        return paymentStatusMsg;
    }

    public void setPaymentStatusMsg(String paymentStatusMsg) {
        this.paymentStatusMsg = paymentStatusMsg;
    }

    public String getInhouseStatus() {
        return inhouseStatus;
    }

    public void setInhouseStatus(String inhouseStatus) {
        this.inhouseStatus = inhouseStatus;
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
        if (!(object instanceof Paymentnotification)) {
            return false;
        }
        Paymentnotification other = (Paymentnotification) object;
        if ((this.transid == null && other.transid != null) || (this.transid != null && !this.transid.equals(other.transid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Paymentnotification[ transid=" + transid + " ]";
    }
    
}
