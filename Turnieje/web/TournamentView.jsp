<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Podglad turnieju</title>
    </head>

    <script>var toInit = "Teams";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa turnieju: <%= request.getParameter("tournamentName") %> </h1>
        
        <br/><br/>

        <%-- lista druzyn bioracych udzial w tym turnieju --%>
        
        <br/><br/>

        <form action = "TournamentBracketView.jsp" method="get">
            <input type = "submit" value = "Drabinka">
        </form>
        
        <br/><br/>
        
        <form action = "TournamentScheduleView.jsp" method="get">
            <input type = "submit" value = "Harmonogram">
        </form>
        
        <br/><br/>
        
        <form action = "index.html" method="get">
            <input type = "submit" value = "Powrot">
        </form>

    </center>
    
    </body>
</html>