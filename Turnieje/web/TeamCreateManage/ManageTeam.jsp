<%-- 
    Document   : ManageTeam
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Edytuj drużynę</title>
    </head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);setFieldsAndLists();myCountingFunction(toInit);myCountingFunction(toInit2)">
        
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
        
        String acutalTeamCaptain = (String) session.getAttribute("teamCaptainEmail");
        String acutalTeam = request.getParameter("teamName");
    %>

    <center>
        
    <h1>Edytujesz drużynę: <%= acutalTeam %> </h1>
    
    
    <!--<form action = "ManageTeam" method="get">-->
        
        <!-- Nazwa druzyny -->
        Nazwa drużyny : <input type = "text" name = "teamName" id="teamName" value="<%= acutalTeam %>">
        
        <br/><br/>
        
        <!-- Kapitan -->
        Kapitan (email): <input type = "text" name = "captain" id="captain" value="<%= acutalTeamCaptain %>">
        
        <br/><br/>
       
        <!-- Dodawanie zawodników (poza lista raczej zbedne
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

        <br/><br/>-->

        Edytuj zawodników (wybierz z listy) :

        <br/><br/>

        <div id="container">
            <div id="avaible">
                Dostępni: 
                <br/>
                <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
            </div>
            <div id="added">
                Dodani:
                <br/>
                <iframe id="ChoosedUsers" src="/Turnieje/Lists/UsersList.jsp?inTeam=true"></iframe>
            </div>
        </div>

        <br/><br/>

        Edytuj dyscypliny (wybierz z listy) :

        <br/><br/>

        <div id="container">
            <div id="avaible">
                Dostępne: 
                <br/>
                <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            </div>
            <div id="added">
                Dodane:
                <br/>
                <iframe id="ChoosedDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp?added=true"></iframe>
            </div>
        </div>
        
        <br/><br/>
    <!--</form>-->
    
    <input type = "submit" value = "Zatwierdź" onclick="temp()">
    
    <br/><br/>
    
    <!--
    Powrót do menu g?ównego
    -->
    <form action = "http://localhost:8080/Turnieje/MainMenu.jsp" method="get">
        <input type = "submit" value = "Powrót">
    </form>

    </center>
    
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTeamSubmit.js"></script>
    <script>
        function temp()
        {
            var captainIncluded = false;
            var captain = document.getElementById("captain").value;
            var iframe = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            for (var i = 0, optionsLength = options.length; i < optionsLength; i++) 
            {
                if (options[i].value == captain) 
                {
                    captainIncluded=true;
                    break;
                }
            }
            if(captainIncluded==false)
            {
                alert("Brak kapitana w drużynie!");
                return;
            }
            
            var myVar="Manage";
            submit(myVar);
        }
    </script>
    </body>
</html>


