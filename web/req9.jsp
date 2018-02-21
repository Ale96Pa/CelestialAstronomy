<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.StarController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Star" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.TypeOfStars" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile starBean e istanzia un oggetto StarBean -->
<jsp:useBean id="starBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.StarBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto starBean -->
<jsp:setProperty name="starBean" property="filamentID"/>



<html>
<head>
    <title>Stars area</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body bgcolor="#f0f8ff">

<p align="right">
    <img src="logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Search the stars included in a filament </I></h2>
<br><br><br><br><hr>
<form action="req9.jsp" method="get" id="req9">
<fieldset>
    <legend> Insert ID or NAME of the filament</legend>
    <label for="filamentID">ID/Name:  </label>
    <input type="text" name="filamentID" id="filamentID">
    <br><br>
    <input name="query" type="submit" id="query" value="Query" class="btn btn-info">
    <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
    <button name="back" id="back"> <a href="Welcome.jsp"> Come Back </a></button>
    <br>
</fieldset>
</form>

</body>
</html>


<% if(request.getParameter("query")!= null){
    ArrayList<Star> stars = StarController.findStarsInFilament(starBean);
    if(stars.size() == 0){
        %><p class="text-info"> <I><U><h3>
        <span style="color: red; ">  There aren't stars into the filament ! </span>
        </h3></U></I></p>
    <%} else {
        HashMap rateByType = StarController.rateByType(starBean, stars);
        %> <h4> Found <% out.print(stars.size()); %> total results. </h4>
            <fieldset>
                <legend> Percentage of each type</legend> <%
            for(TypeOfStars allType : TypeOfStars.values()){
                String key = allType.toString();
                Double value = (Double) rateByType.get(key);
                out.println(key + ":  " + value + "%");
                %> <br> <%
            } %>
            </fieldset>
        <br>
        <table border="2">
            <tr>
                <th>ID </th>
                <th>Name </th>
                <th>Type </th>
                <th>Flux </th>
                <th>Longitude </th>
                <th>Latitude</th>
            </tr> <%
            for(int i=0; i<stars.size(); i++){
            %><tr>
                <td> <% out.print(stars.get(i).getID()); %> </td>
                <td> <% out.print(stars.get(i).getName() ); %> </td>
                <td> <% out.print(stars.get(i).getType()); %> </td>
                <td> <% out.print(stars.get(i).getFlux()); %> </td>
                <td> <% out.print(stars.get(i).getLongitude()); %> </td>
                <td> <% out.print(stars.get(i).getLatitude()); %> </td>
        </tr>
            <%}%>
        </table>
        <%}
}%>