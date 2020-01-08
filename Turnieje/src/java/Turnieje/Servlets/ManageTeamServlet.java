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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.UserRepository;

/**
 *
 * @author Daniel-PC
 */
@WebServlet(name = "ManageTeamServlet", urlPatterns = {"/ManageTeam"})
public class ManageTeamServlet extends HttpServlet {
    
    ITeamRepository teamRepository;
    IUserRepository userRepository;
    
    //<editor-fold defaultstate="expanded" desc="init()">
    @Override
    public void init()
    {
        teamRepository = new TeamRepository();
        userRepository = new UserRepository();
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
            
            
            int managedTeamID=0;
            
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("managedTeamID")) {
                        managedTeamID = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            
            //pobieram wprowadzona nazwe druzyny i doknuje zmiany gdy pole nie bylo puste
            String managedTeam = request.getParameter("teamName");
            if(managedTeam.compareTo("")!=0)
            {
                Cookie cookie = new Cookie("managedTeamName", managedTeam);
                response.addCookie(cookie);
            }
            
            //pobieram wprowadzonego uzytkownika
            String userToAdd = request.getParameter("userToAdd");
            if(userToAdd.compareTo("")!=0)
            {
                //Team toEdit = teamRepository.getById(managedTeamID);
                //newPlayer = userRepository.getByEmail(userToAdd);
                //toEdit.addPlayer(newPlayer);
            }
            
            String userToAddID = request.getParameter("userToAddID");
            if(userToAdd.compareTo("")!=0)
            {
                int userToAddIDInt = Integer.parseInt(userToAddID);
                //Team toEdit = teamRepository.getById(managedTeamID);
                //newPlayer = userRepository.getById(userToAddIDInt);
                //toEdit.addPlayer(newPlayer);
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageTeamServlet</title>");            
            out.println("</head>");
            out.println("<body><center>");
            out.println("<h1>Pomyślna zmiana nazwy na: "+managedTeam+"</h1>");
            out.print("<script>\n" +
"setTimeout(function() {\n" +
<<<<<<< Updated upstream
"  location.replace(\"/Turnieje/ManageTeam.jsp\")\n" +
=======
"  location.replace(\"/Turnieje/ManageTeam.jsp?teamNameGet="+managedTeam+"\")\n" +
>>>>>>> Stashed changes
"}, 2000);\n" +
"</script>");
            
            out.println("</center></body>");
            out.println("</html>");
        }
        
            //Team toEdit = teamRepository.getById(managedTeamID);
            //toEdit.setName(teamName);
            //teamRepository.update(toEdit);
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
