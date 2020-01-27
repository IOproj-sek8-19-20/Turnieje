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
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Witamy w serwisie!</title>
    </head>
    <body>
<%
    String loggedUser = (String) session.getAttribute("loggedUser");
    if(loggedUser == null)
    {
        response.sendRedirect("/Turnieje/Login.jsp");
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
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTournamentsList">
        <input type = "submit" value = "Turnieje">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTeamsList">
        <input type = "submit" value = "Drużyny">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTournamentsList?onlyMine=true">
        <input type = "submit" value = "Moje turnieje">
        </a>
        
        <br/><br/>
         
        <a href="/Turnieje/PrepareTeamsList?onlyMine=true">
        <input type = "submit" value = "Moje drużyny">
        </a>
        <br/><br/>
        <a href="/Turnieje/EditMyData?onlyMine=true">
        <input type = "submit" value = "Edytuj konto">
        </a>
        
        <br/><br/>
        
        <a href="/Turnieje/AALogout">
        <input type = "submit" value = "Wyloguj">
        </a>

          <br/><br/>
         
        <a href="/Turnieje/PrepareSendEmail">
        <input type = "submit" value = "Wyslij wiadomosc">
        </a>
        
    </center>
    </body>
</html>
