<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Stworz druzyne</title>

    </head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);">
    <center>

        <br/>

        <!-- form w akutalej wersji nie za bardzo uzyteczny -->
        <form action = "CreateTeam" method="get" id="myForm">

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
        </form>

        <input type = "submit" value = "Zatwierdz" onclick="submit()">

    </center>

    <script src="/Turnieje/JavaScripts/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/deleteFunction.js"></script>

    <script>
            function submit()
            {
                //funkcja ta w GET wysyla JSONa zawierajacego nazwe druzyny, tablice jej czlonkow,
                //oraz tablice wybranych dyscyplin.
                //sekcja nazwy druzyny
                var JSONToSend = "{\"name\":\"" + document.getElementById("teamName").value + "\",";

                //sekcja uzytkownikow do dodania
                JSONToSend = JSONToSend + " \"usersToAdd\": [";

                var iframe = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
                var select = iframe.contentWindow.document.getElementById("choosedUsers");
                var options = select.getElementsByTagName('option');    //pobieram opcje z listy
                var i;
                console.log(JSONToSend);
                var JSONArrayOfUsersToAdd = "";
                for (i = 0; i < options.length; i++)
                {
                    JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd + "\"" + options[i].text + "\", ";
                }
                //usuwanie ostatniego ", " z uzytkownikow do dodania
                JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd.substring(0, JSONArrayOfUsersToAdd.length - 2);
                console.log(JSONArrayOfUsersToAdd);
                //dodanie do JSONa znaku konca tablicy
                JSONToSend = JSONToSend + JSONArrayOfUsersToAdd + "]";

                //sekcja odpowiedzialna za dziedziny
                iframe = document.getElementById("ChoosedDisciplines");   //dobieram sie do iframe
                select = iframe.contentWindow.document.getElementById("choosedDisciplines");
                options = select.getElementsByTagName('option');    //pobieram opcje z listy

                JSONToSend = JSONToSend + " , \"disciplinesToAdd\": [";

                var JSONArrayOfDisciplinesToAdd = "";
                for (i = 0; i < options.length; i++)
                {
                    JSONArrayOfDisciplinesToAdd = JSONArrayOfDisciplinesToAdd + "\"" + options[i].text + "\", ";
                }
                //usuwanie ostatniego ", " z uzytkownikow do dodania
                JSONArrayOfDisciplinesToAdd = JSONArrayOfDisciplinesToAdd.substring(0, JSONArrayOfDisciplinesToAdd.length - 2);
                console.log(JSONArrayOfDisciplinesToAdd);
                //dodanie do JSONa znaku konca tablicy i konca JSONa na ten moment
                JSONToSend = JSONToSend + JSONArrayOfDisciplinesToAdd + "] }";

                console.log(JSONToSend);
                location.replace("/Turnieje/CreateTeam?JSONFromCreateTeam=" + JSONToSend);
            }
    </script>
</body>
</html>


