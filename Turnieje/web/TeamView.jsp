<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad druzyny</title>
    </head>
<%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
    <script>var toCount="Users"</script>
    <body onload="myCountingFunction(toCount)">

    <center>
        
        <h1>Nazwa druzyny: <%= request.getParameter("teamName") %> </h1>
        
        <br/><br/>

        Zawodnicy:

        <br/><br/>

        <iframe id="Users" src="/Turnieje/Lists/UsersList.jsp?inTeam=true"></iframe>
        
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
                location = "/Turnieje/UserView?userName="+options[select.selectedIndex].text;
            }
    </script>
    </body>
</html>