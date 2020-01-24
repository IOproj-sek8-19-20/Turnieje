<%-- 
    Document   : CreateTournament
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.User"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> <html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stwórz turniej</title>
</head>
<%
    //Sprawdzanie, czy uzytkownik jest zalogowany
    User user = (User) session.getAttribute("loggedUser");
    if(user == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }

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
            Administrator: <input type = "text" name = "admin" id="admin" value="<%= user.getEmail() %>">
            
            <br/><br/>
            
            Dyscyplina: 
            
            <br/><br/>

            <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            
            <br/><br/>
            
            Tryb rozgrywek:
            <select name="tournamentMode" size="1" id="tournamentMode">
            
            <% for(TournamentMode mode: TournamentMode.values())
                { %>
                    <option> <%= mode.toString() %></option>
                <% } %>
            </select>
            
            <br/><br/>
            
            Rozmiar drużyn: <input type="number" id="teamSize" name="teamSize" value="1" min="1">
            
            <br/><br/>
        
            Data rozpoczęcia: <input type="date" id="startDate" name="startDate" value="<%=ft.format(date)%>" min="<%=ft.format(date)%>">
            
            <br/><br/>
            
            Data zakończenia: <input type="date" id="endDate" name="endDate" value="<%=ft.format(date)%>" min="<%=ft.format(date)%>">
            
            <br/><br/>
            
            Drużyny 
            <br/>
            Do dodania: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodane:
            
            <br/><br/>

            <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
            <iframe id="ChoosedTeams" src="/Turnieje/Lists/TeamsList.jsp?inTournament=true" width="300" height="150"></iframe>
            
            <br/>
            
            <!--</form>-->
            
            <br/><br/>
                <input type = "submit" value = "Zatwierdz" onclick="temp()">

            
        <!--
        Powrót do menu g?ównego
        -->
        <br/>
        <form action = "http://localhost:8080/Turnieje//MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
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


