<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Podglad druzyny</title>
    </head>

    <script>var toInit = "Users";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa druzyny: <%= request.getParameter("teamName") %> </h1>
        
        <br/><br/>

        Zawodnicy:

        <br/><br/>

        <%--  lista uzytkownikow w turnieju z tym id --%>
        
        <br/><br/>
        
        <form action = "index.html" method="get">
            <input type = "submit" value = "Powrot">
        </form>

    </center>
    
    </body>
</html>