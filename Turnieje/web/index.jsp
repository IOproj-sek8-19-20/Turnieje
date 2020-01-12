<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Turnieje</title>

</head>
<%
    String user = null;
    if(session.getAttribute("loginUser") == null)
    {
        response.sendRedirect("http://localhost:8080/Turnieje/Login.jsp");
        return;
    }
    else 
    {
        response.sendRedirect("http://localhost:8080/Turnieje/MainMenu.jsp");
    } 

%>

    <body>
        <center>
                        
            <br/>
            <form action="Login.jsp" method="GET">
                <input type="submit" value="Zaloguj się" />
            </form>
            
            <br/><br/>
            
            <form action="Registration.jsp" method="GET">
                <input type="submit" value="Zajerestruj się" />
            </form>
            
        </center>
    </body>
</html>