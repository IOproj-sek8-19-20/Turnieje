<%-- 
    Document   : TeamsList
    Created on : 2020-01-09, 18:28:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Teams list</title>
    </head>
    <script>var toFilter="Teams"</script>
    <body>

        <%
            Set<String> allTeams = (Set<String>) session.getAttribute("allTeams");
            Set<String> teamsInTournament = (Set<String>) session.getAttribute("teamsInTournament");
            
            boolean inTournament = Boolean.parseBoolean(request.getParameter("inTournament"));
        %>
        

        <center>
            <label for="searchTeams">Nazwa:</label>
            <input type = "text" name = "searchTeams" id="searchTeams" onkeyup="myFilterFunction(toFilter)">
        
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedTeams" size="6" style="width:100%;" id="choosedTeams">
            <% if(inTournament!=true){
                for(String teamName: allTeams) {%>
                <option><%= teamName %></option>
            <%}}
            else{
                for(String teamName: teamsInTournament) {%>
                <option><%= teamName %></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort ="Teams";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);
            window.onload = mySortingFunction(toSort);
        </script>
    </body>
</html>