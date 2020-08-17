/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avcnu.plagiarism.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tika.exception.TikaException;
import org.avcnu.plagiarism.sessions.MainSessionLocal;
import org.xml.sax.SAXException;

/**
 *
 * @author eaglescan
 */
public class CompareDocuments extends HttpServlet {

    @EJB
    private MainSessionLocal sess;

    int maxFileSize = 10000 * 1024;
    int maxMemSize = 10000 * 1024;
    String ext1;
    String doc1;
    String fileName1;
    String ext2;
    String doc2;
    String fileName2;
    String plagPercent;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
        try {
            sc.setAttribute("ext1", null);
            sc.setAttribute("doc1", null);
            sc.setAttribute("fileName1", null);
            sc.setAttribute("ext2", null);
            sc.setAttribute("doc2", null);
            sc.setAttribute("fileName1", null);
            sc.setAttribute("plagPercent", null);
        } catch (Exception n) {
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);
                factory.setRepository(new File("/temp"));

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(maxFileSize);
                List<FileItem> multiparts = upload.parseRequest(request);
                int i = 0;
                String altText1 = null;
                String altText2 = null;
                for (FileItem item : multiparts) {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldName = item.getFieldName();
                        if (fieldName.equalsIgnoreCase("plagPercent")) {
                            plagPercent = item.getString();
                        }
                        if (fieldName.equalsIgnoreCase("altText1")) {
                            altText1 = item.getString();
                        }
                        if (fieldName.equalsIgnoreCase("altText2")) {
                            altText2 = item.getString();
                        }

                        // ... (do your job here)
                    } else {
                        // Process form file field (input type="file").
                        InputStream fileContent = item.getInputStream();
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        try {
                            if (fileName != null && fileName.trim().length() > 0) {
                                String doc = sess.extractText(fileContent);
                                String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                                if (fieldName.equalsIgnoreCase("file1")) {
                                    doc1 = doc;
                                    ext1 = ext;
                                    fileName1 = fileName;
                                }
                                if (fieldName.equalsIgnoreCase("file2")) {
                                    doc2 = doc;
                                    ext2 = ext;
                                    fileName2 = fileName;
                                }
                            }
                        } catch (IOException | TikaException | SAXException j) {
                        }
                    }
                }
                if (doc1 == null) {
                    if (altText1 != null) {
                        doc1 = altText1;
                    } else {
                        sc.setAttribute("message", fileName1 + " is " + ext1 + "\n but documents must be supported format, please use the following formats: .pdf,.doc, .docx, .xls, .xlsx, .ppt, .pptx");
                    }
                }
                if (doc2 == null) {
                    if (altText2 != null) {
                        doc2 = altText2;
                    } else {
                        sc.setAttribute("message", fileName2 + " is " + ext2 + "\n but documents must be supported format, please use the following formats: .pdf,.doc, .docx, .xls, .xlsx, .ppt, .pptx");
                    }
                }

            } catch (FileUploadException | IOException ex) {
                sc.setAttribute("message", "File Upload Failed due to " + ex);
            }

        } else {
            sc.setAttribute("message", "Sorry this Servlet only handles file upload request");
        }
        try {
            sc.setAttribute("ext1", ext1);
            sc.setAttribute("doc1", doc1);
            sc.setAttribute("fileName1", fileName1);
            sc.setAttribute("ext2", ext1);
            sc.setAttribute("doc2", doc1);
            sc.setAttribute("fileName2", fileName1);
            sc.setAttribute("plagPercent", plagPercent);
        } catch (Exception n) {
        }
        //response.sendRedirect("plagChecker.jsp");
        Random rand = new Random();
        String ran = Long.toHexString(rand.nextLong());
        RequestDispatcher rd = request.getRequestDispatcher("compareDocuments2.jsp?a1=" + ran);
        rd.forward(request, response);
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
