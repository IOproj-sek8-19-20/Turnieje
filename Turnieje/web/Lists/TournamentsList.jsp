<%-- 
    Document   : TournamentsList
    Created on : 2020-01-12, 13:39:42
    Author     : Daniel-PC
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
            Set<String> tournaments = new TreeSet<String>();
            tournaments.add("Turniej 1");
            tournaments.add("Turniej 2");
            tournaments.add("Turniej 3");
            tournaments.add("Turniej 4");
        %>
        
        <script>var toFilter="Tournaments"</script>
        Nazwa: <input type = "text" name = "searchTournaments" id="searchTournaments" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedTournaments" size="7" style="width:100%;" id="choosedTournaments">
            <%for(String tournament: tournaments) {%>
                <option><%= tournament%></option>
            <%}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Tournaments";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
        </script>
    </body>
</html>
