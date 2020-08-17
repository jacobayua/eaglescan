/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

/**
 *
 * @author eaglescan
 */
public class IndexDocumentRequest {

    private Long documentid;
    public IndexDocumentRequest() {
    }

    public IndexDocumentRequest(Long documentid) {
        this.documentid = documentid;
    }

    
    public Long getDocumentid() {
        return documentid;
    }

    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }

    
    
}
