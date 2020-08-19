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
public class IdAndDaterangeRequest {
    private String id;
    private String datefrom;
    private String dateto;

    public IdAndDaterangeRequest(String id, String datefrom, String dateto) {
        this.id = id;
        this.datefrom = datefrom;
        this.dateto = dateto;
    }

    public IdAndDaterangeRequest() {
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
     * @return the datefrom
     */
    public String getDatefrom() {
        return datefrom;
    }

    /**
     * @param datefrom the datefrom to set
     */
    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    /**
     * @return the dateto
     */
    public String getDateto() {
        return dateto;
    }

    /**
     * @param dateto the dateto to set
     */
    public void setDateto(String dateto) {
        this.dateto = dateto;
    }
    
}
