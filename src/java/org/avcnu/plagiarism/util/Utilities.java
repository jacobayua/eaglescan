/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
    
  public String getPincodes(int size) {
        String pin;
        Random ra = new Random();
        long l1 = ra.nextLong() % 100000000;
        if (l1 < 0) {
            l1 = (-1) * l1;
        }
        long l2 = ra.nextLong() % 100000000;
        if (l2 < 0) {
            l2 = (-1) * l2;
        }
        long l3 = ra.nextLong() % 100000000;
        if (l3 < 0) {
            l3 = (-1) * l3;
        }
        long l4 = ra.nextLong() % 100000000;
        if (l4 < 0) {
            l4 = (-1) * l4;
        }

        String f = Long.toString(l1) + Long.toString(l2) + Long.toString(l3) + Long.toString(l4);
        String cor = "1234567890987654321123456789";
        pin = (f + cor).substring(0, size);
        return pin;
    }

    public String getTodaysdate() {
        String month1 = "";
        String day1 = "";
        java.util.Calendar cal = new java.util.GregorianCalendar();
        int year = cal.get(GregorianCalendar.YEAR);
        int month = (cal.get(GregorianCalendar.MONTH) + 1);
        int day = cal.get(GregorianCalendar.DATE);
        if (Integer.toString(month).length() < 2) {
            month1 = "0" + month;
        } else {
            month1 = month + "";
        }
        if (Integer.toString(day).length() < 2) {
            day1 = "0" + day;
        } else {
            day1 = day + "";
        }

        String TODAY = year + "-" + month1 + "-" + day1;
        return TODAY;
    }
    
    public String getMD5(String str) {
        String co = str;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] pb = str.getBytes();
            md.reset();
            byte[] db = md.digest(pb);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < db.length; i++) {
                sb.append(Integer.toHexString(0xff & db[i]));
            }
            co = sb.toString();
        } catch (NoSuchAlgorithmException j) {
        }
        return co;
    }
    public List<String> getAllServices(){
        List<String> services = new ArrayList();
        services.add("Admingeneral");
        return services;
    }
    
    public List<String> getAllRigths(){
        List<String> rights = new ArrayList();
        rights.add("");
        return rights;
    }
    public List<String> getoperations(){
        List<String> list = new ArrayList();
        try{
            list.add("NEWACTIVATIONCODEBATCH");
            list.add("NEWAPIKEY");
            list.add("NEWINSTITUTION");
            list.add("NEWINSTITUTIONTYPE");
            list.add("NEWMEMBER");
            list.add("ENCRYPTTEXT");
            list.add("UPDATEDOCUMENT");
            list.add("UPDATEINSTITUTION");
            list.add("UPDATEINSTITUTIONTYPE");
            list.add("UPDATEMEMBERSHIP");
            list.add("GETACTIVATIONCODEBATCH");
            list.add("GETACTIVATIONCODE");
            list.add("GETAPIKEY");
            list.add("GETAPICALL");
            list.add("GETDOCUMENT");
            list.add("GETINSTITUTION");
            list.add("GETINSTITUTIONTYPE");
            list.add("GETMEMBERSHIP");
            list.add("GETPAYMENTNOTIFICATION");
            list.add("GETPAYMENTREFERENCE");
            list.add("GETPAYMENT");
            list.add("GETPAYMENTTRASH");
            list.add("GETWEBPAYMENT");
            list.add("GETAPIKEYSBYIP");
            list.add("GETAPIKEYSBYEMAIL");
            list.add("GETAPIKEYSBYSTATUS");
            list.add("GETAPIKEYSBYDATERANGE");
            list.add("GETAPICALLSBYAPIKEY");
            list.add("GETAPICALLSBYSERVICE");
            list.add("GETAPICALLSBYIP");
            list.add("GETAPICALLSBYDATERANGE");
            list.add("GETAPICALLSBYAPIKEYANDDATERANGE");
            list.add("GETALLIPSINAPICALLS");
            list.add("GETALLINSTITUTIONTYPES");
            list.add("GETINSTITUTIONSBYTYPE");
            list.add("GETINSTITUTIONSBYDATEREGISTERED");
            list.add("GETACTIVATIONCODEBATCHESBYINSTITUTION");
            list.add("BETACTIVATIONCODEBATCHESBYDATERANGE");
            list.add("GETACTIVATIONCODESBYDATERANGE");
            list.add("GETACTIVATIONCODESBYBATCHIDANDSTATUS");
            list.add("GETACTIVATIONCODESBYUSERANDSTATUS");
            list.add("GETMEMBERSBYINSTITUTIONANDSTATUS");
            list.add("GETMEMBERSHIPBYRIGHT");
            list.add("GETMEMBERSHIPBYRIGHTANDINSTITUTION");
            list.add("GETMEMBERSREGISTEREDBYDATE");
            list.add("GETDOCUMENTSBYUSERID");
            list.add("GETDOCUMENTBYPOSTDATERANGE");
            list.add("GETDOCUMENTBYUSERIDANDPOSTDATERANGE");
            list.add("GETDOCUMENTBYINSTITUTION");
            list.add("GETDOCUMENTBYINSTITUTIONANDPOSTDATERANGE");
            list.add("GETDOCUMENTSBYTITLE");
            list.add("GETDOCUMENTSBYAUTHOR");
            list.add("GETDOCUMENTTITLES");
            list.add("GETDOCUMENTAUTHORS");
            list.add("GENERATEACTIVATIONCODESFORBATCHANDUSERID");
            list.add("ASSIGNACTIVATIONCODETOUSER");
            list.add("GENERATEPAYMENTREFERENCE");
            
        }catch(Exception n){}
        return list;
    }
    
    
}
