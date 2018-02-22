<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Filament" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.NoDataFoundException" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile filamentBean e istanzia un oggetto FilamentBean -->
<jsp:useBean id="filamentBean" scope="request" class="uniroma2.it.dicii.celestialAstronomy.View.FilamentBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto filamentBean -->
<jsp:setProperty name="filamentBean" property="brillance"/>
<jsp:setProperty name="filamentBean" property="minEllipse"/>
<jsp:setProperty name="filamentBean" property="maxEllipse"/>

<html>
<head>
    <title>Filament area</title>
</head>
<body bgcolor="#f0f8ff">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Search the filaments by ellipse and brillance </I></h2>
<br><br><br><br><hr>
<form action="req6.jsp" method="get" id="req6">
<fieldset>
    <legend> Insert data</legend>
    <label for="brillance">Percentage brillance:  </label>
    <input type="text" name="brillance" id="brillance">
    <br><br>
    <label for="minEllipse">Minimum ellipse:  </label>
    <input type="text" name="minEllipse" id="minEllipse">
    <label for="maxEllipse">Maximum ellipse:  </label>
    <input type="text" name="maxEllipse" id="maxEllipse">
    <br><br>
    <input name="query" type="submit" id="query" value="Query" class="btn btn-info">
    <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
    <button name="back" id="back"> <a href="Welcome.jsp"> Come Back </a></button>
    <br>
</fieldset>
</form>
</body>
</html>


<%
    int pages;
    int totalResult;
    double rateFilaments;
    if(request.getParameter("query")!= null){
        try{
            if(filamentBean.getBrillance() <= 0){
                %><p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: Brillance's percentage have to be POSITIVE!  </span>
                </h3></U></I></p> <%
                throw new WrongDataException();
            }
            else if(filamentBean.getMinEllipse() <= 1 || filamentBean.getMaxEllipse() >= 10 ){
                %><p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: Ellipse's measure have to be included in range [2,9] !  </span>
                </h3></U></I></p> <%
                throw new WrongDataException();
            }
            else if(filamentBean.getMinEllipse() > filamentBean.getMaxEllipse()){
                %><p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: minimum Ellipse have to be LOWER than maximum !  </span>
                </h3> </U> </I> </p> <%
                throw new WrongDataException();
            }
            else{
                ArrayList<Filament> filaments = FilamentController.findFilamentByEllipseAndContrast(filamentBean, -1);
                totalResult = filaments.size();
                pages = ((totalResult - (totalResult%20))/20);
                if(totalResult%20 != 0)
                    pages+=1;
                filamentBean.setPagese(pages);
                    if(totalResult==0){
                        %><p class="text-info"> <I><U><h3>
                        <span style="color: red; "> No result found! </span>
                        </h3></U></I></p> <%
                        throw new NoDataFoundException();
                    }
                rateFilaments = FilamentController.rateFilamentRequired(filaments);
            }%>

            <form method="get" action="req6DONE.jsp">
                There are  <% out.print(totalResult); %>  results.
                <label for="page"> Choose a page to view: </label>
                <select name="page" id="page"> <%
                for(int i=0; i<pages; i++){
                    %><option value="<%= i %>"> Page <% out.print(i+1); %> </option> <%
                }
                %></select>
                <button><input type="hidden" name="offset"> Go! </button>
            </form>
               <h4> You found  <% out.print(rateFilaments); %> % of all filaments! </h4><%
        } catch (WrongDataException | NoDataFoundException e) {
            e.printStackTrace();
        }
    }
%>