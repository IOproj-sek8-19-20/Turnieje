/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;

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
        //pomysł :
        User user = userRepository.getByEmail(login);
        // if (user != null)
           //    {
             ///      if (user.checkpassword(document.getElementById("password").value;))
            //       {
             //          location = "/Turnieje/MainMenu?JSON";
            //       }
            //   }
            //   else
           //    {
           //        location = "/Turnieje/BadLogin?JSONFromLogin";
           //    }
          //  }
        //   System.out.print(login);
         //   System.out.print(password);
         
        if ((login.equals("123")) && (password.equals("123"))) //test
        { 
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute("ListOperation");
            session.setAttribute("loginUser", login);
            session.setAttribute("passwordUser", password);
            
            session.setAttribute("loggedUser", user);
            //Jezeli ta postac przejdzie to pakowanie do sesji loginu, hasla, a chyba
            //nawet active jest zbedne, bo mozna wyciagnac wszystko z obiektu user
            //oraz sprawdzac czy jest nullem
            session.setAttribute("acive","YES"); // sprawdzanie na każdej stornie 
            //zabrania wpsiania od razu adresu akcji wymagającej logowania
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