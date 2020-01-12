//Funkcja odpowiedzialna za przenoszenie pozycji z 
//listy "wybrane" do listy "do wyboru"
function deleteFunction(toDelete)
{
    var iframe = document.getElementById("Choosed"+toDelete);   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosed"+toDelete);   //dobieram sie do listy druzyn
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy

    var iframe2 = document.getElementById("Avaible"+toDelete);   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosed"+toDelete);   //dobieram sie do listy druzyn
    var option = document.createElement("option");
    option.text = options[select.selectedIndex].text;
    select2.add(option);

    select.remove(select.selectedIndex);
}