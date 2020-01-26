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
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Show matches</title>
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
    
    <script>var toCount="Matches"</script>
    <body onload="myCountingFunction(toCount);init()">
    <center>
        Mecze (testowo):
        
        <br/><br/>
        
        <iframe id="Matches" src="/Turnieje/Lists/MatchesList.jsp"></iframe>
        
        <br/><br/>
        
        <input type="radio" name="team" value="" id="firstTeam">
        <label for="firstTeam" id="firstTeamLabel"></label>
       
        <input type="radio" name="team" value="" id="secondTeam">
        <label for="secondTeam" id="secondTeamLabel"></label>
        
        <br/><br/>

        <input type = "submit" value = "Wskaż zwycięzcę" onclick="submitEnterResult()" id="showMeTheWinner">
        
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
        function init()
        {
            var iframe = document.getElementById("Matches");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedMatches");   
            select.addEventListener("click", showTeams.bind(),false);
        }
        function showTeams()
        {
            var iframe = document.getElementById("Matches");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedMatches");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            var matchName = options[select.selectedIndex].text;
            var firstSpace = matchName.indexOf(' ',0);
            var secondSpace = matchName.indexOf(' ',(firstSpace+1));
            
            var firstTeamName = matchName.substring(0, firstSpace);
            var firstButton = document.getElementById("firstTeam");
            var firstButtonLabel = document.getElementById("firstTeamLabel");
            firstButton.value = firstTeamName;
            firstButtonLabel.innerText = firstTeamName;
            
            var secondTeamName = matchName.substring(secondSpace+1, matchName.length);
            var secondButton = document.getElementById("secondTeam");
            var secondButtonLabel = document.getElementById("secondTeamLabel");
            secondButton.value = secondTeamName;
            secondButtonLabel.innerText = secondTeamName;
        }
        function submitEnterResult()
        {
            var iframe = document.getElementById("Matches");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedMatches");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option'); 
            
            var firstButton = document.getElementById("firstTeam");
            var secondButton = document.getElementById("secondTeam");
            if(secondButton.checked == true)
            {
                var winner = secondButton.value;
            }
            else
            {
                var winner = firstButton.value;
            }
            location = "/Turnieje/EnterResult?match="+options[select.selectedIndex].text+"&winner="+winner;
        }
    </script>
    </body>
</html>


