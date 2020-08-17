/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import java.util.List;

/**
 *
 * @author eaglescan
 */
public class SimilarityResult {

    private double similarityPercantage;
    private double maxSimilarity;
    private List<SimilarDocuments> similarTexts;

    public SimilarityResult() {
    }

    public SimilarityResult(double similarityPercantage, double maxSimilarity,List<SimilarDocuments> similarTexts) {
        this.similarityPercantage = similarityPercantage;
        this.maxSimilarity = maxSimilarity;
        this.similarTexts = similarTexts;
    }

    /**
     * @return the similarityPercantage
     */
    public double getSimilarityPercantage() {
        return similarityPercantage;
    }

    /**
     * @param similarityPercantage the similarityPercantage to set
     */
    public void setSimilarityPercantage(double similarityPercantage) {
        this.similarityPercantage = similarityPercantage;
    }

    /**
     * @return the similarTexts
     */
    public List<SimilarDocuments> getSimilarTexts() {
        return similarTexts;
    }

    /**
     * @param similarTexts the similarTexts to set
     */
    public void setSimilarTexts(List<SimilarDocuments> similarTexts) {
        this.similarTexts = similarTexts;
    }

    /**
     * @return the maxSimilarity
     */
    public double getMaxSimilarity() {
        return maxSimilarity;
    }

    /**
     * @param maxSimilarity the maxSimilarity to set
     */
    public void setMaxSimilarity(double maxSimilarity) {
        this.maxSimilarity = maxSimilarity;
    }

}
