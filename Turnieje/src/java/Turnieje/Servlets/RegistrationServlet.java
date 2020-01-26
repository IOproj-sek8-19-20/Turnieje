/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;


import email.GoogleMail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.IUserRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;
/**
 *
 * @author mariu
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/Registration"})
public class RegistrationServlet extends HttpServlet {
ITeamRepository teamRepository;

    //<editor-fold defaultstate="expanded" desc="init()">
    RepositoryProvider repositoryProvider;
    IUserRepository userRepository;
 

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        userRepository = repositoryProvider.getUserRepository();
     
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
       public static boolean validateFirstName(String firstName) {       
             return firstName.matches("[A-Z][a-zA-Z]*");
        }
        public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
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
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        
        String JSONString = request.getParameter("JSONFromRegistration");
        JSONObject JSON = new JSONObject(JSONString);
        String name = JSON.getString("name");
        String surname = JSON.getString("surname");
        String email = JSON.getString("email");
        String password1 = JSON.getString("password1");
        String password2 = JSON.getString("password2");
        String checkBox= JSON.getString("checkBox"); 
        String statement ="";
        if ("".equals(name))
        {statement="Imie jest puste!";

        }
        if(name.length()<3)
        {
            statement="Imie jest zbyt krótkie!";
        }
        if(!validateFirstName(name))
        {
            statement="Imie jest niepoprawne!";

        }
        if(!validateLastName(surname))
        {
            statement="Nazwisko jest niepoprawne!";

        }
        if ("".equals(surname))
        {statement="Nazwisko jest puste!";
  
        }
        if(surname.length()<3)
        {
        statement="Nazwisko jest zbyt krótkie!";

        }
        if (!(password1.equals(password2)))
        { statement="Hasła są różne!";
      
        }
        
         if (("".equals(password1)) || ("".equals(password2)))
        {statement="Hasło jest puste!";
      
        }
         if(password1.length()<8)
        {
        statement="Hasło jest zbyt krótkie!";
        
        }
          if(!isValidEmailAddress(email))
          {
          statement="Niepoprawny email!";
         
          }
         if ("false".equals(checkBox))
          {
              statement="Brak akceptacji regulaminu!";
             
          } 
         if("".equals(statement))
        { User user = new User();
         user.setEmail(email);
         user.setFirstName(name);
         user.setActive(false);
         user.setLastName(surname);
         user.setPassHash(password1);
         UserId userid = userRepository.add(user);
         
         if (userid == null)
         {
              statement ="Email ktory podales juz istnieje w bazie!";
              response.sendRedirect("BadRegistration.jsp?statement="+statement);
         }
         else
         {
         int id=userid.id;
         GoogleMail.Send("turniejeserwis","Aligator33",email,
            email,"Link Aktywacyjny","Jeśli się rejestrowałeś skopiuj ten link aktywacyjny: "
                      + "http://localhost:15406/Turnieje/RegistrationActivate.jsp?id="+id);
        
        if(statement.isEmpty())
        {  
            response.sendRedirect("GoodRegistration.jsp");
        }
        else
        {  
          response.sendRedirect("BadRegistration.jsp?statement="+statement);
        }
        }
        }
         else
         {
              response.sendRedirect("BadRegistration.jsp?statement="+statement);
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
    try {
        processRequest(request, response);
    } catch (MessagingException ex) {
        Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    try {
        processRequest(request, response);
    } catch (MessagingException ex) {
        Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
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