/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamInTournament;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.IDisciplineRepository;
import pl.polsl.aei.io.turnieje.model.repository.IMatchRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
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
    IMatchRepository matchRepository;
    IUserRepository userRepository;
    IDisciplineRepository disciplineRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        teamRepository = repositoryProvider.getTeamRepository();
        tournamentRepository = repositoryProvider.getTournamentRepository();
        matchRepository = repositoryProvider.getMatchRepository();
        userRepository = repositoryProvider.getUserRepository();
        disciplineRepository = repositoryProvider.getDisciplineRepository();
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
        
        Date date = new Date();
        
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
        
        String adminEmail = (String) session.getAttribute("loggedUser");
        User admin = userRepository.getByEmail(adminEmail);
        
        Date startDate=null;
        Date endDate=null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
        } catch (ParseException ex) {
            Logger.getLogger(AACreateTournamentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Discipline tournamentDiscipline = disciplineRepository.getByName(discipline);
        Tournament newTournament = new Tournament();
        newTournament.setName(tournamentName);
        newTournament.setTeamSize(Integer.parseInt(teamSize));
        newTournament.setAdmin(admin);
        newTournament.setDiscipline(tournamentDiscipline);
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
        
        Tournament newTournamentWithId = tournamentRepository.getByName(newTournament.getName());

        Set <Team> temp = new HashSet<>();
        JSONArray teams = JSON.getJSONArray("teamsToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for (int i = 0; i < teams.length(); i++) 
        {
            System.out.print(teams.getString(i));
            Team toAddTeam = teamRepository.getByName(teams.getString(i));
            TeamInTournament toAdd = new TeamInTournament();
            toAdd.tourId = newTournamentWithId.id;
            toAdd.eliminated = false;
            toAdd.joinDate = date;
            toAdd.points = 0;
            toAdd.teamId = toAddTeam.id;
            toAdd.groupNr = 1;
            newTournamentWithId.addTeam(toAdd);
            temp.add(toAddTeam);
        }
        
        try
        {
            tournamentRepository.update(newTournamentWithId);
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        
        
        /*try{
            temp = teamRepository.getByTournament(newTournamentWithId);
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }*/
        
        List<Team> teamsInTournament = new ArrayList<>(temp);
        
        for(int i=0; i<teamsInTournament.size();i+=2)
        {
            Match toAdd = new Match();
            //No z tą datą to tak jeszcze do przemyślenia
            toAdd.setDate(date);
            toAdd.setFinished(false);
            toAdd.setTourId(newTournamentWithId.id);
            toAdd.setTeamId((i+1), teamsInTournament.get(i).id);
            toAdd.setTeamId((i+2), teamsInTournament.get(i+1).id);
            matchRepository.addMatch(toAdd);
        }
        
        session.setAttribute("tournamentToEdit", newTournamentWithId);

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
