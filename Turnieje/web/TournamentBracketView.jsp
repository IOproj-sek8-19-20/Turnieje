<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad drabinki</title>
    </head>

    <script>var toInit = "Teams";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        Drabinka:
        
        <%-- Tabele z meczami --%>
        
        <br><br/>
        
        <input type = "submit" value = "Powrot" onclick="submitShowTournament()">

    </center>
    <script>
    function submitShowTournament()
    {
        var name = "<%= request.getParameter("tournamentName")%>"
        location = "/Turnieje/TournamentView.jsp?tournamentName="+name;
    }
    </script>
    </body>
</html>