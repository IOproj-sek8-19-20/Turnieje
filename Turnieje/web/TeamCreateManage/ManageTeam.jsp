<%-- 
    Document   : ManageTeam
    Author     : Daniel Kaleta
--%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Discipline"%>
<%@page import="java.util.Set"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.Team"%>
<%@page import="pl.polsl.aei.io.turnieje.model.datamodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj drużynę</title>
    </head>
    <!-- 2 inity, 1 dla list uzytkownikow, 2 dla list dyscyplin-->
    <script>var toInit = "Users", toInit2 = "Disciplines";</script>
    <body onload="init(toInit);init(toInit2);setFieldsAndLists();">
        
    <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        if(session.getAttribute("loginUser") == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
        
        Team acutalTeam = (Team) session.getAttribute("actualTeam");
    
        //aktualnie nie do konca, zeby byl jakis podglad po edycji kapitana
        User captain = (User) session.getAttribute("actualTeamCaptain");
    %>

    <center>
        
    <h1>Edytujesz drużynę: <%= acutalTeam.getName()%> o ID <%= acutalTeam.getId().id %>  </h1>
    
    
    <!--<form action = "ManageTeam" method="get">-->
        
        <!-- Nazwa druzyny -->
        Nazwa drużyny : <input type = "text" name = "teamName" id="teamName" value="<%= acutalTeam.getName() %>">
        
        <br/><br/>
        
        <!-- Kapitan -->
        Kapitan (email): <input type = "text" name = "captain" id="captain" value="<%= captain.getEmail() %>">
        
        <br/><br/>
       
        <!-- Dodawanie zawodników (poza lista raczej zbedne
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">

        <br/><br/>-->

        Edytuj zawodników (wybierz z listy) :

        <br/>

        Dostępni: 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Dodani:

        <br/><br/>

        <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
        <iframe id="ChoosedUsers" src="/Turnieje/Lists/UsersList.jsp?inTeam=true"></iframe>

        <br/><br/>

        Edytuj dyscypliny (wybierz z listy) :

        <br/>

        Dostępne: 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Dodane:

        <br/><br/>

        <iframe id="AvaibleDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
        <iframe id="ChoosedDisciplines" src="/Turnieje/Lists/DisciplinesList.jsp?added=true"></iframe>
        
        <br/><br/>
    <!--</form>-->
    
    <input type = "submit" value = "Zatwierdź" onclick="temp()">
    
    <br/><br/>
    
    <!--
    Powrót do menu g?ównego
    -->
    <form action = "http://localhost:8080/Turnieje/MainMenu.jsp" method="get">
        <input type = "submit" value = "Powrót">
    </form>

    </center>
    
    <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createTeamSubmit.js"></script>
    <script>
        function temp()
        {
            var myVar="Manage";
            submit(myVar);
        }
        function setFieldsAndLists()
        {
            //Ustawianie dyscyplin juz dodanych
            iframe = document.getElementById("ChoosedDisciplines");   //dobieram sie do iframe
            select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn

            <% 
                Set<Discipline> teamDisciplines = acutalTeam.getDisciplines();
                for (Discipline discipline: teamDisciplines)
                {
                    %>
                        var option = document.createElement("option");
                        option.text = "<%= discipline.name() %>";
                        select.add(option);
                    <%
                }
            %>

            //Usuniecie dodanych dyscyplin z listy dostepnych
            iframe = document.getElementById("AvaibleDisciplines");   //dobieram sie do iframe
            select = iframe.contentWindow.document.getElementById("choosedDisciplines");   //dobieram sie do listy druzyn
            options = select.getElementsByTagName('option');

            <% 
                for (Discipline discipline: teamDisciplines) 
                {
                    %>
                        for (var i = 0; i < select.length; i++) 
                        {
                            if(options[i].value == "<%= discipline.name() %>")
                            {
                                select.remove(i);
                            }
                        }
                    <%
                }
            %>
        }
        function deleteFromNewCaptain()
        {
            
        }
            
    </script>
    </body>
</html>


