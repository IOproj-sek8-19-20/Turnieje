<%-- 
    Document   : ManageTournament
    Author     : Daniel Kaleta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Edytuj turniej</title>
    </head>

    <script>var toInit="Teams"</script>
    <body onload="init(toInit);setFieldsAndLists();myCountingFunction(toInit)">
        
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }

        String toEditName = request.getParameter("tournamentName");
        Integer toEditTeamSize = (Integer) session.getAttribute("tournamentToEditTeamSize");
    %>

    <center>
        
    <h1>Edytujesz turniej: <%= toEditName %> </h1>
    
        Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName" value="<%= toEditName %>">
        
        <br/><br/>
        
        <!-- Administrator -->
        Administrator: <input type = "text" name = "admin" id="admin" value="<%= userEmail %>">
        
        <br/><br/>
        
        Dyscyplina: 
            
        <br/><br/>
        
        <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
        
            
        <br/><br/>
        
        Tryb rozgrywek:
            <select name="tournamentMode" size="1" id="tournamentMode">
                    <option>Tryb 1</option>
                    <option>Tryb 2</option>
            </select>
            
            <br/><br/>
            
            Rozmiar drużyn: <input type="number" id="teamSize" name="teamSize" value="<%= toEditTeamSize %>" min="1">
            
            <br/><br/>
            
            Drużyny 
            <br/>
            Do dodania: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodane:
            
            <br/><br/>

        <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
        <iframe id="ChoosedTeams" src="/Turnieje/Lists/TeamsList.jsp?inTournament=true" width="300" height="150"></iframe>

        <br/><br/>
        
        <input type = "submit" value = "Zatwierdz" onclick="temp()">
        
        <!--
        Powrót do menu glownego
        -->
        <form action = "http://localhost:8080/Turnieje//MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>

    </center>
    
        <script> 
            //Funkcja temp wywoluje tylko funkcje submit z odpowiednim argumentem, aby skrypt 
            //tworz?cy/edytuj?cy turniej wiedzia?, jaki servlet wywo?a? (edycji lub tworzenia)
            function temp()
            {
                var myVar="Manage";
                submit(myVar);
            }
            //Funkcja do ustawienia nazwy, dyscypliny, trybu rozgrywek, rozmiaru druzyn, oraz druzyn w turnieju
            function setFieldsAndLists()
            {
                //Ustawianie dyscypliny
                var iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
                var select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn
                var options = select.getElementsByTagName('option');
                
                var actualDiscipline = "<%= (String) session.getAttribute("tournamentToEditDiscipline") %>";
                
                for ( var i = 0; i < select.length; i++ ) 
                {
                    if ( select.options[i].text == actualDiscipline ) {
                        select.selectedIndex = i;
                    }
                }
                
                //Ustawianie trybu rozgrywek
                var tournamentMode = document.getElementById("tournamentMode"); 
                options = tournamentMode.getElementsByTagName('option');
                for (var i = 0; i < tournamentMode.length; i++) 
                {
                    tournamentMode.selectedIndex = 0;
                }
            }
        </script>
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTournamentSubmit.js"></script>
    </body>
</html>


