<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj druzyne</title>
    </head>

    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);">

    <center>
        
    <h1>Edytujesz druzyne: <%= request.getParameter("teamName") %> </h1>
    
    
    <!--<form action = "ManageTeam" method="get">-->
        
        <!-- Nazwa druzyny -->
        Nazwa druzyny : <input type = "text" name = "teamName" id="teamName" value="<%= request.getParameter("teamName") %>">
        
        <br/><br/>
        
        <!-- Dodawanie zawodników (poza lista raczej zbedne
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
    </script>
    </body>
</html>


