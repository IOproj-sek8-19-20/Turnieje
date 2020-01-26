/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.TeamRepository;

/**
 *
 * @author mariu
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/Registration"})
public class RegistrationServlet extends HttpServlet {
ITeamRepository teamRepository;

    //<editor-fold defaultstate="expanded" desc="init()">
    @Override
    public void init() {
        //teamRepository = new TeamRepository();
    }
    //wykorzystanie klasy InternetAddress do sprawdzenia emaila 
      public static boolean isValidEmailAddress(String email) { 
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
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
        
        String JSONString = request.getParameter("JSONFromRegistration");
        JSONObject JSON = new JSONObject(JSONString);
        String name = JSON.getString("name");
        String surname = JSON.getString("surname");
        String email = JSON.getString("email");
        String password1 = JSON.getString("password1");
        String password2 = JSON.getString("password2");
        String checkBox= JSON.getString("checkBox"); //on gdy zazanaczony
        String statement ="";
        if ("".equals(name))
        {statement="Imie jest puste!";
        }
         if ("".equals(surname))
        {statement="Nazwisko jest puste!";
        }
         
        if (!(password1.equals(password2)))
        { statement="Hasła są różne!";
        }
        
         if (("".equals(password1)) || ("".equals(password2)))
        {statement="Hasło jest puste!";
        }
         
          if (("on".equals(checkBox)))
        {
        statement="Brak akceptacji regulaminu!";
        }
          if(!isValidEmailAddress(email))
          {
          statement="Niepoprawny email!";
          }
        
         response.sendRedirect("BadRegistration.jsp?statement="+statement);
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