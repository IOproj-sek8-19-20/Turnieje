<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Stworz turniej</title>

</head>
    <script>var toInit="Teams"</script>
    <body onload="init(toInit)">
        <center>
            <br/>
            
            
            <!--<form action = "CreateTournament" method="get">-->
            Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName">

            <%
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

                /*Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                Date DatePlusOne = c.getTime();*/
            %>
            
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
            </center>
            
        <script> 
            function temp()
            {
                var myVar="Create";
                submit(myVar);
            }
        </script>
        <script src="/Turnieje/JavaScripts/initFunction.js"></script>
        <script src="/Turnieje/JavaScripts/addFunction.js"></script>
        <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>
        <script src="/Turnieje/JavaScripts/createTournamentSubmit.js"></script>
    </body>
</html>


