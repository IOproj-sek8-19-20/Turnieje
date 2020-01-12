<%-- 
    Document   : TeamEdited
    Created on : 2020-01-09, 20:21:53
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Druzyna zedytowana</title>
    </head>
    
    <body> 
        <center>
            <h1> Pomyślna zmiana nazwy druzyny na: <%= request.getParameter("teamName") %></h1>
        </center>   

        <script>
        setTimeout(function() 
        {
            location.replace("/Turnieje/TeamCreateManage/ManageTeam.jsp?teamName="+"<%= request.getParameter("teamName") %>"+"")
        }, 2000);
        </script>
    </body>
</html>
