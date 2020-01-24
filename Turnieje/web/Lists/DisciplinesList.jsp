<%-- 
    Document   : DisciplinesList
    Created on : 2020-01-09, 17:00:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.HashSet"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Discipline"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
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
            Team actualTeam = (Team) session.getAttribute("actualTeam");
            Set<Discipline> teamDisciplines = new HashSet<Discipline>();
            Set<Discipline> notTeamDisciplines = new HashSet<Discipline>();
            
            if(actualTeam!=null)
            {
                teamDisciplines = actualTeam.getDisciplines();
                for(Discipline discipline: Discipline.values())
                {
                    if(teamDisciplines.contains(discipline))
                    {
                        continue;
                    }
                    notTeamDisciplines.add(discipline);
                }
            }
            else
            {
                for(Discipline discipline: Discipline.values())
                {
                    notTeamDisciplines.add(discipline);
                }
            }
            
            boolean added = Boolean.parseBoolean(request.getParameter("added"));
        %>
        
        <script>var toFilter="Disciplines"</script>
        Nazwa: <input type = "text" name = "searchDisciplines" id="searchDisciplines" onkeyup="myFilterFunction(toFilter)">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
            Ilość: <input type="text" id="amount" value="" style="width:20%;" readonly>
        </center>
                
        <select name="choosedDisciplines" size="6" style="width:100%;" id="choosedDisciplines">
            <% if(added!=true){
                for(Discipline discipline: notTeamDisciplines) {%>
                <option><%= discipline.toString() %></option>
            <%}}
            else{
                for(Discipline discipline: teamDisciplines) {%>
                <option><%= discipline.toString() %></option>
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