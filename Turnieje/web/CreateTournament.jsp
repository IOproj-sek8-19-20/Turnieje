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
            
            
            <form action = "CreateTournament" method="get">
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
            
            
            </form>
            
            <br/><br/>
                <input type = "submit" value = "Zatwierdz" onclick="submit()">
            
            
        <script src="/Turnieje/JavaScripts/initFunction.js"></script>
        <script src="/Turnieje/JavaScripts/addFunction.js"></script>
        <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>
        
        <script>
            function submit()
            {
                //funkcja ta w GET wysyla JSONa zawierajacego nazwe turnieju, tablice jej druzyn czlonkowskich,
                //oraz wybrana dyscypline.
                //sekcja nazwy turnieju
                var JSONToSend = "{\"name\":\"" + document.getElementById("tournamentName").value + "\",";

                //sekcja druzyn do dodania
                JSONToSend = JSONToSend + " \"teamsToAdd\": [";

                var iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
                var select = iframe.contentWindow.document.getElementById("choosedTeams");
                var options = select.getElementsByTagName('option');    //pobieram opcje z listy
                var i;
                console.log(JSONToSend);
                var JSONArrayOfUsersToAdd = "";
                for (i = 0; i < options.length; i++)
                {
                    JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd + "\"" + options[i].text + "\", ";
                }
                //usuwanie ostatniego ", " z druzyn do dodania
                JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd.substring(0, JSONArrayOfUsersToAdd.length - 2);
                console.log(JSONArrayOfUsersToAdd);
                //dodanie do JSONa znaku konca tablicy
                JSONToSend = JSONToSend + JSONArrayOfUsersToAdd + "]";

                //sekcja odpowiedzialna za dziedzine
                iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedDisciplines");
                options = select.getElementsByTagName('option');    //pobieram opcje z listy
                var choosedDiscipline = options[select.selectedIndex].text;

                JSONToSend = JSONToSend + " , \"discipline\": " + "\"" + choosedDiscipline + "\"";
                
                //sekcja odpowiedzialna za rozmiar druzyny
                var teamSize = document.getElementById("teamSize").value; 
                JSONToSend = JSONToSend + " , \"teamSize\": " + "\"" + teamSize + "\"";
                
                //typ turnieju
                var type = document.getElementById("tournamentMode").value; 
                JSONToSend = JSONToSend + " , \"type\": " + "\"" + type + "\"";
                
                //sekcja odpowiedzialna za date rozpoczecia
                var startDate = document.getElementById("startDate").value; 
                JSONToSend = JSONToSend + " , \"startDate\": " + "\"" + startDate + "\"";
                
                //sekcja odpowiedzialna za date zakonczenia
                var endDate = document.getElementById("endDate").value;  
                JSONToSend = JSONToSend + " , \"endDate\": " + "\"" + endDate + "\" }";

                console.log(JSONToSend);
                location.replace("/Turnieje/CreateTournament?JSONFromCreateTournament=" + JSONToSend);
            }


            </script>
            
        
        </center>
    </body>
</html>


