function init()
{
    var iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedUser");
    select.addEventListener("click", addUser);

    var iframe2 = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosedUser");
    select2.addEventListener("click", deleteUser);
}