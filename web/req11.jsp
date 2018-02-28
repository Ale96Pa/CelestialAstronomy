<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.SegmentController" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile segmentBean e istanzia un oggetto SegmentBean -->
<jsp:useBean id="segmentBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.SegmentBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto segmentBean -->
<jsp:setProperty name="segmentBean" property="ID"/>


<html>
<head>
    <title>Segment area</title>
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
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Calculate the distance of segment's vertex from perimeter </I></h2>
<br><br><br><br><hr>
<form action="req11.jsp" method="get" id="req11">
<fieldset>
    <legend> Insert ID of the segment </legend>
    <label for="ID">ID:  </label>
    <input type="number" name="ID" id="ID">
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
    if(!SegmentController.findSegmentById(segmentBean)){
        %><p class="text-info"> <I><U><h3>
        <span style="color: red; ">  Not segment found with the inserted ID!  </span>
        </h3></U></I></p> <%
    }else {
        ArrayList<Double> distances = SegmentController.findDistanceOfSegment(segmentBean);
        %> <table border="2">
            <tr>
                <th>ID </th>
                <th>Distance of minimum vertex </th>
                <th>Distance of maximum vertex </th>
            </tr>
            <tr>
                <td> <% out.print(segmentBean.getID()); %> </td>
                <td> <% out.print(distances.get(0) ); %> </td>
                <td> <% out.print(distances.get(1)); %> </td>
            </tr>
        </table> <%
    }
}%>