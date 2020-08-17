/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import org.languagetool.Language;

/**
 *
 * @author eaglescan
 */
public class Languages {
    private Language language;
    private String description;

    public Languages() {
    }

    public Languages(Language language, String description) {
        this.language = language;
        this.description = description;
    }

    /**
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
