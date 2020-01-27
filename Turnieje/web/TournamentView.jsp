<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad turnieju</title>
    </head>

    <script>var toInit = "Teams";</script>
    

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        <br/><br/>
        
        Druzyny biorace udzial w tym turnieju:
        
        <br/><br/>
        
        <iframe id="Teams" src="/Turnieje/Lists/TournamentTeamsList.jsp"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz druzyne" onclick="submitShowTeam()">
        
        <br/><br/>
        
        <input type = "submit" value = "Drabinka" onclick="submitShowBracket()">  
        
        <br/><br/>
        
        <input type = "submit" value = "Harmonogram" onclick="submitShowSchedule()">    
        
        <br/><br/>
        
        <form action = "MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>

        </center>
        <script>
        function submitShowBracket()
            {
                var name = "<%= request.getParameter("tournamentName")%>"
                location = "/Turnieje/TournamentBracketView.jsp?tournamentName="+name;
            }
        function submitShowSchedule()
            {
                var name = "<%= request.getParameter("tournamentName")%>"
                location = "/Turnieje/TournamentScheduleView.jsp?tournamentName="+name;
            }
        function submitShowTeam()
            {
                var iframe = document.getElementById("Teams");   
                var select = iframe.contentWindow.document.getElementById("choosedTeams");   
                var options = select.getElementsByTagName('option');    
                location = "/Turnieje/TeamView.jsp?teamName="+options[select.selectedIndex].text;
            }
        </script>
    </body>
</html>