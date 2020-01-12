<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Podglad drabinki</title>
    </head>

    <script>var toInit = "Teams";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        Drabinka:
        
        <%-- Tabele z meczami --%>
        
        <br><br/>
        
        <form action = "TournamentView.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>

    </center>
    
    </body>
</html>