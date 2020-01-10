function init()
{
    var iframe = document.getElementById("AvaibleTeams");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedTeam");
    select.addEventListener("click", addTeam);

    var iframe2 = document.getElementById("ChoosedTeams");   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosedTeam");
    select2.addEventListener("click", deleteTeam);
}