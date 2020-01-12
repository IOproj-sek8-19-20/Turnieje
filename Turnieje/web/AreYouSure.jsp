<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zmien dane</title>
    </head>
    <body>
    <center>
        <h1>Na pewno chcesz usunąć konto na zawsze? To naprawdę długo</h1>
        <input type = "submit" value = "Usuń konto, ostatecznie" onclick="submit()">   <br>
        <input type = "submit" value = "Anuluj usuwanie konta" onclick="submit1()"> <br>
    </center>
    <script>
        function submit()
        {
            var JSONToSend = "{\"firstName\":\"" + "ss" + "\",";
            JSONToSend = JSONToSend + "\"type\":\"" + "deleteforever" + "\"}";
            console.log(JSONToSend);
            location.replace("/Turnieje/AccountDelete");
        }
        function submit1()
        {
            location.replace("/Turnieje/edit/EditData.jsp");
        }
    </script>
    </body>
</html>

