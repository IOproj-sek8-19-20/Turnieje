<%-- 
    Document   : Registration
    Created on : 2020-01-11, 13:25:27
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8080/Turnieje/CSS/style.css" type="text/css"/>
        <title>Registration</title>
    </head>
    <body>
    <center>
       <form action = "Registration" method="get" id="myForm">
           
            <div id="regDiv">
                <div id="text">
                   <label for="name">Imie:</label><br/>
                   <label for="surname">Nazwisko: </label><br/>
                   <label for="email">Adres email: </label><br/>
                   <label for="password1">Hasło: </label><br/>
                   <label for="password2">Powtórz hasło: </label><br/>
                   <label for="myCheck">Regulamin:</label><br/>
                </div>
                <div id="fields">
                   <input type = "text" name = "name" id="name"> <br>
                   <input type = "text" name = "surname" id="surname"><br>
                   <input type = "text" name = "email" id="email"><br>
                   <input type = "password" name = "password1" id="password1"> <br>
                   <input type = "password" name = "password2" id="password2"> <br>
                   <input type="checkbox" id="myCheck" onclick="myFunction()">
                </div>
            </div>
           
        </form>
        <br/><br/>
        <input type = "submit" value = "Zatwierdz" onclick="submit()">
      
        <br/><br/>
      
        <a href="/Turnieje/Login.jsp">
            <input type = "submit" value = "Logowanie">
        </a>
      
      </center>
      <script>
        function submit()
        {    
            var JSONToSend = "{\"name\":\"" + document.getElementById("name").value + "\",";
            JSONToSend = JSONToSend + "\"surname\":\"" + document.getElementById("surname").value + "\",";
            JSONToSend = JSONToSend + "\"email\":\"" + document.getElementById("email").value + "\",";
            JSONToSend = JSONToSend + "\"password1\":\"" + document.getElementById("password1").value + "\",";
            JSONToSend = JSONToSend + "\"password2\":\"" + document.getElementById("password2").value + "\",";
            JSONToSend = JSONToSend + "\"checkBox\":\"" + document.getElementById("myCheck").value+"\"}";
            console.log(JSONToSend);
            location = "/Turnieje/Registration?JSONFromRegistration="+ JSONToSend;
        }
        </script>
    </body>
</html>
