function submit(myVar)
{
    //funkcja ta w GET wysyla JSONa zawierajacego nazwe turnieju, tablice jej druzyn czlonkowskich,
    //oraz wybrana dyscypline.
    //sekcja nazwy turnieju
    if(document.getElementById("tournamentName").value=="")
    {
        alert("Wprowadz nazwe turnieju!");
        return;
    }
    var JSONToSend = "{\"name\":\"" + document.getElementById("tournamentName").value + "\",";

    //sekcja druzyn do dodania
    JSONToSend = JSONToSend + " \"teamsToAdd\": [";

    var iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedTeams");
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy
    var i;
    console.log(JSONToSend);
    var JSONArrayOfUsersToAdd = "";
    for (i = 0; i < options.length; i++)
    {
        JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd + "\"" + options[i].text + "\", ";
    }
    //usuwanie ostatniego ", " z druzyn do dodania
    JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd.substring(0, JSONArrayOfUsersToAdd.length - 2);
    console.log(JSONArrayOfUsersToAdd);
    //dodanie do JSONa znaku konca tablicy
    JSONToSend = JSONToSend + JSONArrayOfUsersToAdd + "]";

    //sekcja odpowiedzialna za dziedzine
    iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
    select = iframe.contentWindow.document.getElementById("choosedDisciplines");
    options = select.getElementsByTagName('option');    //pobieram opcje z listy
    if(options[select.selectedIndex]==null)
    {
        alert("Wybierz dyscypline!");
        return;
    }
    var choosedDiscipline = options[select.selectedIndex].text;

    JSONToSend = JSONToSend + " , \"discipline\": " + "\"" + choosedDiscipline + "\"";

    //sekcja odpowiedzialna za rozmiar druzyny
    var teamSize = document.getElementById("teamSize").value; 
    JSONToSend = JSONToSend + " , \"teamSize\": " + "\"" + teamSize + "\"";

    //typ turnieju
    var type = document.getElementById("tournamentMode").value; 
    JSONToSend = JSONToSend + " , \"type\": " + "\"" + type + "\"";

    //sekcja odpowiedzialna za date rozpoczecia
    var startDate = document.getElementById("startDate").value; 
    JSONToSend = JSONToSend + " , \"startDate\": " + "\"" + startDate + "\"";

    //sekcja odpowiedzialna za date zakonczenia
    var endDate = document.getElementById("endDate").value;  
    JSONToSend = JSONToSend + " , \"endDate\": " + "\"" + endDate + "\" }";

    console.log(JSONToSend);
    location.replace("/Turnieje/"+myVar+"Tournament?JSONFromCreateTournament=" + JSONToSend);
}