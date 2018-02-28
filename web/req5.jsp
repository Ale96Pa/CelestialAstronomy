<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile filamentBean e istanzia un oggetto FilamentBean -->
<jsp:useBean id="filamentBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.FilamentBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto filamentBean -->
<jsp:setProperty name="filamentBean" property="idOrName"/>


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
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Information about filament </I></h2>
<br><br><br><br><hr>

<form action="req5.jsp" method="get" id="req5">
<fieldset>
    <legend> Insert ID or NAME of the filament</legend>
    <label for="idOrName">ID/Name:  </label>
    <input type="text" name="idOrName" id="idOrName">
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
    try{
        if(!FilamentController.findFilamentInDb(filamentBean)){
            %><p class="text-info"> <I><U><h3>
            <span style="color: red; "> Filament isn't in Database !  </span>
            </h3></U></I></p>
            <% throw new WrongDataException();
        } else {
            %><table border="2">
            <tr>
                <th>Centroide (Long, Lat)</th>
                <th>Estention (Long, Lat)</th>
                <th>Number of segments</th>
            </tr>
            <tr>
                <td> <% out.print("( " + FilamentController.findCentroide(filamentBean).getLongitude() + " , " +
                FilamentController.findCentroide(filamentBean).getLatitude() + " )" ); %> </td>
                <td> <%  out.print("( " + FilamentController.findExtension(filamentBean).getLongitudinalExtension() + " , " +
                FilamentController.findExtension(filamentBean).getLatitudinalExtension() + " )" ); %> </td>
                <td> <% out.print(FilamentController.findNumOfSegments(filamentBean)); %> </td>
            </tr>
            </table>
<%      }
    }catch (WrongDataException e){
        e.printStackTrace();
    }
}%>