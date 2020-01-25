<%-- 
    Document   : ShowMatches
    Created on : 2020-01-25, 17:48:06
    Author     : Danielowy Eltech
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Show matches</title>
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
    
    <script>var toCount="Matches"</script>
    <body onload="myCountingFunction(toCount)">
    <center>
        Mecze (testowo):
        
        <br/><br/>
        
        <iframe id="Matches" src="/Turnieje/Lists/MatchesList.jsp"></iframe>
        
        <br/>
        
        <input type = "submit" value = "Pokaż mecz" onclick="submitEnterResult()">
        
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
        function submitEnterResult()
        {
            var iframe = document.getElementById("Matches");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedMatches");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            location = "/Turnieje/TeamView.jsp?teamName="+options[select.selectedIndex].text;
        }
        
        function submitEditTeam()
        {
            var iframe = document.getElementById("Teams");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            location = "/Turnieje/PrepareManageTeam?teamName="+options[select.selectedIndex].text;
        }
    </script>
    </body>
</html>


