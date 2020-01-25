/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.util.Date;
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
import pl.polsl.aei.io.turnieje.model.datamodel.PlayerInTeam;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.IDisciplineRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 * Servlet responsible for editing the team.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "ManageTeamServlet", urlPatterns = {"/ManageTeam"})
public class AAManageTeamServlet extends HttpServlet {

    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;
    ITeamRepository teamRepository;
    IDisciplineRepository disciplineRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        teamRepository = repositoryProvider.getTeamRepository();
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
        
        String JSONString = request.getParameter("JSONFromCreateTeam");
        JSONObject JSON = new JSONObject(JSONString);

        String teamName = JSON.getString("name");
        
        HttpSession session = request.getSession(true);
        String oldCaptainEmail = (String) session.getAttribute("loggedUser");
        User oldCaptain = userRepository.getByEmail(oldCaptainEmail);
        User newCaptain = userRepository.getByEmail(JSON.getString("captain"));
        if(newCaptain==null)
        {
            String errorMessage = "Brak w bazie adresu email: " + JSON.getString("captain");
            response.sendRedirect("/Turnieje/Error.jsp?errorMessage="+errorMessage);
            return;
        }
        String toEditName = (String) session.getAttribute("teamToEdit");
        Team toEdit = teamRepository.getByName(toEditName);
        
        if(newCaptain.id == oldCaptain.id)
        {
            //brak zmiany kapitana
        }
        else
        {
            toEdit.setCapitan(newCaptain);
        }
        
        //testowo narazie usuwam wszystkich i na nowo uzupelniam
        toEdit.getPlayers().clear();
        JSONArray users = JSON.getJSONArray("usersToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for(int i=0; i<users.length();i++)
        {
            User newPlayer = userRepository.getByEmail(users.getString(i));
            PlayerInTeam playerInTeam = new PlayerInTeam();	
            playerInTeam.teamId = toEdit.id;
            playerInTeam.userId = newPlayer.id;
            playerInTeam.joinDate = date;
            toEdit.addPlayer(playerInTeam);
        }
        
        toEdit.getDisciplines().clear();
        JSONArray disciplines = JSON.getJSONArray("disciplinesToAdd");
        Set<Discipline> allDisciplines = disciplineRepository.getAll();
        for(int i=0; i<disciplines.length();i++)
        {
            for(Discipline discipline: allDisciplines)
            {
                String disciplineName = discipline.getName();
                if(disciplines.getString(i).equals(disciplineName))
                {
                    toEdit.addDiscipline(discipline);
                }
            }
        }
        
        toEdit.setName(teamName);
        teamRepository.update(toEdit);
        
        response.sendRedirect("/Turnieje/TeamCreateManage/TeamEdited.jsp?teamName="+teamName);
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
