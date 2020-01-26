<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
      <input type = "submit" value = "Usuń konto, definitywnie ostatecznie" onclick="submit()">   <br>
      <input type = "submit" value = "Nie chcę usuwać konta" onclick="submit1()"> <br>
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

