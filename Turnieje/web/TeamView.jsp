<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Podglad druzyny</title>
    </head>

    <script>var toInit = "Users";</script>
    <body onload="init(toInit);">

    <center>
        
        <h1>Nazwa druzyny: <%= request.getParameter("teamName") %> </h1>
        
        <br/><br/>

        Zawodnicy:

        <br/><br/>

        <iframe id="Users" src="/Turnieje/Lists/TeamPlayersList.jsp"></iframe>
        
        <br/><br/>
        
        <input type = "submit" value = "Pokaz zawodnika" onclick="submitShowUser()">
        
        <br/><br/>
        
        <form action = "MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>

    </center>
    <script>
    function submitShowUser()
            {
                var iframe = document.getElementById("Users");   
                var select = iframe.contentWindow.document.getElementById("choosedUsers");   
                var options = select.getElementsByTagName('option');    
                location = "/Turnieje/UserView.jsp?userName="+options[select.selectedIndex].text;
            }
    </script>
    </body>
</html>