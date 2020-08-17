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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "Document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByDocId", query = "SELECT d FROM Document d WHERE d.docId = :docId"),
    @NamedQuery(name = "Document.findByDocType", query = "SELECT d FROM Document d WHERE d.docType = :docType"),
    @NamedQuery(name = "Document.findByDocName", query = "SELECT d FROM Document d WHERE d.docName = :docName"),
    @NamedQuery(name = "Document.findByAuthor", query = "SELECT d FROM Document d WHERE d.author = :author"),
    @NamedQuery(name = "Document.findByTitle", query = "SELECT d FROM Document d WHERE d.title = :title"),
    @NamedQuery(name = "Document.findByDsession", query = "SELECT d FROM Document d WHERE d.dsession = :dsession"),
    @NamedQuery(name = "Document.findByDyear", query = "SELECT d FROM Document d WHERE d.dyear = :dyear"),
    @NamedQuery(name = "Document.findByDegree", query = "SELECT d FROM Document d WHERE d.degree = :degree"),
    @NamedQuery(name = "Document.findBySupervisor", query = "SELECT d FROM Document d WHERE d.supervisor = :supervisor"),
    @NamedQuery(name = "Document.findByAbstract1", query = "SELECT d FROM Document d WHERE d.abstract1 = :abstract1"),
    @NamedQuery(name = "Document.findByTopic", query = "SELECT d FROM Document d WHERE d.topic = :topic"),
    @NamedQuery(name = "Document.findByName", query = "SELECT d FROM Document d WHERE d.name = :name"),
    @NamedQuery(name = "Document.findByPublisherPlace", query = "SELECT d FROM Document d WHERE d.publisherPlace = :publisherPlace"),
    @NamedQuery(name = "Document.findByVolume", query = "SELECT d FROM Document d WHERE d.volume = :volume"),
    @NamedQuery(name = "Document.findByIssueNo", query = "SELECT d FROM Document d WHERE d.issueNo = :issueNo"),
    @NamedQuery(name = "Document.findByPublishedYear", query = "SELECT d FROM Document d WHERE d.publishedYear = :publishedYear"),
    @NamedQuery(name = "Document.findByPublisher", query = "SELECT d FROM Document d WHERE d.publisher = :publisher"),
    @NamedQuery(name = "Document.findByEdition", query = "SELECT d FROM Document d WHERE d.edition = :edition"),
    @NamedQuery(name = "Document.findByIsbn", query = "SELECT d FROM Document d WHERE d.isbn = :isbn"),
    @NamedQuery(name = "Document.findByPages", query = "SELECT d FROM Document d WHERE d.pages = :pages"),
    @NamedQuery(name = "Document.findByFilename", query = "SELECT d FROM Document d WHERE d.filename = :filename"),
    @NamedQuery(name = "Document.findByInstId", query = "SELECT d FROM Document d WHERE d.instId = :instId"),
    @NamedQuery(name = "Document.findByPostDate", query = "SELECT d FROM Document d WHERE d.postDate = :postDate"),
    @NamedQuery(name = "Document.findByPostTime", query = "SELECT d FROM Document d WHERE d.postTime = :postTime"),
    @NamedQuery(name = "Document.findByIpAddr", query = "SELECT d FROM Document d WHERE d.ipAddr = :ipAddr"),
    @NamedQuery(name = "Document.findByUserId", query = "SELECT d FROM Document d WHERE d.userId = :userId")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docId")
    private long docId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docType")
    private long docType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "docName")
    private String docName;
    @Size(max = 255)
    @Column(name = "author")
    private String author;
    @Size(max = 200)
    @Column(name = "title")
    private String title;
    @Size(max = 10)
    @Column(name = "dsession")
    private String dsession;
    @Size(max = 4)
    @Column(name = "dyear")
    private String dyear;
    @Size(max = 100)
    @Column(name = "degree")
    private String degree;
    @Size(max = 255)
    @Column(name = "supervisor")
    private String supervisor;
    @Size(max = 255)
    @Column(name = "abstract")
    private String abstract1;
    @Size(max = 255)
    @Column(name = "topic")
    private String topic;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "publisherPlace")
    private String publisherPlace;
    @Size(max = 20)
    @Column(name = "volume")
    private String volume;
    @Size(max = 20)
    @Column(name = "issueNo")
    private String issueNo;
    @Size(max = 10)
    @Column(name = "publishedYear")
    private String publishedYear;
    @Size(max = 255)
    @Column(name = "publisher")
    private String publisher;
    @Size(max = 100)
    @Column(name = "edition")
    private String edition;
    @Size(max = 100)
    @Column(name = "isbn")
    private String isbn;
    @Size(max = 20)
    @Column(name = "pages")
    private String pages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "filename")
    private String filename;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Column(name = "instId")
    private long instId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "postDate")
    private String postDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "postTime")
    private String postTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ipAddr")
    private String ipAddr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "userId")
    private String userId;

    public Document() {
    }

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, long docId, long docType, String docName, String author, String title, String dsession, String dyear, String degree, String supervisor, String abstract1, String topic, String name, String publisherPlace, String volume, String issueNo, String publishedYear, String publisher, String edition, String isbn, String pages, String filename, String remark, long instId, String postDate, String postTime, String ipAddr, String userId) {
        this.id = id;
        this.docId = docId;
        this.docType = docType;
        this.docName = docName;
        this.author = author;
        this.title = title;
        this.dsession = dsession;
        this.dyear = dyear;
        this.degree = degree;
        this.supervisor = supervisor;
        this.abstract1 = abstract1;
        this.topic = topic;
        this.name = name;
        this.publisherPlace = publisherPlace;
        this.volume = volume;
        this.issueNo = issueNo;
        this.publishedYear = publishedYear;
        this.publisher = publisher;
        this.edition = edition;
        this.isbn = isbn;
        this.pages = pages;
        this.filename = filename;
        this.remark = remark;
        this.instId = instId;
        this.postDate = postDate;
        this.postTime = postTime;
        this.ipAddr = ipAddr;
        this.userId = userId;
    }

    public Document(Long id, long docId, long docType, String docName, String filename, String remark, long instId, String postDate, String postTime, String ipAddr, String userId) {
        this.id = id;
        this.docId = docId;
        this.docType = docType;
        this.docName = docName;
        this.filename = filename;
        this.remark = remark;
        this.instId = instId;
        this.postDate = postDate;
        this.postTime = postTime;
        this.ipAddr = ipAddr;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public long getDocType() {
        return docType;
    }

    public void setDocType(long docType) {
        this.docType = docType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDsession() {
        return dsession;
    }

    public void setDsession(String dsession) {
        this.dsession = dsession;
    }

    public String getDyear() {
        return dyear;
    }

    public void setDyear(String dyear) {
        this.dyear = dyear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherPlace() {
        return publisherPlace;
    }

    public void setPublisherPlace(String publisherPlace) {
        this.publisherPlace = publisherPlace;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getInstId() {
        return instId;
    }

    public void setInstId(long instId) {
        this.instId = instId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Document[ id=" + id + " ]";
    }
    
}
