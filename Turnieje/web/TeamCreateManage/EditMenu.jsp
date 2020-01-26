<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zmien dane</title>
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
        <form action = "Login" method="get" id="myForm">
            <h1> Aby zmienić dane, wpisz nowe dane w odpowiednie pole, <br>
                wpisz poprawne hasło w pole Bieżące haslo, i kliknij odpowiedni przycisk
            </h1>
            Nowe imię: <input type = "text" name = "login" id="firstName"> <br> 
            Nowe nazwisko: <input type = "text" name = "password" id="lastName"> <br> 
            Bieżące hasło: <input type = "password" name = "password" id="password"> <br>
            Nowe hasło: <input type = "password" name = "password" id="newPassword"> <br>
        </form>
        <input type = "submit" value = "Zmień imię" onclick="submit()">   <br>
      <input type = "submit" value = "Zmień nazwisko" onclick="submit1()"> <br>
      <input type = "submit" value = "Zmień hasło" onclick="submit3()"> <br>
      <input type = "submit" value = "Usuń konto" onclick="submit4()"> <br>
      <a href="/Turnieje/MainMenu.jsp">
        <input type = "submit" value = "Powrót" >
        </a>
      
    </center>
    <script>
        function submit()
        {
            var JSONToSend = "{\"firstName\":\"" + document.getElementById("firstName").value + "\",";
            JSONToSend = JSONToSend + "\"password\":\"" + document.getElementById("password").value + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChange?JSON="+ JSONToSend;
        }
        function submit1()
        {
            var JSONToSend = "{\"lastName\":\"" + document.getElementById("lastName").value + "\",";
            JSONToSend = JSONToSend + "\"password\":\"" + document.getElementById("password").value + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/LastNameChange?JSON="+ JSONToSend;
        }

        function submit3()
        {
            var JSONToSend = "{\"password\":\"" + document.getElementById("password").value + "\",";
            JSONToSend = JSONToSend + "\"newPassword\":\"" + document.getElementById("newPassword").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "password" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/PasswordChange?JSON="+ JSONToSend;
        }
        function submit4()
        {
            var JSONToSend = "{\"password\":\"" + document.getElementById("password").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "delete" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/DeleteMyAccount?JSON="+ JSONToSend;
        }
        
    </script>
    </body>
</html>

