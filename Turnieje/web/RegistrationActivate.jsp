<%-- 
    Document   : RegistrationActivate
    Created on : 2020-01-26, 14:37:33
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Aktywacja Linku </title>
        </head>
        <body>
            <h1>Dziekujemy za rejestrację w serwisie!</h1>
            <script>
                var id = "<%= request.getParameter("id")%>"
            location = "/Turnieje/RegistrationActivate?JSONFromRegistrationActivate="+id;
        </script>       
</body>
    </html>
</f:view>
