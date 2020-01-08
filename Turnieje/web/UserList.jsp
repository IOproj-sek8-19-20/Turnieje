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
        
        Nazwa: <input type = "text" name = "userToAdd" id="searchUser" onkeyup="myFilterFunction()">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option></option>
                <option>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedUser" size="7" style="width:100%;" id="choosedUser" onchange="mySortingFunction()">
        <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>

        
        <script>
            sort = document.getElementById("sorting");
            sort.addEventListener("change", myFunctionSort);
            
            function mySortingFunction() {
                console.log("Wywoluje funkcje sort");
                var input, filter, select, options, i, txtValue, typeOfSorting;
                input = document.getElementById('searchUser');
                filter = input.value.toUpperCase();
                
                typeOfSorting = sort.options[sort.selectedIndex].value;
                
                select = document.getElementById("choosedUser");
                options = select.getElementsByTagName('option');
                options = Array.prototype.slice.call(options);
                
                if(typeOfSorting.localeCompare("")==0)
                {
                
                }
                else if(typeOfSorting.localeCompare("A-Z")==0)
                {
                    options.sort(function(a, b)
                    {
                      return a.value.localeCompare(b.value);
                    });
                }
                else if(typeOfSorting.localeCompare("Z-A")==0)
                {
                    options.sort(function(a, b)
                    {
                      return (-1)*a.value.localeCompare(b.value);
                    });
                }
                
                console.log("Po sortowaniu:");
                console.log(options);
                
                for (i = 0; i < select.length; i++) {
                    console.log(options[i].value)
                    select.add(options[i])
                }
            }
            
            function myFilterFunction() {
                console.log("Wywoluje funkcje");
                var input, filter, select, options, i, txtValue;
                input = document.getElementById('searchUser');
                filter = input.value.toUpperCase();
                select = document.getElementById("choosedUser");
                options = select.getElementsByTagName('option');

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