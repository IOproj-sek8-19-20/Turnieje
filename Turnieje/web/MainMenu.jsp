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
    <center>
        <input type = "submit" value = "Stworz druzyne" onclick="submitTeam()">
        
        <br/><br/>
        
        <input type = "submit" value = "Stworz turniej" onclick="submitTour()">
    </center>
        <script>
            function submitTeam()
            {
                location.replace("/Turnieje/TeamCreateManage/CreateTeam.jsp");
            }
            function submitTour()
            {
                location.replace("/Turnieje/TournamentCreateManage/CreateTournament.jsp");
            }
    </script>
    </body>
</html>
