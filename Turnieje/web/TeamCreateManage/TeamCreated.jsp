<%-- 
    Document   : TeamCreated
    Created on : 2020-01-09, 19:47:13
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Druzyna utworzona</title
    </head>
    
    <body>
        <center>
            <h1> Druzyna <%= request.getParameter("teamName") %> dodana</h1>
        </center>   

        <script>
        setTimeout(function() {
            location.replace("/Turnieje/TeamCreateManage/ManageTeam.jsp?teamName="+"<%= request.getParameter("teamName") %>"+"");
        }, 2000);
        </script>
    </body>
</html>
