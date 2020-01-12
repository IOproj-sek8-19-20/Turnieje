<%-- 
    Document   : ManageTournament
    Author     : Daniel Kaleta
--%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj turniej</title>
    </head>

    <script>var toInit="Teams"</script>
    <body onload="init(toInit);setFieldsAndLists();">
        
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
        
        //Pobieram z ciasteczka JSONa w kt�rym mam wszelkie informacje na temat turnieju
        //Uzyje go do wyswietlania poprawnych informacji na stronie edycji
        String JSONString=null;
        JSONObject JSON=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("aboutTournament")) {
                    JSONString = cookie.getValue();
                    JSON = new JSONObject(JSONString);
                    break;
                }
            }
        }
    %>

    <center>
        
    <h1>Edytujesz turniej: <%= request.getParameter("tournamentName") %> </h1>
    
        Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName" value="<%= request.getParameter("tournamentName") %>">
        
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
            
            Rozmiar druzyn: <input type="number" id="teamSize" name="teamSize" value="<%= JSON.getString("teamSize") %>" min="1">
            
            <br/><br/>
            
            Druzyny 
            <br/>
            Do dodania: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodane:
            
            <br/><br/>

        <iframe id="AvaibleTeams" src="/Turnieje/Lists/TeamsList.jsp" width="300" height="150"></iframe>
        <iframe id="ChoosedTeams" src="/Turnieje/Lists/TeamsList.jsp?Empty=true" width="300" height="150"></iframe>

        <br/><br/>
        
        <input type = "submit" value = "Zatwierdz" onclick="temp()">
        
        <!--
        Powr�t do menu g?�wnego
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
                var admin = <%=user%>
                submit(myVar,admin);
            }
            //Funkcja do ustawienia nazwy, dyscypliny, trybu rozgrywek, rozmiaru druzyn, oraz druzyn w turnieju
            function setFieldsAndLists()
            {
                //Ustawianie dyscypliny
                var iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
                var select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn
                var options = select.getElementsByTagName('option');
                
                var actualDiscipline = "<%= JSON.getString("discipline") %>";
                
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
                    if(options[i].value == "<%= JSON.getString("type") %>")
                    {
                        tournamentMode.selectedIndex = i;
                    }
                }
                
                //Ustawianie druzyn, ktore sa juz dodane
                iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
                
                <% 
                    JSONArray teams = JSON.getJSONArray("teamsToAdd");
                    for (int i = 0; i < teams.length(); i++) 
                    {
                        %>
                            var option = document.createElement("option");
                            option.text = "<%= teams.getString(i)%>";
                            select.add(option);
                        <%
                    }
                %>
                 
                //Usuniecie dodanych druzyn z listy dostepnych
                iframe = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
                options = select.getElementsByTagName('option');
                
                <% 
                    for (int i = 0; i < teams.length(); i++) 
                    {
                        %>
                            for (var i = 0; i < select.length; i++) 
                            {
                                if(options[i].value == "<%= teams.getString(i)%>")
                                {
                                    select.remove(i);
                                }
                            }
                        <%
                    }
                %>
            }
        </script>
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTournamentSubmit.js"></script>
    </body>
</html>


