<%-- 
    Document   : TeamsList
    Created on : 2020-01-09, 18:28:06
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teams list</title>
    </head>
    <script>var toFilter="Teams"</script>
    <body>

        <%
            Set<Team> allTeams = (Set<Team>) session.getAttribute("allTeams");
            Set<Team> teamsInTeam = (Set<Team>) session.getAttribute("teamsInTournament");
            
            boolean inTournament = Boolean.parseBoolean(request.getParameter("inTournament"));
        %>
        

        <center>
            
        Nazwa: <input type = "text" name = "searchTeams" id="searchTeams" onkeyup="myFilterFunction(toFilter)">
        
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedTeams" size="6" style="width:100%;" id="choosedTeams">
            <% if(inTournament!=true){
                for(Team team: allTeams) {%>
                <option><%= team.getName() %></option>
            <%}}
            else{
                for(Team team: teamsInTeam) {%>
                <option><%= team.getName() %></option>
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