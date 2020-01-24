<%-- 
    Document   : TeamCreated
    Created on : 2020-01-09, 19:47:13
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drużyna utworzona</title
    </head>
    
    <%
        String acutalTeam = (String) session.getAttribute("actualTeamName");
    %>
    
    <body>
        <center>
            <h1> Drużyna <%= acutalTeam.toString() %> dodana</h1>
        </center>   

        <script>
        setTimeout(function() {
            location = "/Turnieje/PrepareManageTeam?teamName=<%= acutalTeam.toString() %>"
        }, 2000);
        </script>
    </body>
</html>
