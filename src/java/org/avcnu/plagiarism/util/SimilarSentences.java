/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

/**
 *
 * @author eaglescan
 */
public class SimilarSentences {
    private double similarityPercentage;
    private String sentence;

    public SimilarSentences(double similarityPercentage, String sentence) {
        this.similarityPercentage = similarityPercentage;
        this.sentence = sentence;
    }

    public SimilarSentences() {
    }

    /**
     * @return the similarityPercentage
     */
    public double getSimilarityPercentage() {
        return similarityPercentage;
    }

    /**
     * @param similarityPercentage the similarityPercentage to set
     */
    public void setSimilarityPercentage(double similarityPercentage) {
        this.similarityPercentage = similarityPercentage;
    }

    /**
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * @param sentence the sentence to set
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    
}
