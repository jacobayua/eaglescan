/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.tika.exception.TikaException;
import org.avcnu.plagiarism.entities.Document;
import org.avcnu.plagiarism.sessions.MainSessionLocal;
import org.avcnu.plagiarism.util.IndexedDocument;
import org.avcnu.plagiarism.util.Utilities;
import org.xml.sax.SAXException;

/**
 *
 * @author eaglescan
 */
public class IndexDocument extends HttpServlet {

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
        IndexDocumentResponse status = new IndexDocumentResponse();

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
                    if (sb.toString().length() > 0) {
                        IndexDocumentRequest docid = (IndexDocumentRequest) gson.fromJson(sb.toString(), IndexDocumentRequest.class);
                        if (docid != null) {
                            if (docid.getDocumentid() != null) {
                                Document doc = (Document) sess.getSingleObject(Document.class, docid.getDocumentid());
                                if (doc != null) {
                                    String documentLocation = util.REPOSITORY_LOCATION + "/" + doc.getInstId() + "/" + doc.getFilename();
                                    try {
                                        InputStream stream = new FileInputStream(new File(documentLocation));
                                        String text = sess.extractText(stream);
                                        if (text != null && text.length() > 0) {
                                            try {
                                                //String onlyName = doc.getFilename().substring(0, doc.getFilename().lastIndexOf("."));
                                                //String indexedFile = util.REPOSITORY_INDEXED_LOCATION + "/" + doc.getInstId() + onlyName + ".txt";
                                                IndexedDocument index = new IndexedDocument(docid.getDocumentid() + "", doc.getDocId(), doc.getDocType(), doc.getDocName(),
                                                        doc.getAuthor(), doc.getTitle(), doc.getDsession(), doc.getDyear(), doc.getDegree(), doc.getSupervisor(),
                                                        doc.getAbstract1(), doc.getTopic(), doc.getName(), doc.getPublisherPlace(), doc.getVolume(), doc.getIssueNo(),
                                                        doc.getPublishedYear(), doc.getPublisher(), doc.getEdition(), doc.getIsbn(), doc.getPages(),
                                                        doc.getFilename(), doc.getRemark(), doc.getInstId(), doc.getPostDate().toString(), doc.getPostTime(), doc.getIpAddr(),
                                                        doc.getUserId(), text);
                                                //Files.writeString(Path.of(indexedFile), gson.toJson(index), Charset.forName("UTF-8"));

                                                //push to Solr
                                                SolrClient client = util.getSolrClient();
                                                final UpdateResponse response2 = client.addBean("localRepository", index);
                                                client.commit("eaglescandb");

                                                status.setResponseCode("SUCCESS");
                                            } catch (Exception e) {
                                                status.setResponseCode("ERROR");
                                                status.setResponseDescription("Unknown Error while Indexing document " + documentLocation + ", kindly delete and resubmit");
                                                // delete from repository
                                                try {
                                                    File file = new File(documentLocation);
                                                    Files.deleteIfExists(file.toPath());
                                                    sess.deleteObject(doc);
                                                } catch (IOException sa) {
                                                }
                                            }

                                        } else {
                                            status.setResponseCode("ERROR");
                                            status.setResponseDescription("Document " + documentLocation + " can not be extracted! Kindly delete and resubmit");
                                            //delete file from repository
                                            try {
                                                File file = new File(documentLocation);
                                                Files.deleteIfExists(file.toPath());
                                                sess.deleteObject(doc);
                                            } catch (IOException sa) {
                                            }
                                        }
                                    } catch (IOException | TikaException | SAXException j) {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDescription("Document " + documentLocation + " not found!");
                                        //delete file from repository
                                        try {
                                            File file = new File(documentLocation);
                                            Files.deleteIfExists(file.toPath());
                                            sess.deleteObject(doc);
                                        } catch (IOException sa) {
                                        }
                                    }
                                } else {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDescription("Document ID " + docid.getDocumentid() + " not found!");
                                }
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDescription("Incomplete data submitted");
                            }
                        } else {
                            status.setResponseCode("ERROR");
                            status.setResponseDescription("Wrong data format");
                        }
                    } else {
                        status.setResponseCode("ERROR");
                        status.setResponseDescription("Data not provided");
                    }
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();

                } else {
                    status.setResponseCode("ERROR");
                    status.setResponseDescription("Invalid Authentication token provided");
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
            } else {
                status.setResponseCode("ERROR");
                status.setResponseDescription("Authentication token not provided");
                response.getOutputStream().print(gson.toJson(status));
                response.getOutputStream().flush();
            }

        } catch (JsonSyntaxException | IOException ex) {
            status.setResponseCode("ERROR");
            status.setResponseDescription(ex.getMessage());
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
