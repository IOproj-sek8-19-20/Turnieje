<%-- 
    Document   : GoodRegistration
    Created on : 2020-01-26, 13:41:14
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html> 
<html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Powodzenie Rejestracji</title>
        </head>
        <body>
             <h1> Sprawdź skrzynkę pocztową i aktywuj maila!</h1>
             <input type = "submit" value = "Wróc do ekranu logowania" onclick="submit()">
        <script>
            function submit()
            {
                       location = "/Turnieje/Login.jsp";
            }
            </script>
        </body>
    </html>
