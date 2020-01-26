<%-- 
    Document   : ShowTeams
    Created on : 2020-01-16, 17:35:03
    Author     : Danielowy Eltech
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Show teams</title>
    </head>
    
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("/Turnieje/Login.jsp");
            return;
        }
    %>
    
    <script>var toCount="Teams"</script>
    <body onload="myCountingFunction(toCount)">
    <center>
        Druzyny (testowo):
        
        <br/><br/>
        
        <iframe id="Teams" src="/Turnieje/Lists/TeamsList.jsp"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz druzyne" onclick="submitShowTeam()">
        
        <input type = "submit" value = "Edytuj druzyne" onclick="submitEditTeam()">
        
        <br/><br/>
        
        <!--
        Powrót do menu glownego
        -->
        <form action = "/Turnieje/MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>
        
    </center>
        
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script>
        function submitShowTeam()
        {
            var iframe = document.getElementById("Teams");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
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

