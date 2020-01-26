<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Imie zmienione</title>
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
    <center>
        <h1> Pomyślna zmiana imienia na: <%= session.getAttribute("firstName") %></h1>
    </center>   
    
        <script>
        setTimeout (function() 
        {
            location = "/Turnieje/EditDataManage/EditMenu.jsp"
        }, 2000);
        </script>
    </body>
</html>