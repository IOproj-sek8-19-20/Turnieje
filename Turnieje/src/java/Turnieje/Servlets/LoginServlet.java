/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author mariu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
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

        String JSONString = request.getParameter("JSONFromLogin");
        JSONObject JSON = new JSONObject(JSONString);
        String login = JSON.getString("login");
        String password = JSON.getString("password");
      
        User user = userRepository.getByEmail(login);
         if (user != null || (user.checkPassword(password)) || (!user.getActive()))
                  {
                      HttpSession session = request.getSession(true);
                      session.setAttribute("passwordUser", password);
                      session.setAttribute("loggedUser", login);
                      session.setAttribute("acive","YES");
                      response.sendRedirect("MainMenu.jsp");
                  }
                  else
                  {
                      response.sendRedirect("BadLogin.jsp");
                  }
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