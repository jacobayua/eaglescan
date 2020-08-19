/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.avcnu.plagiarism.entities.Activationcodebatches;
import org.avcnu.plagiarism.entities.Activationcodes;
import org.avcnu.plagiarism.entities.Apiauthentication;
import org.avcnu.plagiarism.entities.Apicalls;
import org.avcnu.plagiarism.entities.Document;
import org.avcnu.plagiarism.entities.Institutions;
import org.avcnu.plagiarism.entities.Institutiontype;
import org.avcnu.plagiarism.entities.Membership;
import org.avcnu.plagiarism.entities.Paymentnotification;
import org.avcnu.plagiarism.entities.Paymentreference;
import org.avcnu.plagiarism.entities.Payments;
import org.avcnu.plagiarism.entities.Paymenttrash;
import org.avcnu.plagiarism.entities.Webpayattempts;
import org.avcnu.plagiarism.sessions.MainSessionLocal;
import org.avcnu.plagiarism.util.Utilities;

/**
 *
 * @author eaglescan
 */
public class Admingeneral extends HttpServlet {

    @EJB
    private MainSessionLocal sess;

    Utilities util = new Utilities();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        GeneralResponse status = new GeneralResponse();
        try {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            String servicename = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1, request.getRequestURI().length());
            String apikey = request.getHeader("authentication");

