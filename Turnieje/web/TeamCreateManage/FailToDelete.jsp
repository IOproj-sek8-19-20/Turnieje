<%-- 
    Document   : FailToDelete
    Created on : 2020-01-26, 20:20:57
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
        <h1>Wpisano złe hasło. Nie możemy usunąć konta</h1>
        <script>
        setTimeout(function() 
        {
            location = "/Turnieje/TeamCreateManage/EditMenu.jsp"
        }, 2000);
        </script>
    </body>
</html>
