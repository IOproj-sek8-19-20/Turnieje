/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;

/**
 * Servlets responsible for creating the team. 
 * 
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "CreateTeamServlet", urlPatterns = {"/CreateTeam"})
public class CreateTeamServlet extends HttpServlet {
    
    ITeamRepository teamRepository;
    
    //<editor-fold defaultstate="expanded" desc="init()">
    @Override
    public void init()
    {
        teamRepository = new TeamRepository();
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String teamName = request.getParameter("teamName");

            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateTeamServlet</title>");            
            out.println("</head>");
            out.println("<body><center>");
            out.println("<h1> Druzyna "+teamName+" dodana.</h1>");
            out.print("<script>\n" +
"setTimeout(function() {\n" +
<<<<<<< Updated upstream
"  location.replace(\"/Turnieje/ManageTeam.jsp\")\n" +
=======
"  location.replace(\"/Turnieje/ManageTeam.jsp?teamNameGet="+teamName+"\")\n" +
>>>>>>> Stashed changes
"}, 2000);\n" +
"</script>");
            out.println("</center></body>");
            out.println("</html>");
            
            Cookie cookieName = new Cookie("managedTeamName", teamName);
            response.addCookie(cookieName);
            Cookie cookieID = new Cookie("managedTeamID", "1");
            response.addCookie(cookieID);
            
            //response.sendRedirect("ManageTeam");
            
            //Team toAdd = new Team();
            //toAdd.setName(teamName);
            //teamRepository.add(toAdd);
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
