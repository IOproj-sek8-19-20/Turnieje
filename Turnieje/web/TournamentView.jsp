<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad turnieju</title>
    </head>

        <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
    <script>var toCount="Teams"</script>
    <body onload="myCountingFunction(toCount)">
    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        <br/><br/>
        
        Druzyny biorace udzial w tym turnieju:
        
        <br/><br/>
        
        <iframe id="Teams" src="/Turnieje/Lists/TeamsList.jsp?inTournament=true"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz druzyne" onclick="submitShowTeam()">
        
        <br/><br/>
        
        <input type = "submit" value = "Harmonogram" onclick="submitShowSchedule()">    
        
        <br/><br/>
        
        <form action = "MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>

        </center>
        <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
        <script>
        function submitShowSchedule()
            {
                var name = "<%= request.getParameter("tournamentName")%>"
                location = "/Turnieje/TournamentSchedule?tournamentName="+name;
            }
        function submitShowTeam()
            {
                var iframe = document.getElementById("Teams");   
                var select = iframe.contentWindow.document.getElementById("choosedTeams");   
                var options = select.getElementsByTagName('option');    
                location = "/Turnieje/TeamView?teamName="+options[select.selectedIndex].text;
            }
        </script>
    </body>
</html>