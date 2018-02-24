<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.InstrumentController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException" %><%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 10/02/2018
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile userBean e istanzia un oggetto UserBean -->
<jsp:useBean id="instrumentBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.InstrumentBean" />
<!-- Mappa automaticamente tutti gli attributi dell'oggetto userBean -->
<jsp:setProperty name="instrumentBean" property="*"/>

<%
    if(request.getParameter("register")!= null){
        try{
            boolean esitoInserimento = InstrumentController.registerInstrument(instrumentBean);
            if(!esitoInserimento){
                %><I> <U> <h3>
                <span style="color: red; "> Instrument is already present in DataBase or you insert NO bands </span>
                </h3></U></I> <%
                throw new AlreadyPresentException();
            } else
                %><jsp:forward page="adm_registrationDONE.jsp" /> <%
        } catch (AlreadyPresentException e){
            e.printStackTrace();
        }
    }
%>


<html>
<head>
    <title>Instrument area</title>
</head>
<body bgcolor="#f0f8ff">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Instrument Area </I></h2>
<br><br><br><br><hr>
<h3> Insert data for a new instrument </h3>
<br>
<form action="adm_insertInstrument.jsp" method="get" id="instrument">
    <fieldset>
        <legend> Insert instrument : </legend>
        <label for="name"> Name: </label>
        <input type="text" name="name" id="name">
        <br><br>
        <label> Realtive bands: </label>
        <input type="text" name="band1"> , </input>
        <input type="text" name="band2"> , </input>
        <input type="text" name="band3"> , </input>
        <input type="text" name="band4"> , </input>
        <input type="text" name="band5"> </input>
        <br><hr><br>
        <input name="register" type="submit" id="register" value="Register instrument" class="btn btn-info">
        <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
        <button name="back" id="back"> <a href="WelcomeAdministrator.jsp"> Come Back </a></button>
        <br>
    </fieldset>
</form>
</body>
</html>