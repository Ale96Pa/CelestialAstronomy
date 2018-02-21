<%@ page import="uniroma2.it.dicii.celestialAstronomy.View.FilamentBean" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Filament" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %><%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 20/02/2018
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
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
    <img src="logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Search the filaments by number of segments </I></h2>
<br><br><br><br><hr>
<br>
<h3> Page <% out.print(Integer.parseInt(request.getParameter("page"))+1); %> </h3>
<br>

</body>
</html>

<%
    FilamentBean filamentBean = new FilamentBean();
    ArrayList<Filament> filaments = FilamentController.findFilamentBySegments(filamentBean,
            Integer.parseInt(request.getParameter("page"))*20);
    %><form method="get" action="req7DONE.jsp">
        <label for="page">Choose a page to view:</label>
        <select name="page" id="page">
        <br> <%
        for(int i=0; i< filamentBean.getPagese(); i++){
            %><option value="<%= i %>"> Page <% out.print(i+1); %> </option><%
                }
        %></select>
        <button><input type="hidden" name="offset"> Go! </button>
    </form>
    <button name="back" id="back"> <a href="req6.jsp"> Come Back </a></button>
    <br><br>
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
        for(int i=0; i<filaments.size(); i++){
        %><tr>
            <td> <% out.print(filaments.get(i).getID()); %> </td>
            <td> <% out.print(filaments.get(i).getName() ); %> </td>
            <td> <% out.print(filaments.get(i).getTotalFlux()); %> </td>
            <td> <% out.print(filaments.get(i).getDensity()); %> </td>
            <td> <% out.print(filaments.get(i).getTemperature()); %> </td>
            <td> <% out.print(filaments.get(i).getEllipse()); %> </td>
            <td> <% out.print(filaments.get(i).getConstrast()); %> </td>
        </tr>
        <%}%>
    </table>