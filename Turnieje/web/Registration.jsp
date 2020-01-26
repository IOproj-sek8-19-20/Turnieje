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
        <title>Registration</title>
    </head>
    <body>
    <center>
       <form action = "Registration" method="get" id="myForm">
           Imie : <input type = "text" name = "name" id="name"> <br>
     Nazwisko : <input type = "text" name = "surname" id="surname"><br>
     Adres email: <input type = "text" name = "email" id="email"><br>
     Hasło:<input type = "password" name = "password1" id="password1"> <br>
     Powtórz hasło:<input type = "password" name = "password2" id="password2"> <br>
     Regulamin:    <input type="checkbox" id="myCheck" onclick="myFunction()">
       </form>
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
