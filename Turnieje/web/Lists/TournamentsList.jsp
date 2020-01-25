<%-- 
    Document   : TournamentsList
    Created on : 2020-01-12, 13:39:42
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Disciplines list</title>
    </head>
    <body>

        <%
            Set<String> tournaments = (Set<String>) session.getAttribute("tournamentsToShow");
        %>
        
        <script>var toFilter="Tournaments"</script>
        Nazwa: <input type = "text" name = "searchTournaments" id="searchTournaments" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedTournaments" size="6" style="width:100%;" id="choosedTournaments">
            <%for(String tournament: tournaments) {%>
                <option><%= tournament.toString()%></option>
            <%}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Tournaments";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
            window.onload = mySortingFunction(toSort);
        </script>
    </body>
</html>
