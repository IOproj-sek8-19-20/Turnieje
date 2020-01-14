<%-- 
    Document   : ShowTournaments
    Created on : 2020-01-14, 11:57:36
    Author     : Daniel-Laptop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show tournaments</title>
    </head>
    <body>
    <center>
        Turnieje (testowo):
        
        <br/><br/>
        
        <iframe id="Tournaments" src="/Turnieje/Lists/TournamentsList.jsp"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz turniej" onclick="submitShowTour()">
        
    </center>
        
    <script>
        function submitShowTour()
        {
            var iframe = document.getElementById("Tournaments");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedTournaments");   //dobieram sie do listy turnieji
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            location.replace("/Turnieje/TournamentView.jsp?tournamentName="+options[select.selectedIndex].text);
        }
    </script>
    </body>
</html>
