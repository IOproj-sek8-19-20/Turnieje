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
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Drużyna zedytowana</title>
    </head>
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("/Turnieje/Login.jsp");
            return;
        }
        String editedTeam = request.getParameter("teamName");
    %>
    
    <body> 
        <center>
            <h1> Pomyślna edycja drużyny <%= editedTeam %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/PrepareManageTeam?teamName=<%= editedTeam %>"
        }, 2000);
        </script>
    </body>
</html>
