<%-- 
    Document   : MainMenu
    Created on : 2020-01-12, 00:11:21
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Witamy w serwisie!</title>
    </head>
    <body>
<%
    String user = null;
    if(session.getAttribute("loginUser") == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }
    else 
    {
        user = (String) session.getAttribute("loginUser");
    } 

%>
    <center>
        
        Zalogowany jako: <%= user %>
        
        <br/><br/>
        
        <input type = "submit" value = "Stworz druzyne" onclick="submitCreateTeam()">
        
        <br/><br/>
        
        <input type = "submit" value = "Stworz turniej" onclick="submitCreateTournament()">
        
        <br/><br/><br/><br/>
        
        <input type = "submit" value = "Pokaz turnieje" onclick="submitShowTournaments()">
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz druzyny" onclick="submitShowTeams()">
        
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
                location.replace("/Turnieje/PrepareTournamentList");
            }
            function submitShowTeams()
            {
                location.replace("/Turnieje/PrepareTeamsList");
            }
    </script>
    </body>
</html>
