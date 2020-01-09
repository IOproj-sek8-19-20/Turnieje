<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Turnieje - stworz turniej</title>

</head>

    <body>
        <center>
            <br/>
            
            
            <form action = "CreateTournament" method="get">
            Nazwa turnieju : <input type = "text" name = "tournamentName" id="tournamentName">

            <%
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

                Calendar c = Calendar.getInstance();
                c.setTime(date);

                c.add(Calendar.DATE, 1);

                Date DatePlusOne = c.getTime();

            %>
            
            <br/><br/>
            
            Dyscyplina: 
            <br/><br/>

            <iframe src="/Turnieje/Lists/DisciplinesList.jsp"></iframe>
            
            
            <br/><br/>
        
            Data zakonczenia: <input type="date" id="start" name="trip-start" value="<%=ft.format(date)%>" min="<%=ft.format(date)%>">
            
            <br/><br/>
            <input type = "submit" value = "Zatwierdz">
            </form>
            
            
        </center>
    </body>
    
</html>


