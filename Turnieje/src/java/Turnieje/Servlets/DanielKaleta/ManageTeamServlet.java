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
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;

/**
 * Servlet responsible for editing the team.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "ManageTeamServlet", urlPatterns = {"/ManageTeam"})
public class ManageTeamServlet extends HttpServlet {

    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;
    ITeamRepository teamRepository;

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
        
        String JSONString = request.getParameter("JSONFromCreateTeam");
        JSONObject JSON = new JSONObject(JSONString);

        String teamName = JSON.getString("name");
        String newCaptain = JSON.getString("captain");

        
        JSONArray users = JSON.getJSONArray("usersToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for(int i=0; i<users.length();i++)
        {
            System.out.print(users.getString(i));
        }
        
        JSONArray disciplines = JSON.getJSONArray("disciplinesToAdd");
        //wypisanie dodanych dyscyplin w ramach testu czy dziala
        for(int i=0; i<disciplines.length();i++)
        {
            System.out.print(disciplines.getString(i));
        }
        
        HttpSession session = request.getSession(true);
        String oldCaptain = (String) session.getAttribute("loginUser");
        boolean newCaptainCorrect=false;
        
        if(oldCaptain.compareTo(newCaptain)==0)
        {
            //nic nie trzeba robic, kapitan bez zmian
        }
        else
        {
            //zmiana kapitana
            //sprawdzam, czy nowy kapitan znajduje sie w druzynie
            for(int i=0; i<users.length();i++)
            {
                System.out.print(users.getString(i));
                if(users.getString(i).compareTo(newCaptain)==0)
                {
                    //nowy kapitan znajduje sie w druzynie
                    newCaptainCorrect=true;
                    break;
                }
            }
            //nowy kapitan nie znajduje sie w druzynie, ustawiam spowrotem dotychczasowego
            if(newCaptainCorrect==false)
            {
                Cookie cookie = new Cookie("aboutTeam", JSONString);
                response.addCookie(cookie);
        
                response.sendRedirect("/Turnieje/TeamCreateManage/TeamEdited.jsp?teamName=" + teamName);
                JSON.put("captain", oldCaptain);
            }
            else
            {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("aboutTeam")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            break;
                        }
                    }
                }
                response.sendRedirect("/Turnieje/MainMenu.jsp"); 
            }
        }

        //Team toEdit = teamRepository.getById(managedTeamID);
        //toEdit.setName(teamName);
        //teamRepository.update(toEdit);
        


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
