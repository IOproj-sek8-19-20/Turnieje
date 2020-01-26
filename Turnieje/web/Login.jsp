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
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Logowanie</title>
    </head>
    <body>
    <center>
        <form action = "Login" method="get" id="myForm">
            
            <div id="regDiv">
                <div id="text">
                   <label for="name">Login: </label><br/>
                   <label for="surname">Hasło: </label><br/>
                </div>
                <div id="fields">
                    <input type = "text" name = "login" id="login"> <br>
                    <input type = "password" name = "password" id="password"> <br>
                </div>
            </div>
            
            
        </form>
        
        <br>
      <input type = "submit" value = "Zatwierdz" onclick="submit()">
      
        <br/><br/>
      
        <a href="/Turnieje/Registration.jsp">
            <input type = "submit" value = "Rejestracja">
        </a>
      
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

