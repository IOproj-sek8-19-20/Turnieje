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
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.IDisciplineRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Daniel-Laptop
 */
@WebServlet(name = "PrepareSendEmail", urlPatterns = {"/PrepareSendEmail"})
public class PrepareSendEmail extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;
    IDisciplineRepository disciplineRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
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
        
        Set<User> allUsers = userRepository.getAll();
        Set<String> allUsersEmails = new HashSet<>();
        for(User user: allUsers)
        {
            allUsersEmails.add(user.getEmail());
        }

        Set<String> notTeamDisciplinesNames = new HashSet<>();
        Set<Discipline> allDisciplines = disciplineRepository.getAll();
        for(Discipline discipline: allDisciplines)
        {
            notTeamDisciplinesNames.add(discipline.getName());
        }
        
        Set<String> usersInTeamEmails = new HashSet<>();    //pusty
        Set<String> teamDisciplinesNames = new HashSet<>();      //pusty
        
        HttpSession session = request.getSession(true);
        session.setAttribute("allUsers", allUsersEmails);
        session.setAttribute("usersInTeam", usersInTeamEmails);
        session.setAttribute("teamDisciplines", teamDisciplinesNames);
        session.setAttribute("notTeamDisciplines", notTeamDisciplinesNames);

        response.sendRedirect("/Turnieje/CreateEmail.jsp");
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
