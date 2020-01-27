<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Imie zmienione</title>
    </head>
    
    <body> 
    <center>
        <h1> Pomyślna zmiana nazwiska na: <%= session.getAttribute("lastName") %></h1>
    </center>   
    
        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/edit/EditData.jsp"
        }, 2000);
        </script>
    </body>
</html>