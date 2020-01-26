/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.repository.IMatchRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Danielowy Eltech
 */
public class AAEnterResultServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);

        String matchName = request.getParameter("match");
        String winner = request.getParameter("winner");
        String tournamentName = (String) session.getAttribute("tournamentName");
        Tournament tournament = tournamentRepository.getByName(tournamentName);
        
        int firstSpace = matchName.indexOf(' ');
        int secondSpace = matchName.indexOf(' ',(firstSpace+1));
        String firstTeamName = matchName.substring(0, firstSpace);
        String secondTeamName = matchName.substring(secondSpace+1, matchName.length());
        
        Team firstTeam = teamRepository.getByName(firstTeamName);
        Team secondTeam = teamRepository.getByName(secondTeamName);
        Team winnerTeam = teamRepository.getByName(winner);
        
        Set<Match> tournamentMatches = matchRepository.getByTournament(tournament);
        Set<Match> tournamentMatchesLeft = new HashSet<>();
        
        //Uzupelnianie listy pozostalych meczy, ponizej do zoptymalizowania petli jeszcze zrobic
        for(Match match: tournamentMatches)
        {
            if(match.getFinished()==false)
            {
                tournamentMatchesLeft.add(match);
            }
        }
        
        //sprawdzam czy ostatni mecz i czy koniec
        if(tournamentMatchesLeft.size()==1)
        {
            tournament.setFinished(true);
        }
        
        for(Match match: tournamentMatches)
        {
            if(match.getTeamId(1).id==firstTeam.id.id)
            {
                if(match.getTeamId(2) != null)
                {
                    if(match.getTeamId(2).id == secondTeam.id.id)
                    {
                        match.setWinner(winnerTeam.id);
                        match.setFinished(true);
                        matchRepository.update(match);
                        break;
                    }
                }
            }
        }
        
        boolean found = false;
        for(Match match: tournamentMatches)
        {
            //znajduje mecz w którym jest tylko pierwsza druzyna, dodaje druga
            if(match.getTeamId(2) == null)
            {
                found=true;
                match.setTeamId(2, winnerTeam.id);
                matchRepository.update(match);
                break;
            }
        }
        //nie znalazlem meczu, tworze nowy
        if(found==false)
        {
            Date date = new Date();
            Match toAdd = new Match();
            //No z tą datą to tak jeszcze do przemyślenia
            toAdd.setDate(date);
            toAdd.setFinished(false);
            toAdd.setTourId(tournament.id);
            toAdd.setTeamId((1), winnerTeam.id);
            matchRepository.addMatch(toAdd);
        }
        

        
            
        response.sendRedirect("/Turnieje/PrepareMatchesList?tournamentName="+tournamentName);
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
