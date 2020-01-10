<%-- 
    Document   : DisciplinesList
    Created on : 2020-01-09, 18:28:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choosed Teams list</title>
    </head>
    <body>

        <script>var toFilter="Teams"</script>
        Nazwa: <input type = "text" name = "searchTeams" id="searchTeams" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedTeams" size="7" style="width:100%;" id="choosedTeams">
        </select>

        
        <script src="/Turnieje/JavaScripts/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Teams";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
        </script>
    </body>
</html>