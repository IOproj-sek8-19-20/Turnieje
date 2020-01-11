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
        <input type = "submit" value = "Stworz druzyne" onclick="submitTeam()">
        <input type = "submit" value = "Stworz turniej" onclick="submitTour()">
        <script>
            function submitTeam()
            {
                       location.replace("/Turnieje/CreateTeam.jsp");
            }
    </script>
         <script>
            function submitTour()
            {
                       location.replace("/Turnieje/CreateTournament.jsp");
            }
    </script>
    </body>
</html>
