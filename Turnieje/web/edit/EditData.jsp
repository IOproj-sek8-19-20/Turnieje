<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zmien dane</title>
    </head>
    <body>
    <center>
        <form action = "Login" method="get" id="myForm">
            Nowe imie: <input type = "text" name = "login" id="firstName"> <br> 
            Nowe nazwisko: <input type = "text" name = "password" id="lastName"> <br> 
            Nowy mail: <input type = "text" name = "password" id="email"> <br>
            Biezace haslo: <input type = "text" name = "password" id="password"> <br>
            Nowe haslo: <input type = "text" name = "password" id="newPassword"> <br>
        </form>
        <input type = "submit" value = "Zmien imię" onclick="submit()">   <br>
      <input type = "submit" value = "Zmien nazwisko" onclick="submit1()"> <br>
      <input type = "submit" value = "Zmien email" onclick="submit2()"> <br>
      <input type = "submit" value = "Zmien haslo" onclick="submit3()"> <br>
      <input type = "submit" value = "Usun konto" onclick="submit4()"> <br>
    </center>
    <script>
        function submit()
        {
            var JSONToSend = "{\"firstName\":\"" + document.getElementById("firstName").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "firstName" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChanged?JSON="+ JSONToSend;
        }
        function submit1()
        {
            var JSONToSend = "{\"lastName\":\"" + document.getElementById("lastName").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "lastName" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChanged?JSON="+ JSONToSend;
        }
        function submit2()
        {
            var JSONToSend = "{\"email\":\"" + document.getElementById("email").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "email" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChanged?JSON="+ JSONToSend;
        }
        function submit3()
        {
            var JSONToSend = "{\"password\":\"" + document.getElementById("password").value + "\",";
            JSONToSend = JSONToSend + "\"newPassword\":\"" + document.getElementById("newPassword").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "password" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChanged?JSON="+ JSONToSend;
        }
        function submit4()
        {
            var JSONToSend = "{\"password\":\"" + document.getElementById("password").value + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "delete" + "\"}";
            console.log(JSONToSend);
            location = "/Turnieje/FirstNameChanged?JSON="+ JSONToSend;
        }
        
    </script>
    </body>
</html>

