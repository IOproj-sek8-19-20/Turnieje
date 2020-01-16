<%-- 
    Document   : TournamentCreated
    Created on : 2020-01-09, 19:57:31
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Turniej utworzony</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <center>
            <h1> Turniej <%= request.getParameter("tournamentName")%> stworzony</h1>
        </center>   

        <script>
            setTimeout(function () 
            {
                    location.replace("/Turnieje/PrepareManageTournament?tournamentName=" + "<%= request.getParameter("tournamentName")%>");
            }, 2000);
        </script>
    </body>
</html>
