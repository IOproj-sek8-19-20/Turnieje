function myFilterFunction(toFilter) {
    console.log(toFilter);
    var input, filter, select, options, i, txtValue;
    input = document.getElementById('search'+toFilter);
    filter = input.value.toUpperCase();
    select = document.getElementById("choosed"+toFilter);
    options = select.getElementsByTagName('option');

    for (i = 0; i < select.length; i++) {
      txtValue = options[i].value;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        select[i].style.display = "";
      } else {
        select[i].style.display = "none";
      }
    }
}