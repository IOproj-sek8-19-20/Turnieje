<%-- 
    Document   : TeamCreated
    Created on : 2020-01-09, 19:47:13
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.TeamId"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.ITeamRepository"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drużyna utworzona</title
    </head>
    
    <%
        Team acutalTeam = (Team) session.getAttribute("actualTeam");
    %>
    
    <body>
        <center>
            <h1> Drużyna o ID: <%= acutalTeam.getId().id %>, nazwie <%= acutalTeam.getName() %> dodana</h1>
        </center>   

        <script>
        setTimeout(function() {
            location = "/Turnieje/PrepareManageTeam?teamName=<%= acutalTeam.getName() %>"
        }, 2000);
        </script>
    </body>
</html>
