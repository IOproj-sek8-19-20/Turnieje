<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Usuń konto</title>
    </head>
    <body>
        <%
        //Sprawdzanie, czy uzytkownik jest zalogowany
        String userEmail = (String) session.getAttribute("loggedUser");
        if(userEmail == null)
        {
            response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
            return;
        }
    %>
    <center>
            <h1> Na pewno? Twoje konto zostanie usunięte na zawsze<br>
            To na prawdę długo!</h1>
      <input type = "submit" value = "Usuń konto" onclick="submit()">   <br>
      <input type = "submit" value = "Nie usuwaj" onclick="submit1()"> <br>
    </center>
    <script>
        
        function submit()
        {
          location = "/Turnieje/AccountFinalyDeleted";
        }
        function submit1()
        {
            location = "/Turnieje/EditMyData?";
        }
        
    </script>
    </body>
</html>

