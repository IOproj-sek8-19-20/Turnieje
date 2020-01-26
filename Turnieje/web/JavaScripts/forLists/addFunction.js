//Funkcja odpowiedzialna za przenoszenie pozycji z 
//listy "do wyboru" do listy "wybrane"
function addFunction(toAdd)
{
    var iframe = document.getElementById("Avaible"+toAdd);   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosed"+toAdd);   //dobieram sie do listy druzyn
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy

    var iframe2 = document.getElementById("Choosed"+toAdd);   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosed"+toAdd);   //dobieram sie do listy druzyn
    var option = document.createElement("option");
    option.text = options[select.selectedIndex].text;
    select2.add(option);

    select.remove(select.selectedIndex);
    myCountingFunction(toAdd);
}