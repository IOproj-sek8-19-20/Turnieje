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
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Drużyna utworzona</title
    </head>
    
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("/Turnieje/Login.jsp");
            return;
        }
        
        String teamName = request.getParameter("teamName");
    %>
    
    <body>
        <center>
            <h1> Drużyna <%= teamName %> dodana</h1>
        </center>   

        <script>
        setTimeout(function() {
            location = "/Turnieje/PrepareManageTeam?teamName=<%= teamName %>"
        }, 2000);
        </script>
    </body>
</html>
