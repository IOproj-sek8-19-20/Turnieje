<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Turnieje - stworz turniej</title>

</head>

    <body>
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

            <iframe src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            
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
            <iframe id="ChoosedTeams" src="/Turnieje/Lists/ChoosedTeamsList.jsp" width="300" height="150"></iframe>
            
            <br/>
            
            </form>
            
            <button onclick="addTeam()">Dodaj</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            rozwiazanie tymczasowe
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button onclick="deleteTeam()">Usun</button>
            
            <br/><br/>
                <input type = "submit" value = "Zatwierdz">
            </form>
            
            <script>
                function addTeam()
                {
                    var iframe = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
                    var select = iframe.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
                    var options = select.getElementsByTagName('option');    //pobieram opcje z listy
                    
                    var iframe2 = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
                    var select2 = iframe2.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
                    var option = document.createElement("option");
                    option.text = options[select.selectedIndex].text;
                    select2.add(option);
                    
                    select.remove(select.selectedIndex);
                }
                
                function deleteTeam()
                {
                    var iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
                    var select = iframe.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
                    var options = select.getElementsByTagName('option');    //pobieram opcje z listy
                    
                    var iframe2 = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
                    var select2 = iframe2.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
                    var option = document.createElement("option");
                    option.text = options[select.selectedIndex].text;
                    select2.add(option);
                    
                    select.remove(select.selectedIndex);
                }
            </script>
        </center>
    </body>
</html>


