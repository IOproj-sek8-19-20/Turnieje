<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad harmonogramu</title>
    </head>
    
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("/Turnieje/Login.jsp");
            return;
        }
    %>
    
    <script>var toCount="Matches"</script>
    <body onload="myCountingFunction(toCount);init()">

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        Harmonogram:
        
        <br><br/>
        
        <iframe id="Matches" src="/Turnieje/Lists/MatchesList.jsp"></iframe>
        
        <br><br/>
        
        <input type = "submit" value = "Powrot" onclick="submitShowTournament()">

    </center>
    
    <script>
    function submitShowTournament()
    {
        var name = "<%= request.getParameter("tournamentName")%>"
        location = "/Turnieje/TournamentView?tournamentName="+name;
    }
    </script>
    </body>
</html>