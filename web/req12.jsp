<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Star" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.NoDataFoundException" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile filamentBean e istanzia un oggetto FilamentBean -->
<jsp:useBean id="filamentBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.FilamentBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto filamentBean -->
<jsp:setProperty name="filamentBean" property="idOrName"/>
<jsp:setProperty name="filamentBean" property="order"/>



<html>
<head>
    <title>Star area</title>
</head>
<body bgcolor="#f0f8ff">

<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Calculate the position of a star respect to backbone </I></h2>
<br><br><br><br><hr>
<form action="req12.jsp" method="get" id="req12">
<fieldset>
    <legend> Insert ID of the filament</legend>
    <label for="idOrName">ID:  </label>
    <input type="text" name="idOrName" id="idOrName">
    <br><br>
    <label for="order">Order by: </label>
    <select name="order" id="order">
        <option value="1">Distance</option>
        <option value="2">Flux</option>
    </select>
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
    if(request.getParameter("query")!= null){
    try{
        if(!FilamentController.findFilamentInDb(filamentBean)){
            %><p class="text-info"> <I> <U> <h3>
            <span style="color: red; "> Filament isn't in Database !  </span>
            </h3> </U> </I> </p> <%
            throw new NoDataFoundException();
        } else {
                ArrayList<Star> stars = FilamentController.findStarsByDistanceFromBackbone(filamentBean, -1);
                totalResult = stars.size();
                pages = ((totalResult - (totalResult%20))/20);
                if(totalResult%20 != 0)
                    pages+=1;
                filamentBean.setPages(pages);
                if(totalResult==0){
                    %><p class="text-info"> <I><U><h3>
                    <span style="color: red; "> No result found! </span>
                    </h3></U></I></p> <%
                    throw new NoDataFoundException();
                }
            } %>
        <form method="get" action="req12DONE.jsp">
            There are  <% out.print(totalResult); %>  results. Choose a page to view: <select name="page" id="page"> <%
            for(int i=0; i<pages; i++){
        %><option value="<%= i %>"> Page <% out.print(i+1); %> </option> <%
            }
        %></select>
            <button><input type="hidden" name="offset"> Go! </button>
        </form> <%
    %>
<%  }catch (NoDataFoundException e){
        e.printStackTrace();
    }
}%>