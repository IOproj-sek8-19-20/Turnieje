<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edytuj druzyne</title>

    </head>

    <body>
        
<%
//Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:derby://localhost:1527/danikaldb", "daniel", "daniel");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("SELECT * FROM History");

%>


<center>
    <center><h1>Edytujesz druzyne: <%= request.getParameter("teamName") %> </h1></center>
    
    <form action = "ManageTeam" method="get">
        
        Nazwa druzyny : <input type = "text" name = "teamName" id="teamName">
        <br/><br/>
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">
        <br/><br/>
        Przenies uprawnienia: 
        
        <br/><br/>
        
        <iframe src="/Turnieje/Lists/UsersList.jsp"></iframe>


        <br/><br/>
        <input type = "submit" value = "Zatwierdz">
        
    </form>
    
    <br/>
    
    <form action = "index.html" method="get">
        <input type = "submit" value = "Powrot">
    </form>

</center>
</body>



</html>


