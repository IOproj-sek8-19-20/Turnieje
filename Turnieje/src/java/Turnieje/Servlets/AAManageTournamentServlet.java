/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * Servlet responsible for editing the tournament.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "ManageTournamentServlet", urlPatterns = {"/ManageTournament"})
public class AAManageTournamentServlet extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    ITournamentRepository tournamentRepository;
    ITeamRepository teamRepository;
    IUserRepository userRepository;
    IDisciplineRepository disciplineRepository;
    IMatchRepository matchRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        teamRepository = repositoryProvider.getTeamRepository();
        tournamentRepository = repositoryProvider.getTournamentRepository();
        userRepository = repositoryProvider.getUserRepository();
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
        
        Date date = new Date();

        String JSONString = request.getParameter("JSONFromCreateTournament");
        JSONObject JSON = new JSONObject(JSONString);

        String tournamentName = JSON.getString("name");
        String type = JSON.getString("type").replaceAll(" ", "_");
        String tournamentDisciplineName = JSON.getString("discipline").replaceAll(" ", "_");
        String teamSize = JSON.getString("teamSize");
        String admin = JSON.getString("admin");
        
        HttpSession session = request.getSession(true);
        String toEditName = (String) session.getAttribute("torunamentToEdit");
        Tournament toEdit = tournamentRepository.getByName(toEditName);
        
        String oldAdminEmail = (String) session.getAttribute("loggedUser");
        User oldAdmin = userRepository.getByEmail(oldAdminEmail);
        User newAdmin = userRepository.getByEmail(admin);
        if(newAdmin==null)
        {
            String errorMessage = "Brak w bazie adresu email: " + JSON.getString("admin");
            response.sendRedirect("/Turnieje/Error.jsp?errorMessage="+errorMessage);
            return;
        }
        
        if(newAdmin.id == oldAdmin.id)
        {
            //brak zmiany administratora
        }
        else
        {
            toEdit.setAdmin(newAdmin);
        }
        
        Discipline tournamentDiscipline = disciplineRepository.getByName(tournamentDisciplineName);
        toEdit.setName(tournamentName);
        toEdit.setMode(TournamentMode.valueOf(type));
        toEdit.setDiscipline(tournamentDiscipline);
        toEdit.setTeamSize(Integer.parseInt(teamSize));
        
        //testowo narazie usuwam wszystkie i na nowo uzupelniam
        toEdit.getTeams().clear();

        JSONArray teams = JSON.getJSONArray("teamsToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for (int i = 0; i < teams.length(); i++) 
        {
            System.out.print(teams.getString(i));
            Team toAddTeam = teamRepository.getByName(teams.getString(i));
            TeamInTournament toAdd = new TeamInTournament();
            toAdd.tourId = toEdit.id;
            toAdd.eliminated = false;
            toAdd.joinDate = date;
            toAdd.points = 0;
            toAdd.teamId = toAddTeam.id;
            toAdd.groupNr = 1;
            toEdit.addTeam(toAdd);
        }
        
        Set<Match> tournamentMatches = matchRepository.getByTournament(toEdit);
        for(Match match: tournamentMatches)
        {
            matchRepository.delete(match);
        }
        tournamentRepository.update(toEdit);
        
        Set<Team> allTeams = teamRepository.getAll();
        Set<Team> teamsInTournament = teamRepository.getByTournament(toEdit);
        //Usuwam wszystkie mecze aby uzupelnic na nowo
        
        //Usuniecie z allTeams druzyn juz dodanych
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
        
        List<Team> teamsInTournamentList = new ArrayList<>(teamsInTournament);
        for(int i=0; i<teamsInTournamentList.size();i+=2)
        {
            //Tworzenie nowej listy meczy
            Match toAdd = new Match();
            //No z tą datą to tak jeszcze do przemyślenia
            toAdd.setDate(date);
            toAdd.setFinished(false);
            toAdd.setTourId(toEdit.id);
            toAdd.setTeamId((1), teamsInTournamentList.get(i).id);
            toAdd.setTeamId((2), teamsInTournamentList.get(i+1).id);
            matchRepository.addMatch(toAdd);
        }
        
        Set<String> allDisciplinesNames = new HashSet<>();
        Set<Discipline> allDisciplines = disciplineRepository.getAll();
        for(Discipline discipline: allDisciplines)
        {
            allDisciplinesNames.add(discipline.getName());
        }
        
        session.setAttribute("torunamentToEdit", toEdit.getName());
        session.setAttribute("allTeams", allTeamsNames);
        session.setAttribute("teamsInTournament", teamsInTournamentNames);
        session.setAttribute("tournamentToEditTeamSize", toEdit.getTeamSize());
        session.setAttribute("tournamentToEditDiscipline", toEdit.getDiscipline().getName());
        session.setAttribute("notTeamDisciplines", allDisciplinesNames);
        session.setAttribute("tournamentAdmin", admin);
        
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
