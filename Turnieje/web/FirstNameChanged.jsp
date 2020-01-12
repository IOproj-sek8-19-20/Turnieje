<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Imie zmienione</title>
    </head>
    
    <body> 
    <center>
        <h1> Pomyślna zmiana imienia na: <%= session.getAttribute("firstName") %></h1>
    </center>   
    
        <script>
        setTimeout(function() 
        {
            location.replace("/Turnieje/edit/EditData.jsp")
        }, 2000);
        </script>
    </body>
</html>