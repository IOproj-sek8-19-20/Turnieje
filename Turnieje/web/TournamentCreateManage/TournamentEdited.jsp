<%-- 
    Document   : TournamentEdited
    Created on : 2020-01-09, 20:27:37
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Turniej zedytowany</title>
    </head>
    
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
        
        String tournamentName = request.getParameter("tournamentName");
    %>
    
    <body> 
        <center>
            <h1> Pomyślna edycja turnieju <%= tournamentName %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/TournamentCreateManage/ManageTournament.jsp?tournamentName="+"<%= tournamentName %>"+""
        }, 2000);
        </script>
    </body>
</html>
