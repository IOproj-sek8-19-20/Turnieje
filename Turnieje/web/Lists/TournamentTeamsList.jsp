<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tournament teams list</title>
    </head>
    <body>

        <%
            Set<String> teams = new TreeSet<String>();
            teams.add("Team 1");
            teams.add("Team 6");
            teams.add("Team 4");
            teams.add("Team 3");
            boolean emptyList = Boolean.parseBoolean(request.getParameter("Empty"));
        %>
        
        <script>var toFilter="Teams"</script>
        Nazwa: <input type = "text" name = "searchTeams" id="searchTeams" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedTeams" size="7" style="width:100%;" id="choosedTeams">
            <% if(emptyList!=true){
                for(String team: teams) {%>
                <option><%= team%></option>
            <%}}%>
        </select>

        <script src="/Turnieje/JavaScripts/forLists/filterFunction.js"></script>
        <script src="/Turnieje/JavaScripts/forLists/sortingFunction.js"></script>
        <script>
            sort = document.getElementById("sorting");
            var toSort="Teams";
            sort.addEventListener("change", mySortingFunction.bind(this,toSort),false);  
        </script>
    </body>
</html>