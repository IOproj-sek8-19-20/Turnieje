<%-- 
    Document   : TeamEdited
    Created on : 2020-01-09, 20:21:53
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drużyna zedytowana</title>
    </head>
    <%
        String editedTeam = (String) session.getAttribute("actualTeamName");
    %>
    
    <body> 
        <center>
            <h1> Pomyślna edycja drużyny <%= editedTeam.toString() %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/PrepareManageTeam?teamName=<%= editedTeam.toString() %>"
        }, 2000);
        </script>
    </body>
</html>
