<%-- 
    Document   : BadPassword
    Created on : 2020-01-12, 21:40:06
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Podano złe hasło! Aby zmienić hasło podaj poprawne hasło!</h1>
        <script>
        setTimeout(function() 
        {
            location.replace("/Turnieje/edit/EditData.jsp")
        }, 2000);
        </script>
    </body>
</html>
