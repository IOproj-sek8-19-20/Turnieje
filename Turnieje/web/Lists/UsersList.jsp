<%-- 
    Document   : UsersList
    Created on : 2020-01-08, 13:20:06
    Author     : Daniel Kaleta
--%>
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
            Set<String> allUsers = (Set<String>) session.getAttribute("allUsers");
            Set<String> usersInTeam = (Set<String>) session.getAttribute("usersInTeam");

            boolean inTeam = Boolean.parseBoolean(request.getParameter("inTeam"));
        %>
        
        <script>var toFilter="Users"</script>
        Nazwa: <input type = "text" name = "searchUsers" id="searchUsers" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedUsers" size="6" style="width:100%;" id="choosedUsers">
            <% if(inTeam!=true){
                for(String user: allUsers) {%>
                <option><%= user.toString()%></option>
            <%}}
            else{
                for(String user: usersInTeam) {%>
                <option><%= user.toString()%></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort = "Users";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false); 
            window.onload = mySortingFunction(toSort);
        </script>
    </body>
</html>