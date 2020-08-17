/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 *
 * @author eaglescan
 */
public class Utilities {

    private Matcher matcher;
    private final String DOMAIN_NAME_PATTERN
            = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,15}";
    private Pattern patrn = Pattern.compile(DOMAIN_NAME_PATTERN);

    //Repository Data
    //public String REPOSITORY_LOCATION="/repository"; //remote
    public String REPOSITORY_LOCATION="/home/eaglescan/repositories";//local
    public String REPOSITORY_INDEXED_LOCATION="/repositoryindexed";
    
    public Utilities() {
    }

    public String getDomainName(String url) {
        String domainName = "";
        matcher = patrn.matcher(url);
        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }
        return domainName;
    }

    public Map<String, String> getAuthentications() {
        Map<String, String> map = new HashMap<>();
        map.put("IndexDocument", "a4b5d487a88fd9e65d07ee278fed5aa");
        map.put("CheckSimilarity", "a4b5d487a88fd9e65d07ee278fed5aa");
        map.put("CompareDocument", "a4b5d487a88fd9e65d07ee278fed5aa");
        return map;
    }
    
    public SolrClient getSolrClient() {
        final String solrUrl = "http://localhost:8982/solr";
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
    
    
    public String getCurrentTime() {
        String hr1;
        String min1;
        String sec1;
        java.util.Calendar cal = new java.util.GregorianCalendar();
        int hr = cal.get(GregorianCalendar.HOUR_OF_DAY);
        int min = cal.get(GregorianCalendar.MINUTE);
        int sec = cal.get(GregorianCalendar.SECOND);
        if (Integer.toString(hr).length() < 2) {
            hr1 = "0" + hr;
        } else {
            hr1 = hr + "";
        }
        if (Integer.toString(min).length() < 2) {
            min1 = "0" + min;
        } else {
            min1 = min + "";
        }
        if (Integer.toString(sec).length() < 2) {
            sec1 = "0" + sec;
        } else {
            sec1 = sec + "";
        }

        String TIME = hr1 + ":" + min1 + ":" + sec1;
        return TIME;
    }
    
  
}
