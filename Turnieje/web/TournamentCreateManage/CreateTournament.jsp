<%-- 
    Document   : CreateTournament
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> <html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
    <title>Stwórz turniej</title>
</head>
<%
    //Sprawdzanie, czy uzytkownik jest zalogowany
    String userEmail = (String) session.getAttribute("loggedUser");
    if(userEmail == null)
    {
        response.sendRedirect("/Turnieje/Login.jsp");
        return;
    }
    
    Set<String> tournamentModes = (Set<String>) session.getAttribute("tournamentModes");

    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
%>
    <script>var toInit="Teams"</script>
    <body onload="init(toInit),myCountingFunction(toInit)">
        <center>
            
            <h1>Tworzenie turnieju</h1>
            
            <!--<form action = "CreateTournament" method="get">-->
            Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName">

            <br/><br/>

            <!-- Administrator -->
            Administrator: <input type = "text" name = "admin" id="admin" value="<%= userEmail %>" readonly>
            
            <br/><br/>
            
            Dyscyplina: 
            
            <br/><br/>

            <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            
            <br/><br/>
            
            Tryb rozgrywek:
            <select name="tournamentMode" size="1" id="tournamentMode">
            
            <% for(String mode: tournamentModes)
                { %>
                    <option> <%= mode %></option>
                <% } %>
            </select>
            
            <br/><br/>
            
            Sugerowany rozmiar drużyn: <input type="number" id="teamSize" name="teamSize" value="1" min="1">
            
            <br/><br/>
        
            Data rozpoczęcia: <input type="date" id="startDate" name="startDate" value="<%= ft.format(date)%>" min="<%= ft.format(date)%>">
            
            <br/><br/>
            
            Data zakończenia: <input type="date" id="endDate" name="endDate" value="<%= ft.format(date)%>" min="<%= ft.format(date)%>">
            
            <br/><br/>
            
            Drużyny 
            
            <br/><br/>

            <div id="container">
                <div id="avaible">
                    Dostępne: 
                    <br/>
                    <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
                </div>
                <div id="added">
                    Dodane:
                    <br/>
                    <iframe id="ChoosedTeams" src="/Turnieje/Lists/TeamsList.jsp?inTournament=true" width="300" height="150"></iframe>
                </div>
            </div>
            
            <br/><br/>
                <input type = "submit" value = "Zatwierdź" onclick="temp()">

            
        <!--
        Powrót do menu g?ównego
        -->
        <br/>
        <form action = "/Turnieje/MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrót">
        </form>
        
        </center>
            
        <script> 
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
    <script src="/Turnieje/JavaScripts/submits/createTournamentSubmit.js"></script>
    </body>
</html>


