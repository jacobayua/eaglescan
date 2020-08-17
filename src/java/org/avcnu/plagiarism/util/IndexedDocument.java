/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author eaglescan
 */
public class IndexedDocument {

    @Field
    private String id;

    @Field
    private long docId;

    @Field
    private long docType;

    @Field
    private String docName;

    @Field
    private String author;

    @Field
    private String title;

    @Field
    private String dsession;

    @Field
    private String dyear;

    @Field
    private String degree;

    @Field
    private String supervisor;

    @Field
    private String abstract1;

    @Field
    private String topic;

    @Field
    private String name;

    @Field
    private String publisherPlace;

    @Field
    private String volume;

    @Field
    private String issueNo;

    @Field
    private String publishedYear;

    @Field
    private String publisher;

    @Field
    private String edition;

    @Field
    private String isbn;

    @Field
    private String pages;

    @Field
    private String filename;

    @Field
    private String remark;

    @Field
    private long instId;

    @Field
    private String postDate;

    @Field
    private String postTime;

    @Field
    private String ipAddr;

    @Field
    private String userId;

    @Field
    private String text;

    public IndexedDocument() {
    }

    public IndexedDocument(String id, long docId, long docType, String docName, String author, String title, String dsession, String dyear, String degree, String supervisor, String abstract1, String topic, String name, String publisherPlace, String volume, String issueNo, String publishedYear, String publisher, String edition, String isbn, String pages, String filename, String remark, long instId, String postDate, String postTime, String ipAddr, String userId, String text) {
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
        this.text = text;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the docId
     */
    public long getDocId() {
        return docId;
    }

    /**
     * @param docId the docId to set
     */
    public void setDocId(long docId) {
        this.docId = docId;
    }

    /**
     * @return the docType
     */
    public long getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(long docType) {
        this.docType = docType;
    }

    /**
     * @return the docName
     */
    public String getDocName() {
        return docName;
    }

    /**
     * @param docName the docName to set
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the dsession
     */
    public String getDsession() {
        return dsession;
    }

    /**
     * @param dsession the dsession to set
     */
    public void setDsession(String dsession) {
        this.dsession = dsession;
    }

    /**
     * @return the dyear
     */
    public String getDyear() {
        return dyear;
    }

    /**
     * @param dyear the dyear to set
     */
    public void setDyear(String dyear) {
        this.dyear = dyear;
    }

    /**
     * @return the degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the abstract1
     */
    public String getAbstract1() {
        return abstract1;
    }

    /**
     * @param abstract1 the abstract1 to set
     */
    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the publisherPlace
     */
    public String getPublisherPlace() {
        return publisherPlace;
    }

    /**
     * @param publisherPlace the publisherPlace to set
     */
    public void setPublisherPlace(String publisherPlace) {
        this.publisherPlace = publisherPlace;
    }

    /**
     * @return the volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * @return the issueNo
     */
    public String getIssueNo() {
        return issueNo;
    }

    /**
     * @param issueNo the issueNo to set
     */
    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    /**
     * @return the publishedYear
     */
    public String getPublishedYear() {
        return publishedYear;
    }

    /**
     * @param publishedYear the publishedYear to set
     */
    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the pages
     */
    public String getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the instId
     */
    public long getInstId() {
        return instId;
    }

    /**
     * @param instId the instId to set
     */
    public void setInstId(long instId) {
        this.instId = instId;
    }

    /**
     * @return the postDate
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the postTime
     */
    public String getPostTime() {
        return postTime;
    }

    /**
     * @param postTime the postTime to set
     */
    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    /**
     * @return the ipAddr
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * @param ipAddr the ipAddr to set
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
