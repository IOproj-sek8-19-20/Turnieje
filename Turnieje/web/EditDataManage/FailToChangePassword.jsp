<%-- 
    Document   : FailToChangePassword
    Created on : 2020-01-26, 20:09:30
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
        <h1>Nie udało się zmienić hasła. Wpisano nieprawidłowe stare hasło lub hasło nie spełnia wymagań</h1>
        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/EditDataManage/EditMenu.jsp"
        }, 2000);
        </script>
    </body>
</html>
