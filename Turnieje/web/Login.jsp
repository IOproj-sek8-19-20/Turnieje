<%-- 
    Document   : login
    Created on : 2020-01-11, 12:53:32
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logowanie</title>
    </head>
    <body>
    <center>
        <form action = "Login" method="get" id="myForm">
            login : <input type = "text" name = "login" id="login"> <br>
            hasło : <input type = "password" name = "password" id="password"> <br>
        </form>
      <input type = "submit" value = "Zatwierdz" onclick="submit()">
    </center>
    <script>
        function submit()
        {    
            var JSONToSend = "{\"login\":\"" + document.getElementById("login").value + "\",";
            JSONToSend = JSONToSend + "\"password\":\"" + document.getElementById("password").value+"\"}";
            console.log(JSONToSend);
            location = "/Turnieje/Login?JSONFromLogin="+ JSONToSend;
     
        }
    </script>
    </body>
</html>

