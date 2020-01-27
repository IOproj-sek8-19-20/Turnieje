
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
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.repository.IMatchRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.MatchRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Wojtek Wos
 * @verion 1.0.0
 */
@WebServlet(name = "TournamentScheduleServlet", urlPatterns = {"/TournamentSchedule"})
public class TournamentScheduleServlet extends HttpServlet {

    RepositoryProvider repositoryProvider;
    ITournamentRepository tournamentRepository;
    IMatchRepository matchRepository;
    ITeamRepository teamRepository;
    
    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        tournamentRepository = repositoryProvider.getTournamentRepository();
        matchRepository = repositoryProvider.getMatchRepository();
        teamRepository = repositoryProvider.getTeamRepository();
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
        String tournamentName = request.getParameter("tournamentName");
        Tournament tournament = tournamentRepository.getByName(tournamentName);
        Set<Match> tournamentMatches = matchRepository.getByTournament(tournament);
        
        Set<String> matchesNames = new HashSet<>();
        for(Match match: tournamentMatches)
        {
            if(match.getFinished()==false)
            {
                String team1Name = teamRepository.getById(match.getTeamId(1)).getName();
                if(match.getTeamId(2)!=null)
                {
                    Team secondTeam = teamRepository.getById(match.getTeamId(2));
                    String team2Name = secondTeam.getName();
                    String matchName = team1Name + " vs " + team2Name;
                    matchesNames.add(matchName);
                }
                else
                {
                    String matchName = team1Name + " vs jeszcze nikt";
                    matchesNames.add(matchName);
                }
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("matchesToShow", matchesNames);
        session.setAttribute("tournamentName", tournamentName);
        response.sendRedirect("TournamentSchedule.jsp?tournamentName=" + tournamentName);
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
