<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 09/02/2018
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body background="bbback.jpg">

<span style="color: #fff5bf">
<p align="right">
    <img src="backg.jpeg" width="150" height="1600" align="right">
    <img src="logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Welcome to INAF Database </I></h2>
<br><br><br><br><hr>
<br>
</span>

<span style="color: lightgreen">
<fieldset>
    <legend> Informations about a filament </legend>
    REQ-FN-5
    <h4> You can insert a filament with its ID or NAME and you get: Centroide, Extension and Number of segment </h4>
    <h3> <button> <a href="req5.jsp"> <span style="color: #00008b; "> Examine  </span> </a> </button> </h3>
</fieldset>
</span>
<br>

<span style="color: #fff5bf">
<fieldset>
    <legend> Search the filaments by ellipse and brillance </legend>
    REQ-FN-6
    <h4> You can insert the limit of ellipse and the percentage of brillance and you get the filaments with required features </h4>
    <h3> <button> <a href="req6.jsp">  <span style="color: #00008b; "> Examine </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: sandybrown">
<fieldset>
    <legend> Search the filaments by number of segments </legend>
    REQ-FN-7
    <h4> You can insert a limited range and you get the filaments that have a number of segments included in it </h4>
    <h3> <button> <a href="req7.jsp"> <span style="color: #00008b; "> Examine </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: lightgreen">
<fieldset>
    <legend> Search the filaments totally included in a region </legend>
    REQ-FN-8
    <h4> You can insert a region (Square or Circle) and you get the filaments included in the region</h4>
    <h3> <button> <a href="req8.jsp"> <span style="color: #00008b; "> Examine  </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: sandybrown">
<fieldset>
    <legend> Search the stars included in a filament </legend>
    REQ-FN-9
    <h4> You can insert the filament ID and you get the stars included in it</h4>
    <h3> <button> <a href="req9.jsp"> <span style="color: #00008b; "> Examine  </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: #fff5bf">
<fieldset>
    <legend> Calculate the rate of stars in a region </legend>
    REQ-FN-10
    <h4> You can insert the measures of a rectangle and you get all statistics about stars into and out a filament</h4>
    <h3> <button> <a href="req10.jsp"> <span style="color: #00008b; "> Examine  </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: sandybrown">
<fieldset>
    <legend> Calculate the distance of segment's vertex from perimeter </legend>
    REQ-FN-11
    <h4> You can insert the segment ID and you get the distances of its vertexes from its filaments' perimeter</h4>
    <h3> <button> <a href="req11.jsp"> <span style="color: #00008b; "> Examine  </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<span style="color: lightgreen">
<fieldset>
    <legend> Calculate the position of a star respect to backbone </legend>
    REQ-FN-12
    <h4> You can insert the filament ID and you get the stars included in it with the distance from backbone and flux</h4>
    <h3> <button> <a href="req12.jsp"> <span style="color: #00008b; "> Examine  </span>  </a> </button> </h3>
</fieldset>
</span>

<br>
<h3> <button> <a href="index.jsp"> <span style="color: #00008b; "> Log-Out </span>  </a> </button></h3>
<br>

</body>
</html>