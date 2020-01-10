<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj druzyne</title>
    </head>

    <body onload="init()">

    <center>
        
    <h1>Edytujesz druzyne: <%= request.getParameter("teamName") %> </h1>
    
    <form action = "ManageTeam" method="get">
        
        <!-- Nazwa druzyny -->
        Nazwa druzyny : <input type = "text" name = "teamName" id="teamName">
        
        <br/><br/>
        
        <!-- Dodawanie zawodników -->
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

        <br/><br/>

        Dodaj zawodnika (wybierz z listy) :

        <br/>

        Dostepni: 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Dodani:

        <br/><br/>

        <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
        <iframe id="ChoosedUsers" src="/Turnieje/Lists/ChoosedUsersList.jsp"></iframe>

        <br/><br/>
        
        <input type = "submit" value = "Zatwierdz">
        
    </form>
    
    <br/>
    
    <form action = "index.html" method="get">
        <input type = "submit" value = "Powrot">
    </form>

    </center>
    
    <script src="/Turnieje/JavaScripts/initUsers.js"></script>
    <script src="/Turnieje/JavaScripts/addUsers.js"></script>
    <script src="/Turnieje/JavaScripts/deleteUsers.js"></script>
    
    </body>
</html>


