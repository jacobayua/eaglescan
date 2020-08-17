/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets.apis;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.avcnu.plagiarism.sessions.MainSessionLocal;
import org.avcnu.plagiarism.util.SimilarDocuments;
import org.avcnu.plagiarism.util.Utilities;

/**
 *
 * @author eaglescan
 */
public class CompareDocument extends HttpServlet {

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
        CompareDocumentResponse status = new CompareDocumentResponse();
        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }

            String authentication = request.getHeader("authentication");
            if (authentication != null) {
                Map<String, String> map = util.getAuthentications();
                if (authentication.equals(map.get("CompareDocument"))) {
                    if (sb.toString().length() > 0) {
                        CompareDocumentRequest docid = (CompareDocumentRequest) gson.fromJson(sb.toString(), CompareDocumentRequest.class);
                        if (docid != null) {
                            if (docid.getOriginalDocument().length() > 0 && docid.getSuspiciousDocument().length() > 0 && docid.getPercentage() > 0) {

                                /////////////////
                                try {
                                    SimilarDocuments statusx = sess.compareDocuments(docid.getOriginalDocument(), docid.getSuspiciousDocument(), docid.getPercentage());
                                    if (statusx != null) {
                                        status.setResponseCode("SUCCESS");
                                        status.setResponseDescription("");
                                        status.setData(statusx);
                                    } else {
                                        status.setResponseCode("ERROR");
                                        status.setResponseDescription("No similarity detected");
                                    }

                                } catch (Exception e) {
                                    status.setResponseCode("ERROR");
                                    status.setResponseDescription("Unknown Error while Checking for Similarity, kindly resubmit");

                                }
/////////////////////
                            } else {
                                status.setResponseCode("ERROR");
                                status.setResponseDescription("Either no document submitted or No comparing document or similarity percantage is less than 0");
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
