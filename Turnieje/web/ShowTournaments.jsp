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
    
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
    
    <script>var toCount="Tournaments"</script>
    <body onload="myCountingFunction(toCount)">
    <center>
        Turnieje (testowo):
        
        <br/><br/>
        
        <iframe id="Tournaments" src="/Turnieje/Lists/TournamentsList.jsp"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz turniej" onclick="submitShowTour()">
        
        <input type = "submit" value = "Edytuj turniej" onclick="submitEditTour()">
        
        <br/><br/>
        
        <!--
        Powrót do menu glownego
        -->
        <form action = "http://localhost:8080/Turnieje//MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>
        
    </center>
        
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script>
        function submitShowTour()
        {
            var iframe = document.getElementById("Tournaments");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedTournaments");   //dobieram sie do listy turnieji
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            location = "/Turnieje/TournamentView.jsp?tournamentName="+options[select.selectedIndex].text;
        }
        
        function submitEditTour()
        {
            var iframe = document.getElementById("Tournaments");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedTournaments");   //dobieram sie do listy turnieji
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            location = "/Turnieje/PrepareManageTournament?tournamentName="+options[select.selectedIndex].text;
        }
    </script>
    </body>
</html>
