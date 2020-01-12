<%-- 
    Document   : CreateTournament
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html> <html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stworz turniej</title>
</head>
<%
    //Sprawdzanie, czy uzytkownik jest zalogowany
    String user = null;
    if(session.getAttribute("loginUser") == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }
    else 
    {
        user = (String) session.getAttribute("loginUser");
    } 

    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

    /*Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.DATE, 1);
    Date DatePlusOne = c.getTime();*/
%>
    <script>var toInit="Teams"</script>
    <body onload="init(toInit)">
        <center>
            
             <h1>Tworzenie turnieju</h1>
            
            <!--<form action = "CreateTournament" method="get">-->
            Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName">


            
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
            
            Rozmiar druzyn: <input type="number" id="teamSize" name="teamSize" value="1" min="1">
            
            <br/><br/>
        
            Data rozpoczecia: <input type="date" id="startDate" name="startDate" value="<%=ft.format(date)%>" min="<%=ft.format(date)%>">
            
            <br/><br/>
            
            Data zakonczenia: <input type="date" id="endDate" name="endDate" value="<%=ft.format(date)%>" min="<%=ft.format(date)%>">
            
            <br/><br/>
            
            Druzyny 
            <br/>
            Do dodania: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodane:
            
            <br/><br/>

            <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
            <iframe id="ChoosedTeams" src="/Turnieje/Lists/TeamsList.jsp?Empty=true" width="300" height="150"></iframe>
            
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
                var admin = <%=user%>
                submit(myVar,admin);
            }
        </script>
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTournamentSubmit.js"></script>
    </body>
</html>