            boolean authenticated = sess.authenticateAPICall(apikey, ipAddress, servicename);
            if (apikey != null) {
                if (authenticated) {
                    var sb = new StringBuffer();
                    String s;
                    while ((s = request.getReader().readLine()) != null) {
                        sb.append(s);
                    }
                    String operation = request.getHeader("operation");
                    if (operation != null) {
                        if (operation.equalsIgnoreCase("NEWACTIVATIONCODEBATCH")) {
                            if (sb.toString().length() > 0) {
                                ActivationcodebatchesRequest docid = (ActivationcodebatchesRequest) gson.fromJson(sb.toString(), ActivationcodebatchesRequest.class);
                                if (docid != null) {
                                    Institutions inst = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstitutionid());
                                    if (inst != null) {
                                        Activationcodebatches batch = new Activationcodebatches(util.getPincodes(14), docid.getBatchname(),
                                                util.getTodaysdate(), docid.getDurationdays(), docid.getAmount(), docid.getInstitutionid(),
                                                docid.getNumbergenerated(), 0d, docid.getGeneratedby());
                                        sess.newEntry(batch);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails(gson.toJson(batch));
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Wrong institutionid prodided: " + docid.getInstitutionid());
                                    }

                                } else {
                                    status.setResponseCode("ERROR");
                                    ActivationcodebatchesRequest dummy = new ActivationcodebatchesRequest("Batch Name", 365, 700d, "123", 50, "jacobayua@gmail.com");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("NEWACTIVATIONCODES")) {
                            if (sb.toString().length() > 0) {
                                ActivationcodebatchesRequest docid = (ActivationcodebatchesRequest) gson.fromJson(sb.toString(), ActivationcodebatchesRequest.class);
                                if (docid != null) {
                                    Institutions inst = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstitutionid());
                                    if (inst != null) {
                                        if (docid.getNumbergenerated() > 0) {
                                            String batchcode = util.getPincodes(10);
                                            Activationcodebatches batch = new Activationcodebatches(batchcode, docid.getBatchname(),
                                                    util.getTodaysdate(), docid.getDurationdays(), docid.getAmount(), docid.getInstitutionid(),
                                                    docid.getNumbergenerated(), 0d, docid.getGeneratedby());
                                            sess.newEntry(batch);
                                            try {
                                                for (int i = 0; i < docid.getNumbergenerated(); i++) {
                                                    Activationcodes actcodes = new Activationcodes(batchcode + util.getPincodes(6), batchcode, "", "", util.getTodaysdate(),
                                                            docid.getAmount(), docid.getDurationdays(), "INACTIVE");
                                                    sess.newEntry(actcodes);
                                                }
                                            } catch (Exception j) {
                                            }
                                            status.setResponseCode("SUCCESS");
                                            status.setResponseDetails(gson.toJson(batch));
                                        } else {
                                            status.setResponseCode("ERROR");
                                            status.setResponseDetails("Invalid Number for activation codes: " + docid.getNumbergenerated());
                                        }

                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Wrong institutionid prodided: " + docid.getInstitutionid());
                                    }

                                } else {
                                    status.setResponseCode("ERROR");
                                    ActivationcodebatchesRequest dummy = new ActivationcodebatchesRequest("Batch Name", 365, 700d, "123", 50, "jacobayua@gmail.com");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("NEWAPIKEY")) {
                            if (sb.toString().length() > 0) {
                                ApiauthenticationRequest docid = (ApiauthenticationRequest) gson.fromJson(sb.toString(), ApiauthenticationRequest.class);
                                if (docid != null) {
                                    if (docid.getMaxapicalls() > 0 || docid.getMaxapicalls() == -1) {
                                        String apiplain = util.getPincodes(10);
                                        String api = util.getMD5(apiplain);
                                        Apiauthentication auth = new Apiauthentication(api, docid.getAllowedips(), util.getTodaysdate(),
                                                docid.getAddedby(), docid.getOrganizationname(), docid.getContactemail(),
                                                docid.getContactno(), docid.getServices(), docid.getExpirydate(),
                                                "ACTIVE", docid.getMaxapicalls(), 0l);
                                        sess.newEntry(auth);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails(gson.toJson(auth));
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Maximum api " + docid.getMaxapicalls() + " calls should be more than 0 or -1");
                                    }

                                } else {
                                    status.setResponseCode("ERROR");
                                    ApiauthenticationRequest dummy = new ApiauthenticationRequest("1.2.3.4;6.7.3.2", "username",
                                            "Organization Name", "a@b.com", "2347011111111", "Service1;Service2;ServiceN", "2020-0x-0y",
                                            -1l);
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("NEWINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                InstitutionsRequest docid = (InstitutionsRequest) gson.fromJson(sb.toString(), InstitutionsRequest.class);
                                if (docid != null) {
                                    Institutiontype instype = (Institutiontype) sess.getSingleObject(Institutiontype.class, docid.getInstitutiontype());
                                    if (instype != null) {
                                        String apiplain = util.getPincodes(6);
                                        Institutions inst = new Institutions(apiplain, docid.getInstitutiontype(), docid.getInstitutionname(),
                                                docid.getPhysicaladdress(), docid.getContactname(), docid.getContactphone(),
                                                docid.getContactemailaddress(), docid.getYearestablished(), util.getTodaysdate(), docid.getAddedby());
                                        sess.newEntry(inst);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails(gson.toJson(inst));
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Institution type " + docid.getInstitutiontype() + " does not exist");
                                    }

                                } else {
                                    status.setResponseCode("ERROR");
                                    InstitutionsRequest dummy = new InstitutionsRequest("NIGERIAN UNIVERSITIES", "Benue State University Makurdi",
                                            "KM 1 Gboko Road, Makurdi", "jacob Ayua", "2347011111111", "a@b.com", 1900, "username");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("NEWINSTITUTIONTYPE")) {
                            if (sb.toString().length() > 0) {
                                Institutiontype docid = (Institutiontype) gson.fromJson(sb.toString(), Institutiontype.class);
                                if (docid != null) {
                                    if ((docid.getDurationdays() > 0 || docid.getDurationdays() == -1) && (docid.getSubscriptionamount() > 0 || docid.getSubscriptionamount() == -1)) {
                                        String apiplain = util.getPincodes(4);
                                        Institutiontype inst = new Institutiontype(apiplain, docid.getTypename(), docid.getDescription(),
                                                docid.getSubscriptionamount(), docid.getDurationdays(), util.getTodaysdate(), docid.getAddedby());
                                        sess.newEntry(inst);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails(gson.toJson(inst));
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Either duration or subscription is less than 0 ");
                                    }

                                } else {
                                    status.setResponseCode("ERROR");
                                    InstitutiontypeRequest dummy = new InstitutiontypeRequest("NIGERIAN UNIVERSITIES", "Only Nigerian Universities",
                                            600d, 365, "username");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("NEWMEMBER")) {
                            if (sb.toString().length() > 0) {
                                MembershipRequest docid = (MembershipRequest) gson.fromJson(sb.toString(), MembershipRequest.class);
                                if (docid != null) {
                                    Institutions inst = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstitutionid());
                                    if (inst != null) {
                                        String password = util.getMD5(docid.getPassword());
                                        Membership mem = new Membership(docid.getMembershipid(), docid.getSurname(), docid.getOthernames(),
                                                docid.getInstitutionid(), docid.getContactaddress(), docid.getPhoneno(), docid.getMemershiprights(),
                                                password, "ACTIVE", util.getTodaysdate());
                                        sess.newEntry(mem);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails(gson.toJson(mem));
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Institutionid " + docid.getInstitutionid() + " does not exist");
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    MembershipRequest dummy = new MembershipRequest("a@b.com", "Ayua", "Sefa", "institutionid",
                                            "contact address", "2347011111111", "User", "password");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(dummy));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("ENCRYPTTEXT")) {
                            if (sb.toString().length() > 0) {
                                String encrypted = util.getMD5(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(encrypted);
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("UPDATEDOCUMENT")) {
                            if (sb.toString().length() > 0) {
                                Document docid = (Document) gson.fromJson(sb.toString(), Document.class);
                                if (docid != null) {
                                    Document inst = (Document) sess.getSingleObject(Document.class, docid.getId());
                                    Institutions inst2 = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstId());
                                    if (inst != null && inst2 != null) {
                                        sess.updateRecord(docid);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails("");
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Invalid document ID " + docid.getId() + " or institution id " + docid.getInstId());
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    Document doc = new Document(1l, 1l, 1l, "docName", "author", "title", "2020/2021", "2020", "DEGREE", "Ayua",
                                            "abstract", "topic", "docname", "publisherPlace", "volume", "issueNo", "publishedYear", "publisher",
                                            "edition", "isbn", "pages", "filename", "", 1l, "2020-01-02", "12:00", "", "username");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("UPDATEINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                Institutions docid = (Institutions) gson.fromJson(sb.toString(), Institutions.class);
                                if (docid != null) {
                                    Institutions inst = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstitutionid());
                                    Institutiontype inst2 = (Institutiontype) sess.getSingleObject(Institutiontype.class, docid.getInstitutiontype());
                                    if (inst != null && inst2 != null) {
                                        sess.updateRecord(docid);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails("");
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Invalid institutionid " + docid.getInstitutionid() + " or institution type " + docid.getInstitutiontype());
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    Institutions doc = new Institutions("institutionid", "institutiontype", "institutionname", "physicaladdress",
                                            "contactname", "contactphone", "contactemailaddress", 2020, "dateadded", "username");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("UPDATEINSTITUTIONTYPE")) {
                            if (sb.toString().length() > 0) {
                                Institutiontype docid = (Institutiontype) gson.fromJson(sb.toString(), Institutiontype.class);
                                if (docid != null) {
                                    Institutiontype inst2 = (Institutiontype) sess.getSingleObject(Institutiontype.class, docid.getTypeid());
                                    if (inst2 != null) {
                                        sess.updateRecord(docid);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails("");
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Invalid institution type " + docid.getTypeid());
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    Institutiontype doc = new Institutiontype("typeid", "typename", "description", 700d, 365, "dateadded", "username");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("UPDATEMEMBERSHIP")) {
                            if (sb.toString().length() > 0) {
                                Membership docid = (Membership) gson.fromJson(sb.toString(), Membership.class);
                                if (docid != null) {
                                    Membership inst = (Membership) sess.getSingleObject(Membership.class, docid.getMembershipid());
                                    Institutions inst2 = (Institutions) sess.getSingleObject(Institutions.class, docid.getInstitutionid());
                                    if (inst != null && inst2 != null) {
                                        if (!docid.getPassword().equals(inst.getPassword())) {
                                            docid.setPassword(util.getMD5(docid.getPassword()));
                                        }
                                        sess.updateRecord(docid);
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDetails("");
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDetails("Invalid Membership ID " + docid.getMembershipid() + " or institution id " + docid.getInstitutionid());
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    Membership doc = new Membership("a@b.com", "surname", "othernames", "institutionid", "contactaddress", "phoneno",
                                            "memershiprights", "password", "membershipstatus", "dateregistered");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODEBATCH")) {
                            if (sb.toString().length() > 0) {
                                Activationcodebatches docid = (Activationcodebatches) sess.getSingleObject(Activationcodebatches.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Activation Code Batch " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODE")) {
                            if (sb.toString().length() > 0) {
                                Activationcodes docid = (Activationcodes) sess.getSingleObject(Activationcodes.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Activation Code " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPIKEY")) {
                            if (sb.toString().length() > 0) {
                                Apiauthentication docid = (Apiauthentication) sess.getSingleObject(Apiauthentication.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("API Key " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALL")) {
                            if (sb.toString().length() > 0) {
                                Apicalls docid = (Apicalls) sess.getSingleObject(Apicalls.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("API Call ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENT")) {
                            if (sb.toString().length() > 0) {
                                Document docid = (Document) sess.getSingleObject(Document.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Document ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                Institutions docid = (Institutions) sess.getSingleObject(Institutions.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Institution ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETINSTITUTIONTYPE")) {
                            if (sb.toString().length() > 0) {
                                Institutiontype docid = (Institutiontype) sess.getSingleObject(Institutiontype.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Institution Type ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETMEMBERSHIP")) {
                            if (sb.toString().length() > 0) {
                                Membership docid = (Membership) sess.getSingleObject(Membership.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Membership ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETPAYMENTNOTIFICATION")) {
                            if (sb.toString().length() > 0) {
                                Paymentnotification docid = (Paymentnotification) sess.getSingleObject(Paymentnotification.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Payment Notification ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETPAYMENTREFERENCE")) {
                            if (sb.toString().length() > 0) {
                                Paymentreference docid = (Paymentreference) sess.getSingleObject(Paymentreference.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Payment Reference ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETPAYMENT")) {
                            if (sb.toString().length() > 0) {
                                Payments docid = (Payments) sess.getSingleObject(Payments.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Payment ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETPAYMENTTRASH")) {
                            if (sb.toString().length() > 0) {
                                Paymenttrash docid = (Paymenttrash) sess.getSingleObject(Paymenttrash.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Payment ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETWEBPAYMENT")) {
                            if (sb.toString().length() > 0) {
                                Webpayattempts docid = (Webpayattempts) sess.getSingleObject(Webpayattempts.class, sb.toString());
                                if (docid != null) {
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(docid));
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Webpayment ID " + sb.toString() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPIKEYSBYIP")) {
                            if (sb.toString().length() > 0) {
                                List<Apiauthentication> keys = sess.getApiauthenticationByAllowedIP(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPIKEYSBYEMAIL")) {
                            if (sb.toString().length() > 0) {
                                List<Apiauthentication> keys = sess.getApiauthenticationByContactemail(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPIKEYSBYSTATUS")) {
                            if (sb.toString().length() > 0) {
                                List<Apiauthentication> keys = sess.getApiauthenticationByExpiryStatus(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPIKEYSBYDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Apiauthentication> list = sess.getApiauthenticationByDateaddedrange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALLSBYAPIKEY")) {
                            if (sb.toString().length() > 0) {
                                List<Apicalls> keys = sess.getApicallsByApikey(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALLSBYSERVICE")) {
                            if (sb.toString().length() > 0) {
                                List<Apicalls> keys = sess.getApicallsByServicecalled(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALLSBYIP")) {
                            if (sb.toString().length() > 0) {
                                List<Apicalls> keys = sess.getApicallsByIPaddress(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALLSBYDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Apicalls> list = sess.getApicallsByDatecalledrange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETAPICALLSBYAPIKEYANDDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                IdAndDaterangeRequest docid = (IdAndDaterangeRequest) gson.fromJson(sb.toString(), IdAndDaterangeRequest.class);
                                if (docid != null) {
                                    List<Apicalls> list = sess.getApicallsByApikeyAndDatecalledrange(docid.getId(), docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    IdAndDaterangeRequest doc = new IdAndDaterangeRequest("apikey", "2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETALLIPSINAPICALLS")) {
                            List<String> keys = sess.getApicallsAllIpaddresses();
                            status.setResponseCode("SUCCESS");
                            status.setResponseDetails(gson.toJson(keys));
                        } else if (operation.equalsIgnoreCase("GETALLINSTITUTIONTYPES")) {
                            List<Institutiontype> keys = sess.getAllInstitutiontypes();
                            status.setResponseCode("SUCCESS");
                            status.setResponseDetails(gson.toJson(keys));
                        } else if (operation.equalsIgnoreCase("GETINSTITUTIONSBYTYPE")) {
                            if (sb.toString().length() > 0) {
                                List<Institutions> keys = sess.getInstitutionsByType(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETINSTITUTIONSBYDATEREGISTERED")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Institutions> list = sess.getInstitutionsByDateregisteredrange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODEBATCHESBYINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                List<Activationcodebatches> keys = sess.getActivationcodebatchesByInstitution(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("BETACTIVATIONCODEBATCHESBYDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Activationcodebatches> list = sess.getActivationcodebatchesByDaterange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODESBYDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Activationcodes> list = sess.getActivationcodesByDategeneratedrange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODESBYBATCHIDANDSTATUS")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    List<Activationcodes> list = sess.getActivationcodesByBatchAndStatus(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("batchid", "activationstatus{INACTIVE|ACTIVE|EXPIRED}");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETACTIVATIONCODESBYUSERANDSTATUS")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    List<Activationcodes> list = sess.getActivationcodesByUser(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("userid", "activationstatus{INACTIVE|ACTIVE|EXPIRED}");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETMEMBERSBYINSTITUTIONANDSTATUS")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    List<Membership> list = sess.getMembershipByInstitution(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("institutionid|ALL", "status{ALL|INACTIVE|ACTIVE}");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETMEMBERSHIPBYRIGHT")) {
                            if (sb.toString().length() > 0) {
                                List<Membership> keys = sess.getMembershipByMembershipright(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETMEMBERSHIPBYRIGHTANDINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    List<Membership> list = sess.getMembershipByRightAndInstitutionid(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("right", "institutionid");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETMEMBERSREGISTEREDBYDATE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Membership> list = sess.getMembershipByDateregisteredrange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTSBYUSERID")) {
                            if (sb.toString().length() > 0) {
                                List<Document> keys = sess.getDocumentByUsername(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTBYPOSTDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                DaterangeRequest docid = (DaterangeRequest) gson.fromJson(sb.toString(), DaterangeRequest.class);
                                if (docid != null) {
                                    List<Document> list = sess.getDocumentByPostdaterange(docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    DaterangeRequest doc = new DaterangeRequest("2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTBYUSERIDANDPOSTDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                IdAndDaterangeRequest docid = (IdAndDaterangeRequest) gson.fromJson(sb.toString(), IdAndDaterangeRequest.class);
                                if (docid != null) {
                                    List<Document> list = sess.getDocumentByUsernameAndPostdaterange(docid.getId(), docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    IdAndDaterangeRequest doc = new IdAndDaterangeRequest("userid", "2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTBYINSTITUTION")) {
                            if (sb.toString().length() > 0) {
                                List<Document> keys = sess.getDocumentByInstitutionid(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTBYINSTITUTIONANDPOSTDATERANGE")) {
                            if (sb.toString().length() > 0) {
                                IdAndDaterangeRequest docid = (IdAndDaterangeRequest) gson.fromJson(sb.toString(), IdAndDaterangeRequest.class);
                                if (docid != null) {
                                    List<Document> list = sess.getDocumentByInstitutionidAndPostdaterange(docid.getId(), docid.getDatefrom(), docid.getDateto());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    IdAndDaterangeRequest doc = new IdAndDaterangeRequest("institutionid", "2020-01-01", "2020-03-01");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTSBYTITLE")) {
                            if (sb.toString().length() > 0) {
                                List<Document> keys = sess.getDocumentByTitle(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTSBYAUTHOR")) {
                            if (sb.toString().length() > 0) {
                                List<Document> keys = sess.getDocumentByAuthor(sb.toString());
                                status.setResponseCode("SUCCESS");
                                status.setResponseDetails(gson.toJson(keys));
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTTITLES")) {
                            List<String> keys = sess.getDocumentAllTitles();
                            status.setResponseCode("SUCCESS");
                            status.setResponseDetails(gson.toJson(keys));
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTAUTHORS")) {
                            List<String> keys = sess.getDocumentAllAuthors();
                            status.setResponseCode("SUCCESS");
                            status.setResponseDetails(gson.toJson(keys));
                        } else if (operation.equalsIgnoreCase("GETDOCUMENTSBYYEARRANGE")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    List<Document> list = sess.getDocumentByPublisheddaterange(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("2000", "2020");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GENERATEACTIVATIONCODESFORBATCHANDUSERID")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    Activationcodes list = sess.generateActivationCode(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("batchid", "userid");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("ASSIGNACTIVATIONCODETOUSER")) {
                            if (sb.toString().length() > 0) {
                                Key1Key2Request docid = (Key1Key2Request) gson.fromJson(sb.toString(), Key1Key2Request.class);
                                if (docid != null) {
                                    boolean list = sess.assignUserToActivationCode(docid.getKey1(), docid.getKey2());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(list));
                                } else {
                                    status.setResponseCode("ERROR");
                                    Key1Key2Request doc = new Key1Key2Request("userid", "activationcode");
                                    status.setResponseDetails("Wrong data format, should be: " + gson.toJson(doc));
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("GENERATEPAYMENTREFERENCE")) {
                            if (sb.toString().length() > 0) {
                                Activationcodes act = (Activationcodes) sess.getSingleObject(Activationcodes.class, sb.toString());
                                if (act != null) {
                                    Paymentreference keys = sess.generatePaymentReference(sb.toString());
                                    status.setResponseCode("SUCCESS");
                                    status.setResponseDetails(gson.toJson(keys));
                                } else {
                                    Paymentreference keys = sess.generatePaymentReference(sb.toString());
                                    status.setResponseCode("ERROR");
                                    status.setResponseDetails("Activation Code " + sb.toString() + " does not exist!");
                                }

                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDetails("Data not provided");
                            }
                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else if (operation.equalsIgnoreCase("")) {

                        } else {
                            status.setResponseCode("ERROR");
                            status.setResponseDetails("Operation name " + operation + " is not valid! Please follow the documentation for list of valid operations.");
                        }

                    } else {
                        status.setResponseCode("ERROR");
                        status.setResponseDetails("No operation provided in the header");
                    }

                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();

                } else {
                    status.setResponseCode("ERROR");
                    status.setResponseDetails("Invalid Authentication token provided");
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
            } else {
                status.setResponseCode("ERROR");
                status.setResponseDetails("Authentication token not provided");
                response.getOutputStream().print(gson.toJson(status));
                response.getOutputStream().flush();
            }

        } catch (JsonSyntaxException | IOException ex) {
            status.setResponseCode("ERROR");
            status.setResponseDetails(ex.getMessage());
            response.getOutputStream().print(gson.toJson(status));
            response.getOutputStream().flush();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
