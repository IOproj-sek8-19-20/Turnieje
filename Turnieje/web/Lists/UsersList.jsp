<%-- 
    Document   : UsersList
    Created on : 2020-01-08, 13:20:06
    Author     : Daniel Kaleta
--%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="saveDefaultOrder()">

        <%
            Set<String> users = new TreeSet<String>();
            users.add("Daniel Kaleta");
            users.add("Daniel Tarnecki");
            users.add("Piotr Uhl");
            users.add("Wojtek Woś");
            users.add("Adam Adamski");
            users.add("Mariusz Drynda");
        %>
        
        Nazwa: <input type = "text" name = "userToAdd" id="searchUser" onkeyup="myFilterFunction()">
        
        <center>
            <select name="sorting" size="1" style="width:40%;" id="sorting">
                <option selected>A-Z</option>
                <option>Z-A</option>
            </select>
        </center>
                
        <select name="choosedUser" size="7" style="width:100%;" id="choosedUser">
            <% for(String user: users) {%>
                <option><%= user%></option>
            <%} %>
        </select>

        
        <script>
            sort = document.getElementById("sorting");
            sort.addEventListener("change", mySortingFunction);
            var defaultOrderOptions;
            
            function saveDefaultOrder(){
                var select = document.getElementById("choosedUser");
                defaultOrderOptions = select.getElementsByTagName('option');
                defaultOrderOptions = Array.prototype.slice.call(defaultOrderOptions);
                console.log("Domyslna:");
                console.log(defaultOrderOptions);
            }
            
            function mySortingFunction() {
                var select, options, i, typeOfSorting;
                
                typeOfSorting = sort.options[sort.selectedIndex].value;
                
                select = document.getElementById("choosedUser");
                options = select.getElementsByTagName('option');
                options = Array.prototype.slice.call(options);
                
                if(typeOfSorting.localeCompare("A-Z")==0)
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
                
                for (i = 0; i < select.length; i++) {
                    select.add(options[i])
                }
            }
            
            function myFilterFunction() {
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