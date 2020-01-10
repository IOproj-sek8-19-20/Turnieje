<%-- 
    Document   : UsersList
    Created on : 2020-01-08, 13:20:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            Set<String> users = new TreeSet<String>();
            users.add("Daniel Kaleta");
            users.add("Daniel Tarnecki");
            users.add("Piotr Uhl");
            users.add("Wojtek Woś");
            users.add("Adam Adamski");
            users.add("Mariusz Drynda");
        %>
        
        <script>var toFilter="Users"</script>
        Nazwa: <input type = "text" name = "searchUsers" id="searchUsers" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedUsers" size="7" style="width:100%;" id="choosedUsers">
            <% for(String user: users) {%>
                <option><%= user%></option>
            <%} %>
        </select>

        <script src="/Turnieje/JavaScripts/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Users";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);   
        </script>
    </body>
</html>