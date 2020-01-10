function deleteTeam()
{
    var iframe = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy

    var iframe2 = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosedTeam");   //dobieram sie do listy druzyn
    var option = document.createElement("option");
    option.text = options[select.selectedIndex].text;
    select2.add(option);

    select.remove(select.selectedIndex);
}