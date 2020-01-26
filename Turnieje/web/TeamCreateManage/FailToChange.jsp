<%-- 
    Document   : FailToChange
    Created on : 2020-01-26, 15:46:52
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
        <h1>Nie udało się zmienić danych! Wpisano złe hasło lub dane nie spełniają wymagań</h1>
        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/TeamCreateManage/EditMenu.jsp"
        }, 2000);
        </script>
    </body>
</html>
