<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
<title>Turnieje</title>

</head>
<%
    //Jezeli uzytkownik jest zalogowany zostaje przekierowany do MainMenu.jsp
    //W przeciwnym wypadku bedzie mial mozliwosc zalogowania sie lub zarejestrowania
    if(session.getAttribute("loginUser") != null)
    {
        response.sendRedirect("/Turnieje/MainMenu.jsp");
    }
%>

    <body>
        <center>
                        
            <br/>
            <form action="Login.jsp" method="GET">
                <input type="submit" value="Zaloguj sie" />
            </form>
            
            <br/><br/>
            
            <form action="Registration.jsp" method="GET">
                <input type="submit" value="Zajerestruj sie" />
            </form>
            
        </center>
    </body>
</html>