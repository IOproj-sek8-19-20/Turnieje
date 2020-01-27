<%-- 
    Document   : PasswordChanged
    Created on : 2020-01-26, 20:09:14
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("/Turnieje/Login.jsp");
            return;
        }
        %>
        <center>
        <h1>Pomyślnie zmieniono hasło</h1>
        <center/>
        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/EditDataManage/EditMenu.jsp"
        }, 2000);
        </script>
    </body>
</html>
