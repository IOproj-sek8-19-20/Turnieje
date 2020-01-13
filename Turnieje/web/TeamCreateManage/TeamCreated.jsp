<%-- 
    Document   : TeamCreated
    Created on : 2020-01-09, 19:47:13
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.TeamId"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.ITeamRepository"%>
<%@page import="pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Druzyna utworzona</title
    </head>
    
    <%
        Team acutalTeam = (Team) session.getAttribute("actualTeam");
        
        String TeamCreatedIdString = request.getParameter("teamId");
        RepositoryProvider repositoryProvider = RepositoryProvider.getInstance();
        ITeamRepository teamRepository = repositoryProvider.getTeamRepository();
        TeamId TeamCreatedId = new TeamId(Integer.parseInt(TeamCreatedIdString));
        try
        {
            Team TeamCreated = teamRepository.getById(TeamCreatedId);
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    %>
    
    <body>
        <center>
            <h1> Druzyna o ID: <%= request.getParameter("teamId") %>, nazwie <%= acutalTeam.getName() %> dodana</h1>
        </center>   

        <script>
        setTimeout(function() {
            location.replace("/Turnieje/TeamCreateManage/ManageTeam.jsp?teamId="+"<%= request.getParameter("teamId") %>"+"");
        }, 2000);
        </script>
    </body>
</html>
