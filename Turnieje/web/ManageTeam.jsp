<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html> <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Turnieje - stworz druzyne</title>

    </head>

    <body>


        <script>
            var match = document.cookie.match(new RegExp('(^| )' + "managedTeamName" + '=([^;]+)'));
            var match2 = document.cookie.match(new RegExp('(^| )' + "managedTeamID" + '=([^;]+)'));
            if (match)
            {
                //alert("Znaleziono");
                console.log("Znaleziono");
                console.log(match[2]);
                console.log(match2[2]);
                document.body.innerHTML = "<center><h1>Edytujesz druzyne: " + match[2] + " o ID: " + match2[2] + "</h1></center>";
            } else
            {
                //alert("Nie znaleziono");
                console.log('--something went wrong---');
            }
        </script>
        
<%
//Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:derby://localhost:1527/danikaldb", "daniel", "daniel");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("SELECT * FROM History");

%>


<center>
    <form action = "ManageTeam" method="get">
        Nazwa druzyny : <input type = "text" name = "teamName" id="teamName">
        <br/><br/>
        Dodaj zawodnika (nazwa) : <input type = "text" name = "userToAdd" id="userToAdd">
        <br/><br/>
        Dodaj zawodnika (id) : <input type = "number" name = "userToAddID" id="userToAddID">
        <br/><br/>
        Przenieś uprawnienia: 

        <h1> Drop down box or select element</h1>
        <select>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>


        <br/><br/>
        <input type = "submit" value = "Zatwierdz">
    </form>

</center>
</body>



</html>


