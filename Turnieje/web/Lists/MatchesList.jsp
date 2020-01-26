<%-- 
    Document   : MatchesList.jsp
    Created on : 2020-01-25, 17:14:26
    Author     : Danielowy Eltech
--%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Matches list</title>
    </head>
    <body>

        <%
            Set<String> matches = (Set<String>) session.getAttribute("matchesToShow");
        %>
        
        <script>var toFilter="Matches"</script>
        Nazwa: <input type = "text" name = "searchMatches" id="searchMatches" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedMatches" size="6" style="width:100%;" id="choosedMatches">
            <%for(String match: matches) {%>
                <option><%= match %></option>
            <%}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Matches";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
            window.onload = mySortingFunction(toSort);
        </script>
    </body>
</html>

