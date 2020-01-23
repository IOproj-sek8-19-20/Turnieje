function myCountingFunction(toCount) {
    
    var iframe = document.getElementById("Avaible"+toCount);   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosed"+toCount);   //dobieram sie do listy druzyn
    var options = select.getElementsByTagName('option');  
    iframe.contentWindow.document.getElementById("amount").value = options.length;
    
    var iframe2 = document.getElementById("Choosed"+toCount);   //dobieram sie do iframe
    var select2 = iframe2.contentWindow.document.getElementById("choosed"+toCount);   //dobieram sie do listy druzyn
    var options2 = select2.getElementsByTagName('option');  
    iframe2.contentWindow.document.getElementById("amount").value = options2.length;
}