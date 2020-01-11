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
            boolean emptyList = Boolean.parseBoolean(request.getParameter("Empty"));
        %>
        
        <script>var toFilter="Disciplines"</script>
        Nazwa: <input type = "text" name = "searchDisciplines" id="searchDisciplines" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedDisciplines" size="7" style="width:100%;" id="choosedDisciplines">
            <% if(emptyList!=true){
                for(String discipline: disciplines) {%>
                <option><%= discipline%></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Disciplines";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
        </script>
    </body>
</html>