<%-- 
    Document   : UsersList
    Created on : 2020-01-08, 13:20:06
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.User"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users list</title>
    </head>
    <body>

        <%
            Set<User> usersToShow = (Set<User>) session.getAttribute("usersToShow");

            boolean emptyList = Boolean.parseBoolean(request.getParameter("Empty"));
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
            <% if(emptyList!=true){
                for(User user: usersToShow) {%>
                <option><%= user.getEmail()%></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Users";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);   
        </script>
    </body>
</html>