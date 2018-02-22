<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Filament" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile regionBean e istanzia un oggetto RegionBean -->
<jsp:useBean id="regionBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.RegionBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto regionBean -->
<jsp:setProperty name="regionBean" property="longitudeCenter"/>
<jsp:setProperty name="regionBean" property="latitudeCenter"/>
<jsp:setProperty name="regionBean" property="sideOrRadius"/>
<jsp:setProperty name="regionBean" property="type"/>

<html>
<head>
    <title>Filament area</title>
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
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Search the filaments totally included in a region </I></h2>
<br><br><br><br><hr>
<form action="req8.jsp" method="get" id="req8">
<fieldset>
    <legend> Insert data </legend>
    <label for="longitudeCenter">Center longitude:  </label>
    <input type="text" name="longitudeCenter" id="longitudeCenter">
    <label for="latitudeCenter">Center latitude:  </label>
    <input type="text" name="latitudeCenter" id="latitudeCenter">
    <br><br>
    <label for="sideOrRadius"> Side/Radius:  </label>
    <input type="text" name="sideOrRadius" id="sideOrRadius">
    <br>
    <label for="type"> Check if you want consider the square ----> </label>
    <input type="checkbox" name="type" id="type">
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
    if(regionBean.getSideOrRadius() <= 0){
            %><p class="text-info"> <I><U><h3>
            <span style="color: red; "> Side and Radius cannot be NEGATIVE or ZERO !   </span>
            </h3></U></I></p>
<%
    } else {
        ArrayList<Filament> filaments = FilamentController.findFilamentsInRegion(regionBean);
        if(filaments.size() == 0){
            %><p class="text-info"> <I><U><h3>
            <span style="color: red; "> No results found! </span>
            </h3></U></I></p><%
        } %>
        <table border="2">
            <tr>
                <th>ID </th>
                <th>Name </th>
                <th>Total flux </th>
                <th>Density </th>
                <th>Temperature </th>
                <th>Ellipse </th>
                <th>Contrast </th>
            </tr> <%
            for (Filament filament : filaments) {
            %><tr>
                <td><% out.print(filament.getID()); %></td>
                <td><% out.print(filament.getName()); %></td>
                <td><% out.print(filament.getTotalFlux()); %></td>
                <td><% out.print(filament.getDensity()); %></td>
                <td><% out.print(filament.getTemperature()); %></td>
                <td><% out.print(filament.getEllipse()); %></td>
                <td><% out.print(filament.getConstrast()); %></td>
            </tr>
            <%}%>
            </table> <%
    }
}%>