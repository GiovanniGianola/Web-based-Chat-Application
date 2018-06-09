/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giano
 */
@WebServlet(name = "LoginGateway", urlPatterns = {"/LoginGateway"})
public class LoginGateway extends HttpServlet {

    ClassController CL;

    @Override
    public void init() throws ServletException {
        super.init();
        CL = new ClassController();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String result = "";
        response.setContentType("text/html");

        switch (request.getParameter("code")) {
            case "updateAllOnlineListRequest": {
                //result = CL.checkAllConnections(request);
                out.println(result);
                break;
            }
            default:
                System.out.println("Invalid Code");
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String result = "";
        response.setContentType("text/html");
        System.out.println(request.getParameter("code"));

        switch (request.getParameter("code")) {
            case "LogInRequest": {
                result = CL.logInRequest(request);
                out.println(result);
                break;
            }
            case "SignUpRequest": {
                result = CL.signUpRequest(request);
                out.println(result);
                break;
            }
            default:
                System.out.println("Invalid Code");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login Gateway";
    }// </editor-fold>

}
