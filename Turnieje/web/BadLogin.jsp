<%-- 
    Document   : BadLogin
    Created on : 2020-01-11, 15:03:08
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezultat logowania </title>
    </head>
    <body>
        <h1>Zly login lub haslo!</h1>
        <input type = "submit" value = "Wróc do ekranu logowania" onclick="submit()">
        <script>
            function submit()
            {
                       location.replace("/Turnieje/Login.jsp");
            }
    </script>
    </body>
</html>
