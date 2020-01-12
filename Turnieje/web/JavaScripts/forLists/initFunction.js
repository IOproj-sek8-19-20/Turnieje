//Funkcja odpowiedzialna tylko za dodanie EventListenerów do list 
//"do wyboru" oraz "wybrane" z argumentami
function init(toInit)
{
    var iframe = document.getElementById("Avaible"+toInit);   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosed"+toInit);
    select.addEventListener("click", addFunction.bind(this,toInit),false);

    var iframe2 = document.getElementById("Choosed"+toInit);   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosed"+toInit);
    select2.addEventListener("click", deleteFunction.bind(this,toInit),false);
}