<%-- 
    Document   : TeamsList
    Created on : 2020-01-09, 18:28:06
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.ITeamRepository"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teams list</title>
    </head>
    <body>

        <%
            RepositoryProvider repositoryProvider = RepositoryProvider.getInstance();
            ITeamRepository teamRepository = repositoryProvider.getTeamRepository();
            Set<Team> teams = teamRepository.getAll();

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
                for(Team team: teams) {%>
                <option><%= team.getName()%></option>
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