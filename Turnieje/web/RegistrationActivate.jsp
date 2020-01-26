<%-- 
    Document   : RegistrationActivate
    Created on : 2020-01-26, 14:37:33
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Aktywacja Linku </title>
        </head>
        <body>
            <h1>Dziekujemy za rejestrację w serwisie!</h1> <br>
             <input type = "submit" value = "Dalej" onclick="submit()">
            <script>
                function submit()
                {    var id = "<%= request.getParameter("id")%>"
            location = "/Turnieje/RegistrationActivate?JSONFromRegistrationActivate="+id;
        }
        </script>       
</body>
    </html>
