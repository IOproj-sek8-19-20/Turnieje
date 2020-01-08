<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Turnieje - edytuj turniej</title>

    </head>

    <body>
        


<center>
    <center><h1>Edytujesz turniej: <%= request.getParameter("tournamentNameGet") %> </h1></center>
    
    <form action = "ManageTeam" method="get">
        
        Nazwa turnieju : <input type = "text" name = "teamName" id="teamName">
        <br/><br/>


        <br/><br/>
        <input type = "submit" value = "Zatwierdz">
    </form>

</center>
</body>



</html>


