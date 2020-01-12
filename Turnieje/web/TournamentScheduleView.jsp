<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Podglad harmonogramu</title>
    </head>

    <script>var toInit = "Teams";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        Harmonogram:
        
        <%-- Nadchodzace mecze --%>
        
        <br><br/>
        
        <input type = "submit" value = "Powrot" onclick="submitShowTournament()">

    </center>
    
    <script>
    function submitShowTournament()
    {
        var name = "<%= request.getParameter("tournamentName")%>"
        location.replace("/Turnieje/TournamentView.jsp?tournamentName="+name);
    }
    </script>
    </body>
</html>