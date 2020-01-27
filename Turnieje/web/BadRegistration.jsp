<%-- 
    Document   : BadRegistration
    Created on : 2020-01-11, 22:16:00
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Niepowodzenie Rejestracji</title>
    </head>
    <body>
        <h1> <%= request.getParameter("statement") %> </h1>
          <input type = "submit" value = "Wróc do ekranu rejestracji" onclick="submit()">
        <script>
            function submit()
            {
                       location = "/Turnieje/Registration.jsp";
            }
    </script>
    </body>
</html>
