<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj druzyne</title>
    </head>

    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);setFieldsAndLists();">
        
    <%
        //Pobieram z ciasteczka JSONa w kt�rym mam wszelkie informacje na temat turnieju
        //Uzyje go do wyswietlania poprawnych informacji na stronie edycji
        String JSONString=null;
        JSONObject JSON=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("aboutTeam")) {
                    JSONString = cookie.getValue();
                    JSON = new JSONObject(JSONString);
                    break;
                }
            }
        }
    %>

    <center>
        
    <h1>Edytujesz druzyne: <%= request.getParameter("teamName") %> </h1>
    
    
    <!--<form action = "ManageTeam" method="get">-->
        
        <!-- Nazwa druzyny -->
        Nazwa druzyny : <input type = "text" name = "teamName" id="teamName" value="<%= request.getParameter("teamName") %>">
        
        <br/><br/>
        
        <!-- Dodawanie zawodnik�w (poza lista raczej zbedne
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

        <br/><br/>-->

        Edytuj zawodnikow (wybierz z listy) :

        <br/>

        Dostepni: 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Dodani:

        <br/><br/>

        <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
        <iframe id="ChoosedUsers" src="/Turnieje/Lists/UsersList.jsp?Empty=true"></iframe>

        <br/><br/>

        Edytuj dyscypliny (wybierz z listy) :

        <br/>

        Dostepne: 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Dodane:

        <br/><br/>

        <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
        <iframe id="ChoosedDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp?Empty=true"></iframe>
        
        <br/><br/>
    <!--</form>-->
    
    <input type = "submit" value = "Zatwierdz" onclick="temp()">
    
    <br/><br/>
    
    <form action = "index.html" method="get">
        <input type = "submit" value = "Powrot">
    </form>

    </center>
    
    <script src="/Turnieje/JavaScripts/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/createTeamSubmit.js"></script>
    <script>
        function temp()
        {
            var type="Manage";
            submit(type);
        }
        function setFieldsAndLists()
            {
                //Ustawianie dyscyplin juz dodanych
                iframe = document.getElementById("ChoosedDisciplines");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn
                
                <% 
                    JSONArray disciplines = JSON.getJSONArray("disciplinesToAdd");
                    for (int i = 0; i < disciplines.length(); i++) 
                    {
                        %>
                            var option = document.createElement("option");
                            option.text = "<%= disciplines.getString(i)%>";
                            select.add(option);
                        <%
                    }
                %>
                 
                //Usuniecie dodanych dyscyplin z listy dostepnych
                iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn
                options = select.getElementsByTagName('option');
                
                <% 
                    for (int i = 0; i < disciplines.length(); i++) 
                    {
                        %>
                            for (var i = 0; i < select.length; i++) 
                            {
                                if(options[i].value == "<%= disciplines.getString(i)%>")
                                {
                                    select.remove(i);
                                }
                            }
                        <%
                    }
                %>
                
                //Ustawianie uzytkownikow, ktorzy sa juz dodani
                iframe = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
                
                <% 
                    JSONArray users = JSON.getJSONArray("usersToAdd");
                    for (int i = 0; i < users.length(); i++) 
                    {
                        %>
                            var option = document.createElement("option");
                            option.text = "<%= users.getString(i)%>";
                            select.add(option);
                        <%
                    }
                %>
                 
                //Usuniecie dodanych uzytkownikow z listy dostepnych
                iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
                options = select.getElementsByTagName('option');
                
                <% 
                    for (int i = 0; i < users.length(); i++) 
                    {
                        %>
                            for (var i = 0; i < select.length; i++) 
                            {
                                if(options[i].value == "<%= users.getString(i)%>")
                                {
                                    select.remove(i);
                                }
                            }
                        <%
                    }
                %>
            }
    </script>
    </body>
</html>


