<%-- 
    Document   : SendEmailSuccess
    Created on : 2020-01-27, 03:10:10
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
            <title>Powodzenie wyslania</title>
        </head>
        <body>
    <center>
        Pomyślnie wysłano! <br></br>
          <input type = "submit" value = "Wróć do strony głównej" onclick="submit()">
    </center>
        <script>
            function submit()
            {
                       location = "/Turnieje/MainMenu.jsp";
            }
    </script>
    </html>
