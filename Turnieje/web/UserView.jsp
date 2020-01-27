<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/Turnieje/CSS/style.css" type="text/css"/>
        <title>Podglad zawodnika</title>
    </head>
    
    <script>var toInit = "Player"</script>
    <body onload="init(toInit);">
    <center>
        <h1>Nazwa uzytkownika: <%= request.getParameter("userName") %> </h1>
        
        <br><br/>
        
        <%-- informacje o uzytkowniku z tym id--%>
        
        <br><br/>
        
        <form action = "MainMenu.jsp" method="get">
            <input type = "submit" value = "Powrot">
        </form>
    </center>
    </body>
</html>
