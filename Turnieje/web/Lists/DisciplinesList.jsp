<%-- 
    Document   : DisciplinesList
    Created on : 2020-01-09, 17:00:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Disciplines list</title>
    </head>
    <body>

        <%
            Set<String> teamDisciplines = (Set<String>) session.getAttribute("teamDisciplines");
            Set<String> notTeamDisciplines = (Set<String>) session.getAttribute("notTeamDisciplines");
            
            boolean added = Boolean.parseBoolean(request.getParameter("added"));
        %>
        
        <script>var toFilter="Disciplines"</script>
        
        <label for="searchDisciplines">Nazwa:</label>
        <input type = "text" name = "searchDisciplines" id="searchDisciplines" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedDisciplines" size="6" style="width:100%;" id="choosedDisciplines">
            <% if(added!=true){
                for(String discipline: notTeamDisciplines) {%>
                <option><%= discipline %></option>
            <%}}
            else if(added==true){
                for(String discipline: teamDisciplines) {%>
                <option><%= discipline %></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Disciplines";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false); 
            window.onload = mySortingFunction(toSort);
        </script>
    </body>
</html>