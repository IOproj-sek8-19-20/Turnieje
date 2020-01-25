<%-- 
    Document   : Error
    Created on : 2020-01-25, 18:29:34
    Author     : Danielowy Eltech
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Error!</title>
    </head>
    <body>
    <center><b><h1>
        <%= request.getParameter("errorMessage") %>
    </h1></b></center>
    </body>
</html>
