<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj turniej</title>
    </head>

    <script>var toInit="Teams"</script>
    <body onload="init(toInit);setDiscipline();">

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
            
            Rozmiar druzyn: <input type="number" id="teamSize" name="teamSize" value="1" min="1">
            
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

    </center>
    
    <%
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
    
        <script> 
            function temp()
            {
                var myVar="Manage";
                submit(myVar);
            }
            function setDiscipline()
            {
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
                
                
                var iframe2 = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
                var select2 = iframe2.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
                
                <% 
                    JSONArray teams = JSON.getJSONArray("teamsToAdd");
                    for (int i = 0; i < teams.length(); i++) 
                    {
                        %>
                            var option = document.createElement("option");
                            option.text = "<%= teams.getString(i)%>";
                            select2.add(option);
                        <%
                    }
                %>
                        
                var iframe3 = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
                var select3 = iframe3.contentWindow.document.getElementById("choosedTeams");   //dobieram sie do listy druzyn
                options = select3.getElementsByTagName('option');
                
                <% 
                    for (int i = 0; i < teams.length(); i++) 
                    {
                        %>
                            for (var i = 0; i < select3.length; i++) 
                            {
                                if(options[i].value == "<%= teams.getString(i)%>")
                                {
                                    select3.remove(i);
                                }
                            }
                        <%
                    }
                %>
            

            }
        </script>
        <script src="/Turnieje/JavaScripts/initFunction.js"></script>
        <script src="/Turnieje/JavaScripts/addFunction.js"></script>
        <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>
        <script src="/Turnieje/JavaScripts/createTournamentSubmit.js"></script>
    </body>
</html>


