
            //Ustawianie uzytkownikow, ktorzy sa juz dodani
            iframe = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
            select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            select.addEventListener("click", deleteFromNewCaptain);

            <% 
                JSONArray users = JSON.getJSONArray("usersToAdd");
                for (int i = 0; i < users.length(); i++) 
                {
                    %>
                        var option = document.createElement("option");
                        option.text = "<%= users.getString(i)%>";
                        select.add(option);
                    <%
                }
            %>

            //Usuniecie dodanych uzytkownikow z listy dostepnych
            iframe = document.getElementById("AvaibleUsers");   //dobieram sie do iframe
            select = iframe.contentWindow.document.getElementById("choosedUsers");   //dobieram sie do listy druzyn
            options = select.getElementsByTagName('option');

            <% 
                for (int i = 0; i < users.length(); i++) 
                {
                    %>
                        for (var i = 0; i < select.length; i++) 
                        {
                            if(options[i].value == "<%= users.getString(i)%>")
                            {
                                select.remove(i);
                            }
                        }
                    <%
                }
            %>