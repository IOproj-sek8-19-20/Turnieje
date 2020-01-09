<%-- 
    Document   : DisciplinesList
    Created on : 2020-01-09, 17:00:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disciplines list</title>
    </head>
    <body>

        <%
            Set<String> disciplines = new TreeSet<String>();
            disciplines.add("Pilka nozna");
            disciplines.add("Koszykowka");
            disciplines.add("Szachy podwodne");
            disciplines.add("Bierki");
        %>
        
        Nazwa: <input type = "text" name = "searchDiscipline" id="searchDiscipline" onkeyup="myFilterFunction()">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedDiscipline" size="7" style="width:100%;" id="choosedDiscipline">
            <% for(String discipline: disciplines) {%>
                <option><%= discipline%></option>
            <%} %>
        </select>

        
        <script>
            sort = document.getElementById("sorting");
            sort.addEventListener("change", mySortingFunction);
            var defaultOrderOptions;
            
            function mySortingFunction() {
                var select, options, i, typeOfSorting;
                
                typeOfSorting = sort.options[sort.selectedIndex].value;
                
                select = document.getElementById("choosedDiscipline");
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
            
            function myFilterFunction() {
                var input, filter, select, options, i, txtValue;
                input = document.getElementById('searchDiscipline');
                filter = input.value.toUpperCase();
                select = document.getElementById("choosedDiscipline");
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
        </script>
    </body>
</html>