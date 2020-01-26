<%-- 
    Document   : CreateTeam
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Stwórz drużynę</title>
    </head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="addCaptain();init(toInit);init(toInit2);myCountingFunction(toInit);myCountingFunction(toInit2)">
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
    <center>

        <h1>Tworzenie drużyny</h1>

        <!--form w akutalej wersji nie za bardzo uzyteczny 
        <form action="ManageTeam" method="get" id="myForm"> -->

            <!-- Nazwa druzyny -->
            Nazwa druzyny : <input type = "text" name = "teamName" id="teamName">

            <br/><br/>
        
            <!-- Kapitan -->
            Kapitan (email): <input type = "text" name = "captain" id="captain" value="<%= userEmail %>" readonly>
            
             <br/><br/>

            <!-- Dodawanie zawodników po mailu, ewentualnie do implementacji
            Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

            <br/><br/>-->
            
            <!-- Dodawanie zawodników -->

            Dodaj zawodników (wybierz z listy) :
            
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

            Dodaj dyscypliny (wybierz z listy) :

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
            
            <input type = "submit" value = "Zatwierdź" onclick="temp()">
            
        <!--
        Powrót do menu g?ównego
        -->
        <form action = "http://localhost:8080/Turnieje//MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrót">
        </form>
            
        <!--</form>-->
    </center>
    
    <script> 
        function addCaptain()
        {
            var captainEmail = document.getElementById("captain").value;
            
            var iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            //usuwam kapitana z dostepnych zawodnikow
            for (var i = 0, optionsLength = options.length; i < optionsLength; i++) 
            {
                if (options[i].value == captainEmail) 
                {
                    select.remove(i);
                    break;
                }
            }
            
            //dodaje kapitana do dodanych zawodnikow
            var iframe2 = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
            var select2 = iframe2.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            var option = document.createElement("option");
            option.text = captainEmail;
            select2.add(option);

            myCountingFunction("Users");
        }
        function temp()
        {
            var myVar="Create";
            submit(myVar);
        }
    </script>
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTeamSubmit.js"></script>
    
    </body>
</html>


