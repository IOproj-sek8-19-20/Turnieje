<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Stworz druzyne</title>

</head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit="Users", toInit2="Disciplines";</script>
    <body onload="init(toInit);init(toInit2);">
        <center>
            
            <br/>
            
            <form action = "CreateTeam" method="get">

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

                <input type = "submit" value = "Zatwierdz">

            </form>
            
        </center>
    
        <script src="/Turnieje/JavaScripts/initFunction.js"></script>
        <script src="/Turnieje/JavaScripts/addFunction.js"></script>
        <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>
    </body>
</html>


