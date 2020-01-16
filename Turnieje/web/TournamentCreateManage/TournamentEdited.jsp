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
        <title>Turniej zedytowany</title>
    </head>
    
    <body> 
        <center>
            <h1> Pomyślna edycja turnieju <%= request.getParameter("tournamentName") %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location.replace("/Turnieje/TournamentCreateManage/ManageTournament.jsp?tournamentName="+"<%= request.getParameter("tournamentName") %>"+"")
        }, 2000);
        </script>
    </body>
</html>
