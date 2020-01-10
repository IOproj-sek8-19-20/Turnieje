function mySortingFunction(toSort) {
    var select, options, i, typeOfSorting;
    typeOfSorting = sort.options[sort.selectedIndex].value;

    select = document.getElementById("choosed"+toSort);
    options = select.getElementsByTagName('option');
    options = Array.prototype.slice.call(options);

    if(typeOfSorting.localeCompare("A-Z")==0)
    {
        options.sort(function(a, b)
        {
          return a.value.localeCompare(b.value);
        });
    }
    else if(typeOfSorting.localeCompare("Z-A")==0)
    {
        options.sort(function(a, b)
        {
          return (-1)*a.value.localeCompare(b.value);
        });
    }

    for (i = 0; i < select.length; i++) {
        select.add(options[i])
    }
}