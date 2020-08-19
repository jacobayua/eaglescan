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
public class Key1Key2Request {
    private String key1;
    private String key2;

    public Key1Key2Request() {
    }

    public Key1Key2Request(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    /**
     * @return the key1
     */
    public String getKey1() {
        return key1;
    }

    /**
     * @param key1 the key1 to set
     */
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    /**
     * @return the key2
     */
    public String getKey2() {
        return key2;
    }

    /**
     * @param key2 the key2 to set
     */
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    
}
