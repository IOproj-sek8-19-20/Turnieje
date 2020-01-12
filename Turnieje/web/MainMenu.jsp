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
