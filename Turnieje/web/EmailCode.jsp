<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zmien dane</title>
    </head>
    <body>
    <center>
        <h1> Email zmieniony na: <%= session.getAttribute("email") %></h1>
    </center>
    <script>
        setTimeout(function() 
        {
            location = "/Turnieje/edit/EditData.jsp"
        }, 2000);
        </script>
    </body>
</html>


