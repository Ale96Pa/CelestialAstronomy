<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 09/02/2018
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome Admin</title>
</head>

<body background="Image/bbback.jpg" bgcolor="blue">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" align="right">
    <h2><I><span style="color: #ffe665; "> You are logged in as ADMINISTRATOR! </span></I></h2>
<br><br><br>
<span style="color: #fff5bf"> <h4> Choose an action: </h4>
    <ol id="menu">
        <li><h3><a href="adm_importFile.jsp"> <span style="color: rgba(225,231,87,0.76)"> Import new scientific file </span></a></h3></li>
        <br>
        <li><h3><a href="adm_registerUser.jsp">  <span style="color: rgba(225,231,87,0.76);"> Register new user </span></a></h3></li>
        <br>
        <li><h3><a href="adm_insertSatellite.jsp"> <span style="color: rgba(225,231,87,0.76);"> Insert new satellite data </span></a></h3></li>
        <br>
        <li><h3><a href="adm_insertInstrument.jsp"> <span style="color: rgba(225,231,87,0.76);"> Insert new instrument </span></a></h3></li>
        <br>
        <li><h3><a href="Welcome.jsp"> <span style="color: rgba(225,231,87,0.76);"> Access to DataBase </span></a></h3></li>
    </ol>
</span>

</body>
</html>
