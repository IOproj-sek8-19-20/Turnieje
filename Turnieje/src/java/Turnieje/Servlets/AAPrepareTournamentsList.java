/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Daniel-Laptop
 */
@WebServlet(name = "PrepareTournamentsList", urlPatterns = {"/PrepareTournamentsList"})
public class AAPrepareTournamentsList extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    ITournamentRepository tournamentRepository;
    IUserRepository userRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        tournamentRepository = repositoryProvider.getTournamentRepository();
        userRepository = repositoryProvider.getUserRepository();
    }

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

        HttpSession session = request.getSession(true);
        boolean onlyMine = Boolean.parseBoolean(request.getParameter("onlyMine"));
        
        Set<Tournament> allTournaments = tournamentRepository.getAll();
        Set<String> allTournamentsNames = new HashSet<>();
        
        if(onlyMine==true)
        {
            String adminEmail = (String) session.getAttribute("loggedUser");
            User admin = userRepository.getByEmail(adminEmail);
            for(Tournament tournament: allTournaments)
            {
                if(tournament.getAdmin().id == admin.getId().id)
                {
                    allTournamentsNames.add(tournament.getName());
                }
            }
            session.setAttribute("tournamentsToShow", allTournamentsNames);
            response.sendRedirect("/Turnieje/ShowTournaments.jsp?onlyMine=true");
        }
        else
        {
           for(Tournament tournament: allTournaments)
           {
               allTournamentsNames.add(tournament.getName());
           }
            session.setAttribute("tournamentsToShow", allTournamentsNames);
            response.sendRedirect("/Turnieje/ShowTournaments.jsp");
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
