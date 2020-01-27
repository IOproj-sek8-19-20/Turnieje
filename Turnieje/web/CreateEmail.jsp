<%-- 
    Document   : CreateEmail
    Created on : 2020-01-27, 00:23:46
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
            <title>Stwórz email</title>
        </head>
        <script>var toInit = "Users";</script>
    <body onload="addCaptain();init(toInit);myCountingFunction(toInit);myCountingFunction(toInit2)">
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
        <br/><br/>
         Wysyłający: <input type = "text" name = "captain" id="captain" value="<%= userEmail %>" readonly>
        <br/><br/>
       Wybierz zawodników z listy:
        <br/><br/>

            <div id="container">
                <div id="avaible">
                    Dostępni: 
                    <br/>
                    <iframe id="AvaibleUsers" src="/Turnieje/Lists/UsersList.jsp"></iframe>
                </div>
                <div id="added">
                    Dodani:
                    <br/>
                    <iframe id="ChoosedUsers" src="/Turnieje/Lists/UsersList.jsp?inTeam=true"></iframe>
                </div>
            </div>
         <br/><br/>
                    Treść:
                    <br/><br/>
                    <textarea id="textArea" rows="25" cols="80"></textarea>
                     <br/><br/>
                    
            <input type = "submit" value = "Zatwierdź" onclick="temp()">
        
    </center>
    <script> 
        function addCaptain()
        {
            var captainEmail = document.getElementById("captain").value;
            
            var iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
            var select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            var options = select.getElementsByTagName('option');    //pobieram opcje z listy
            //usuwam wysyłajaćego z dostepnych zawodnikow
            for (var i = 0, optionsLength = options.length; i < optionsLength; i++) 
            {
                if (options[i].value == captainEmail) 
                {
                    select.remove(i);
                    break;
                }
            }
            myCountingFunction("Users");
        }
        function temp()
        {
            var myVar="Create";
            submit(myVar);
        }
          </script>
     <script src="/Turnieje/JavaScripts/forLists/initFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/addFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/deleteFunction.js"></script>
    <script src="/Turnieje/JavaScripts/forLists/optionsCounter.js"></script>
    <script src="/Turnieje/JavaScripts/submits/createEmailSubmit.js"></script>
        </body>
    </html>
