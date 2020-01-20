/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.Discipline;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 * Servlet responsible for creating the tournament.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "CreateTournamentServlet", urlPatterns = {"/CreateTournament"})
public class AACreateTournamentServlet extends HttpServlet {
    
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
        
        HttpSession session = request.getSession(true);

        String JSONString = request.getParameter("JSONFromCreateTournament");
        JSONObject JSON = new JSONObject(JSONString);
        
        String tournamentName = null;
        String type = null;
        String startDateString = null;
        String endDateString = null;
        String discipline = null;
        String teamSize = null;
        
        try
        {
            tournamentName = JSON.getString("name");
            type = JSON.getString("type").replaceAll(" ", "_");
            startDateString = JSON.getString("startDate");
            endDateString = JSON.getString("endDate");
            discipline = JSON.getString("discipline").replaceAll(" ", "_");
            teamSize = JSON.getString("teamSize");
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        
        //sprawdzenie czy bangla
        System.out.print(tournamentName);
        System.out.print(type);
        System.out.print(startDateString);
        System.out.print(endDateString);
        System.out.print(discipline);
        System.out.print(teamSize);
        
        User admin = (User) session.getAttribute("loggedUser");
        //rozwiazanie tymczasowe
        /*LocalDate localDate = LocalDate.now();
        Date startDate= java.sql.Date.valueOf( localDate );
        Date endDate=java.sql.Date.valueOf( localDate );*/
        
        Date startDate=null;
        Date endDate=null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
        } catch (ParseException ex) {
            Logger.getLogger(AACreateTournamentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Tournament newTournament = new Tournament();
        newTournament.setName(tournamentName);
        newTournament.setTeamSize(Integer.parseInt(teamSize));
        newTournament.setAdmin(admin);
        newTournament.setDiscipline(Discipline.valueOf(discipline));
        newTournament.setMode(TournamentMode.valueOf(type));
        newTournament.setFinished(false);
        newTournament.setStartingDate(startDate);
        newTournament.setEndingDate(endDate);
        
        try
        {
            tournamentRepository.add(newTournament);
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        

        JSONArray teams = JSON.getJSONArray("teamsToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for (int i = 0; i < teams.length(); i++) 
        {
            System.out.print(teams.getString(i));
        }
        
        session.setAttribute("tournamentToEdit", newTournament);

        response.sendRedirect("/Turnieje/TournamentCreateManage/TournamentCreated.jsp?tournamentName=" + tournamentName);

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