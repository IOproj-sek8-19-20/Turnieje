<%-- 
    Document   : GoodRegistration
    Created on : 2020-01-26, 13:41:14
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
            <title>Powodzenie Rejestracji</title>
        </head>
        <body>
             <h1> Sprawdź skrzynkę pocztową i aktywuj maila!</h1>
             <input type = "submit" value = "Wróc do ekranu logowania" onclick="submit()">
        <script>
            function submit()
            {
                       location = "/Turnieje/Login.jsp";
            }
        </body>
    </html>
</f:view>
