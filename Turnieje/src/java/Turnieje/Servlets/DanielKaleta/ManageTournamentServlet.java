/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets.DanielKaleta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 * Servlet responsible for editing the tournament.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "ManageTournamentServlet", urlPatterns = {"/ManageTournament"})
public class ManageTournamentServlet extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    ITournamentRepository tournamentRepository;
    ITeamRepository teamRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        teamRepository = repositoryProvider.getTeamRepository();
        tournamentRepository = repositoryProvider.getTournamentRepository();
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

        String JSONString = request.getParameter("JSONFromCreateTournament");
        JSONObject JSON = new JSONObject(JSONString);

        String tournamentName = JSON.getString("name");
        String type = JSON.getString("type").replaceAll(" ", "_");
        String discipline = JSON.getString("discipline").replaceAll(" ", "_");
        String teamSize = JSON.getString("teamSize");
        
        HttpSession session = request.getSession(true);
        Tournament toEdit = (Tournament) session.getAttribute("tournamentToEdit");
        
        toEdit.setName(tournamentName);
        toEdit.setMode(TournamentMode.NONE);
        
        
        tournamentRepository.update(toEdit);
        
        
        

        JSONArray teams = JSON.getJSONArray("teamsToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for (int i = 0; i < teams.length(); i++) 
        {
            System.out.print(teams.getString(i));
        }

        //Team toEdit = teamRepository.getById(managedTeamID);
        //toEdit.setName(teamName);
        //teamRepository.update(toEdit);

        response.sendRedirect("/Turnieje/TournamentCreateManage/TournamentEdited.jsp?tournamentName="+tournamentName);
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
