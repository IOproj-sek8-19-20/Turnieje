<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj turniej</title>
    </head>

    <body onload="init()">

    <center>
        
    <h1>Edytujesz turniej: <%= request.getParameter("tournamentName") %> </h1>
    
    <form action = "ManageTournament" method="get">
        Nazwa turnieju : <input type = "text" name = "tournamentName" id="teamName">
        
        <br/><br/>

        <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
        <iframe id="ChoosedTeams" src="/Turnieje/Lists/ChoosedTeamsList.jsp" width="300" height="150"></iframe>

        <br/><br/>
        
        <input type = "submit" value = "Zatwierdz">
    </form>

    </center>
    
    <script src="/Turnieje/JavaScripts/initTeams.js"></script>
    <script src="/Turnieje/JavaScripts/addTeams.js"></script>
    <script src="/Turnieje/JavaScripts/deleteTeams.js"></script>
    </body>
</html>


