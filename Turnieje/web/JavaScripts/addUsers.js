function addUser()
{
	console.log("wywolanie add");
	var iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
	var select = iframe.contentWindow.document.getElementById("choosedUser");   //dobieram sie do listy druzyn
	var options = select.getElementsByTagName('option');    //pobieram opcje z listy
	
	var iframe2 = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
	var select2 = iframe2.contentWindow.document.getElementById("choosedUser");   //dobieram sie do listy druzyn
	var option = document.createElement("option");
	option.text = options[select.selectedIndex].text;
	select2.add(option);
	
	select.remove(select.selectedIndex);
}