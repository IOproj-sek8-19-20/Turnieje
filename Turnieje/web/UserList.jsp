<%-- 
    Document   : UserList
    Created on : 2020-01-08, 13:20:06
    Author     : Daniel Kaleta
--%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        
        Nazwa: <input type = "text" name = "userToAdd" id="searchUser" onkeyup="myFunction()">
                
        <select name="choosedUser" size="7" style="width:100%;" id="choosedUser">
        <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>
        
        <script>
            function myFunction() {
                console.log("Wywoluje funkcje");
              // Declare variables
              var input, filter, select, options, i, txtValue;
              input = document.getElementById('searchUser');
              filter = input.value.toUpperCase();
              select = document.getElementById("choosedUser");
              options = select.getElementsByTagName('option');

              // Loop through all list items, and hide those who don't match the search query
              for (i = 0; i < select.length; i++) {
                txtValue = options[i].value;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                  select[i].style.display = "";
                } else {
                  select[i].style.display = "none";
                }
              }
            }
        </script>
    </body>
</html>
