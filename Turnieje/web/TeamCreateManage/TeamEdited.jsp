<%-- 
    Document   : TeamEdited
    Created on : 2020-01-09, 20:21:53
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Druzyna zedytowana</title>
    </head>
    <%
        Team editedTeam = (Team) session.getAttribute("actualTeam");
    %>
    
    <body> 
        <center>
            <h1> Pomyślna edycja druzyny <%= editedTeam.getName() %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location.replace("/Turnieje/PrepareManageTeam?teamName=<%= editedTeam.getName() %>")
        }, 2000);
        </script>
    </body>
</html>
