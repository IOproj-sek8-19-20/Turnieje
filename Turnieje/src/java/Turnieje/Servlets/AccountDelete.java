/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;

/**
 *
 * @author mariu
 */
@WebServlet(name = "AccountDelete", urlPatterns = {"/Login"})
public class AccountDelete extends HttpServlet {
ITeamRepository teamRepository;

    //<editor-fold defaultstate="expanded" desc="init()">
    @Override
    public void init() {
        //teamRepository = new TeamRepository();
    }
    //</editor-fold>
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
         response.setContentType("text/html;charset=UTF-8");

        String JSONString = request.getParameter("JSON");
        JSONObject JSON = new JSONObject(JSONString);
        String firstName;
        String lastName;
        String email;
        String code;
        String password;
        String newPassword;
        String type = JSON.getString("type");
        
        if(type.equals("firstName"))
        {
            firstName = JSON.getString("firstName");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("firstName", firstName);
            session.setAttribute("acive","YES"); 
            response.sendRedirect("FirstNameChanged.jsp");
        }
        else
        if(type.equals("lastName"))
        {
            lastName = JSON.getString("lastName");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("lastName", lastName);
            session.setAttribute("acive","YES"); 
            response.sendRedirect("LastNameChanged.jsp");
        }
        if(type.equals("email"))
        {
            email = JSON.getString("email");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("email", email);
            session.setAttribute("acive","YES"); 
            response.sendRedirect("EmailCode.jsp");
        }
        if(type.equals("code"))
        {
            firstName = JSON.getString("firstName");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("firstName", firstName);
            session.setAttribute("acive","YES"); 
            response.sendRedirect("FirstNameChanged.jsp");

        }
        if(type.equals("password"))
        {
            password = JSON.getString("password");
            newPassword = JSON.getString("newPassword");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("acive","YES"); 
            if(password.equals("password"))//sprawdzenie poprawnosci hasla
            {
                response.sendRedirect("PasswordChanged.jsp");
            }
            else
            {
                response.sendRedirect("BadPassword.jsp");
            }

        }
        if(type.equals("delete"))
        {
            password = JSON.getString("password");
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("acive","YES"); 
            if(password.equals("password"))//sprawdzenie poprawnosci hasla
            {
                response.sendRedirect("AreYouSure.jsp");
            }
            else
            {
                response.sendRedirect("BadPasswordToDelete.jsp");
            }

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