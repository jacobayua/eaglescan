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
import javax.persistence.Id;
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
@Table(name = "Apicalls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apicalls.findAll", query = "SELECT a FROM Apicalls a"),
    @NamedQuery(name = "Apicalls.findByApicallid", query = "SELECT a FROM Apicalls a WHERE a.apicallid = :apicallid"),
    @NamedQuery(name = "Apicalls.findByApikey", query = "SELECT a FROM Apicalls a WHERE a.apikey = :apikey"),
    @NamedQuery(name = "Apicalls.findByServicecalled", query = "SELECT a FROM Apicalls a WHERE a.servicecalled = :servicecalled"),
    @NamedQuery(name = "Apicalls.findByDatecalled", query = "SELECT a FROM Apicalls a WHERE a.datecalled = :datecalled"),
    @NamedQuery(name = "Apicalls.findByIpaddresscall", query = "SELECT a FROM Apicalls a WHERE a.ipaddresscall = :ipaddresscall")})
public class Apicalls implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "apicallid")
    private String apicallid;
    @Size(max = 200)
    @Column(name = "apikey")
    private String apikey;
    @Size(max = 100)
    @Column(name = "servicecalled")
    private String servicecalled;
    @Size(max = 45)
    @Column(name = "datecalled")
    private String datecalled;
    @Size(max = 45)
    @Column(name = "timecalled")
    private String timecalled;
    @Size(max = 45)
    @Column(name = "ipaddresscall")
    private String ipaddresscall;

    public Apicalls(String apicallid, String apikey, String servicecalled, String datecalled, String timecalled, String ipaddresscall) {
        this.apicallid = apicallid;
        this.apikey = apikey;
        this.servicecalled = servicecalled;
        this.datecalled = datecalled;
        this.timecalled=timecalled;
        this.ipaddresscall = ipaddresscall;
    }

    public Apicalls() {
    }

    public Apicalls(String apicallid) {
        this.apicallid = apicallid;
    }

    public String getApicallid() {
        return apicallid;
    }

    public void setApicallid(String apicallid) {
        this.apicallid = apicallid;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getServicecalled() {
        return servicecalled;
    }

    public void setServicecalled(String servicecalled) {
        this.servicecalled = servicecalled;
    }

    public String getDatecalled() {
        return datecalled;
    }

    public void setDatecalled(String datecalled) {
        this.datecalled = datecalled;
    }

    public String getIpaddresscall() {
        return ipaddresscall;
    }

    public void setIpaddresscall(String ipaddresscall) {
        this.ipaddresscall = ipaddresscall;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apicallid != null ? apicallid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apicalls)) {
            return false;
        }
        Apicalls other = (Apicalls) object;
        if ((this.apicallid == null && other.apicallid != null) || (this.apicallid != null && !this.apicallid.equals(other.apicallid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.avcnu.plagiarism.entities.Apicalls[ apicallid=" + apicallid + " ]";
    }

    /**
     * @return the timecalled
     */
    public String getTimecalled() {
        return timecalled;
    }

    /**
     * @param timecalled the timecalled to set
     */
    public void setTimecalled(String timecalled) {
        this.timecalled = timecalled;
    }
    
}
