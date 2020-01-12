<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tournament users list</title>
    </head>
    <body>

        <%
            Set<String> users = new TreeSet<String>();
            users.add("Daniel Kaleta");
            users.add("Daniel Tarnecki");
            users.add("Piotr Uhl");
            users.add("Wojtek Wos");
            users.add("Adam Adamski");
            users.add("Mariusz Drynda");
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
                for(String user: users) {%>
                <option><%= user%></option>
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