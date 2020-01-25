<%-- 
    Document   : TournamentCreated
    Created on : 2020-01-09, 19:57:31
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Turniej utworzony</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <h1> Turniej <%= tournamentName %> stworzony</h1>
        </center>   

        <script>
            setTimeout(function () 
            {
                location = "/Turnieje/PrepareManageTournament?tournamentName=" + "<%= tournamentName %>";
            }, 2000);
        </script>
    </body>
</html>
