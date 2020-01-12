/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.PlayerInTeam;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.UserRepository;

/**
 * Servlet responsible for creating the team.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "CreateTeamServlet", urlPatterns = {"/CreateTeam"})
public class CreateTeamServlet extends HttpServlet {

    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;
    ITeamRepository teamRepository;

    //<editor-fold defaultstate="expanded" desc="init()">
    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        try{
        teamRepository = repositoryProvider.getTeamRepository();
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        userRepository = repositoryProvider.getUserRepository();
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
        
        Date date = new Date();
        
        Team toAdd = new Team();

        String JSONString = request.getParameter("JSONFromCreateTeam");
        JSONObject JSON = new JSONObject(JSONString);

        String teamName = JSON.getString("name");
        toAdd.setName(teamName);
        
        String captain = JSON.getString("captain");
        System.out.print(captain);
        toAdd.setCapitan(userRepository.getByEmail(captain));
        
        Set<PlayerInTeam> players = new TreeSet<>();
        JSONArray users = JSON.getJSONArray("usersToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for(int i=0; i<users.length();i++)
        {
            PlayerInTeam playerToAdd = new PlayerInTeam();
            playerToAdd.teamId = toAdd.getId();
            playerToAdd.userId = userRepository.getByEmail(users.getString(i)).id;
            playerToAdd.joinDate = date;
            try
            {
                toAdd.addPlayer(playerToAdd);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
               
        
        
        
        JSONArray disciplines = JSON.getJSONArray("disciplinesToAdd");
        //wypisanie dodanych dyscyplin w ramach testu czy dziala
        for(int i=0; i<disciplines.length();i++)
        {
            System.out.print(disciplines.getString(i));
        }
        
   
        try
        {
            teamRepository.add(toAdd);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        Cookie cookie = new Cookie("aboutTeam", JSONString);
        response.addCookie(cookie);
        
        response.sendRedirect("/Turnieje/TeamCreateManage/TeamCreated.jsp?teamName=" + teamName);

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
