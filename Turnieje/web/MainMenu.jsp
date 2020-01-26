<%-- 
    Document   : MainMenu
    Created on : 2020-01-12, 00:11:21
    Author     : mariu
--%>

<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Witamy w serwisie!</title>
    </head>
    <body>
<%
    String loggedUser = (String) session.getAttribute("loggedUser");
    if(loggedUser == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }
%>
    <center>
        
        Zalogowany jako: <%= loggedUser.toString() %>
        
        <br/><br/>
        
        <a href="/Turnieje/PrepareCreateTeamServlet">
        <input type = "submit" value = "Stwórz drużynę" >
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareCreateTournamentServlet">
        <input type = "submit" value = "Stwórz turniej">
        </a>
        
        <br/><br/><br/><br/>
         
        <a href="/Turnieje/PrepareTournamentsList">
        <input type = "submit" value = "Pokaż turnieje">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTeamsList">
        <input type = "submit" value = "Pokaż drużyny">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTournamentsList?onlyMine=true">
        <input type = "submit" value = "Pokaż moje turnieje">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTeamsList?onlyMine=true">
        <input type = "submit" value = "Pokaż moje drużyny">
        </a>

        
    </center>
    </body>
</html>
