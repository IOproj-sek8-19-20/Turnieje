<%-- 
    Document   : MainMenu
    Created on : 2020-01-12, 00:11:21
    Author     : mariu
--%>

<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Witamy w serwisie!</title>
    </head>
    <body>
<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }
%>
    <center>
        
        Zalogowany jako: <%= loggedUser.getEmail() %>
        
        <br/><br/>
        
        <input type = "submit" value = "Stworz druzyne" onclick="submitCreateTeam()">
        
        <br/><br/>
        
        <input type = "submit" value = "Stworz turniej" onclick="submitCreateTournament()">
        
        <br/><br/><br/><br/>
        
        <input type = "submit" value = "Pokaz turnieje" onclick="submitShowTournaments()">
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz druzyny" onclick="submitShowTeams()">
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz moje turnieje" onclick="submitShowMyTournaments()">
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz moje druzyny" onclick="submitShowMyTeams()">
        
    </center>
        <script>
            function submitCreateTeam()
            {
                location.replace("/Turnieje/PrepareCreateTeamServlet");
            }
            function submitCreateTournament()
            {
                location.replace("/Turnieje/PrepareCreateTournamentServlet");
            }
            function submitShowTournaments()
            {
                location.replace("/Turnieje/PrepareTournamentsList");
            }
            function submitShowTeams()
            {
                location.replace("/Turnieje/PrepareTeamsList");
            }
            function submitShowMyTournaments()
            {
                location.replace("/Turnieje/PrepareTournamentsList?onlyMine=true");
            }
            function submitShowMyTeams()
            {
                location.replace("/Turnieje/PrepareTeamsList?onlyMine=true");
            }
    </script>
    </body>
</html>
