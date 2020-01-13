<%-- 
    Document   : CreateTeam
    Author     : Daniel Kaleta
--%>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Stworz druzyne</title>
    </head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);">
    <%
        //Sprawdzanie zalogowania
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

    %>
    <center>

        <h1>Tworzenie druzyny</h1>

        <!--form w akutalej wersji nie za bardzo uzyteczny 
        <form action="ManageTeam" method="get" id="myForm"> -->

            <!-- Nazwa druzyny -->
            Nazwa druzyny : <input type = "text" name = "teamName" id="teamName">

            <br/><br/>
            
            Kapitan: <%=user%>
            
             <br/><br/>

            <!-- Dodawanie zawodników po mailu, ewentualnie do implementacji
            Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

            <br/><br/>-->
            
            <!-- Dodawanie zawodników -->

            Dodaj zawodnika (wybierz z listy) :

            <br/>

            Dostepni: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodani:

            <br/><br/>

            <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
            <iframe id="ChoosedUsers" src="/Turnieje/Lists/UsersList.jsp?Empty=true"></iframe>

            <br/><br/>

            Dodaj dyscypliny (wybierz z listy) :

            <br/>

            Dostepne: 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            Dodane:

            <br/><br/>

            <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            <iframe id="ChoosedDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp?Empty=true"></iframe>

            <br/><br/>
            
            <input type = "submit" value = "Zatwierdz" onclick="temp()">
            
        <!--
        Powrót do menu g?ównego
        -->
        <form action = "http://localhost:8080/Turnieje//MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>
            
        <!--</form>-->
    </center>
    
    <script> 
        function temp()
        {
            var myVar="Create";
            var captain = "<%= user%>";
            submit(myVar, captain);
        }
    </script>
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTeamSubmit.js"></script>
    
    </body>
</html>


