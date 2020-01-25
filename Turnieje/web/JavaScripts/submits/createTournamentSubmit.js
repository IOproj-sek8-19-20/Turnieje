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
    
    //Administrator turnieju
    JSONToSend = JSONToSend + "\"admin\":\"" + document.getElementById("admin").value + "\",";

    //sekcja druzyn do dodania
    JSONToSend = JSONToSend + " \"teamsToAdd\": [";

    var iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedTeams");
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy
    var n = options.length;
    if(n==0)
    {
        alert("Brak drużyn!");
        return;
    }
    else
    {
        while(n%2 == 0)
        {
            n/=2;
        }
        if(n == 0 || n != 1)
        {
            alert("Liczba drużyn NIE jest potęgą dwójki");
            return;
        }
    }
    var i;
    console.log(JSONToSend);
    var JSONArrayOfTeamsToAdd = "";
    for (i = 0; i < options.length; i++)
    {
        JSONArrayOfTeamsToAdd = JSONArrayOfTeamsToAdd + "\"" + options[i].text + "\", ";
    }
    //usuwanie ostatniego ", " z druzyn do dodania
    JSONArrayOfTeamsToAdd = JSONArrayOfTeamsToAdd.substring(0, JSONArrayOfTeamsToAdd.length - 2);
    console.log(JSONArrayOfTeamsToAdd);
    //dodanie do JSONa znaku konca tablicy
    JSONToSend = JSONToSend + JSONArrayOfTeamsToAdd + "]";

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
    if(document.getElementById("startDate")!=null)
    {
        var startDate = document.getElementById("startDate").value; 
        JSONToSend = JSONToSend + " , \"startDate\": " + "\"" + startDate + "\"";
    }

    //sekcja odpowiedzialna za date zakonczenia
    if(document.getElementById("endDate")!=null)
    {
        var endDate = document.getElementById("endDate").value;  
        JSONToSend = JSONToSend + " , \"endDate\": " + "\"" + endDate + "\" }";
    }
    else
    {
        JSONToSend = JSONToSend + " }";
    }

    console.log(JSONToSend);
    location = "/Turnieje/"+myVar+"Tournament?JSONFromCreateTournament=" + JSONToSend;
}