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
import pl.polsl.aei.io.turnieje.model.datamodel.Discipline;
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.repository.IDisciplineRepository;
import pl.polsl.aei.io.turnieje.model.repository.IMatchRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITournamentRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Daniel-Laptop
 */
@WebServlet(name = "PrepareManageTournament", urlPatterns = {"/PrepareManageTournament"})
public class AAPrepareManageTournament extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    ITournamentRepository tournamentRepository;
    ITeamRepository teamRepository;
    IDisciplineRepository disciplineRepository;
    IMatchRepository matchRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        tournamentRepository = repositoryProvider.getTournamentRepository();
        teamRepository = repositoryProvider.getTeamRepository();
        disciplineRepository = repositoryProvider.getDisciplineRepository();
        matchRepository = repositoryProvider.getMatchRepository();
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

        String tournamentName= request.getParameter("tournamentName");
        Tournament toEdit = tournamentRepository.getByName(tournamentName);
        
        Set<Match> tournamentMatches = matchRepository.getByTournament(toEdit);
        for(Match match: tournamentMatches)
        {
            if(match.getFinished()==true)
            {
                response.sendRedirect("/Turnieje/Error.jsp?errorMessage=Nie można edytowac rozpoczetego turnieju!");
                return;
            }
        }
        
        Set<Team> allTeams = teamRepository.getAll();
        Set<Team> teamsInTournament = teamRepository.getByTournament(toEdit);
        //Usuniecie z allUsers uzytkownikow juz dodanych
        for (Team team: teamsInTournament)
        {
            for (Team team2: allTeams)
            {
                if(team.id.id==team2.id.id)
                {
                    allTeams.remove(team2);
                    break;
                }
            }
        }
        
        Set<String> allTeamsNames = new HashSet<>();
        for(Team team: allTeams)
        {
            allTeamsNames.add(team.getName());
        }
        
        Set<String> teamsInTournamentNames = new HashSet<>();
        for(Team team: teamsInTournament)
        {
            teamsInTournamentNames.add(team.getName());
        }
        
        Set<String> allDisciplinesNames = new HashSet<>();
        Set<Discipline> allDisciplines = disciplineRepository.getAll();
        for(Discipline discipline: allDisciplines)
        {
            allDisciplinesNames.add(discipline.getName());
        }
        
        HttpSession session = request.getSession(true);
        session.setAttribute("torunamentToEdit", toEdit.getName());
        session.setAttribute("allTeams", allTeamsNames);
        session.setAttribute("teamsInTournament", teamsInTournamentNames);
        session.setAttribute("tournamentToEditTeamSize", toEdit.getTeamSize());
        session.setAttribute("tournamentToEditDiscipline", toEdit.getDiscipline().getName());
        session.setAttribute("notTeamDisciplines", allDisciplinesNames);
        String actualAdmin = (String) session.getAttribute("loggedUser");
        session.setAttribute("tournamentAdmin", actualAdmin);
        
        response.sendRedirect("/Turnieje/TournamentCreateManage/ManageTournament.jsp?tournamentName="+tournamentName);
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
