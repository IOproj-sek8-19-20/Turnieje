<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Stworz druzyne</title>

</head>

    <body onload="init()">
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
                <iframe id="ChoosedUsers" src="/Turnieje/Lists/ChoosedUsersList.jsp"></iframe>

                <br/><br/>

                <input type = "submit" value = "Zatwierdz">

            </form>
            
        </center>
    
        <script src="/Turnieje/JavaScripts/initUsers.js"></script>
        <script src="/Turnieje/JavaScripts/addUsers.js"></script>
        <script src="/Turnieje/JavaScripts/deleteUsers.js"></script>
    </body>
</html>


