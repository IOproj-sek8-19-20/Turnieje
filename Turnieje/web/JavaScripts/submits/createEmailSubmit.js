function submit(myVar)
{
  
    if(document.getElementById("text").value=="")
    {
       alert("Nie mozesz wyslac pustej wiadomosci!");
       return;
    }
    var JSONToSend = "{\"sender\":\"" + document.getElementById("captain").value + "\",";
    
   // kapitan druzyny
   JSONToSend = JSONToSend + "\"text\":\"" + document.getElementById("text").value + "\",";

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
   JSONToSend = JSONToSend + JSONArrayOfUsersToAdd + "] }";
    console.log(JSONToSend);
    location = "/Turnieje/"+myVar+"Email?JSONFromCreateEmail="+JSONToSend;
}

