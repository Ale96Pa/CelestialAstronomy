<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.SatelliteController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 10/02/2018
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile satelliteBean e istanzia un oggetto SatelliteBean -->
<jsp:useBean id="satelliteBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.SatelliteBean" />
<!-- Mappa automaticamente tutti gli attributi dell'oggetto satelliteBean -->
<jsp:setProperty name="satelliteBean" property="*"/>

<%
    if(request.getParameter("register")!= null){
        try{
            boolean esitoInserimento = SatelliteController.registerSatellite(satelliteBean);
            if ( satelliteBean.getInitialyear() == 0 ){
                %><I><U><h3>
                <span style="color: red; "> Insert a correct initial year!  </span>
                </h3></U></I><%
                throw new WrongDataException();
            }
            else if(!esitoInserimento){
                %><I><U><h3>
                <span style="color: red; "> Instrument is already present in DataBase or you insert NO agencies  </span>
                </h3></U></I> <%
                throw new AlreadyPresentException();
            }
            else {
                %><jsp:forward page="adm_registrationDONE.jsp" /> <%
            }
        } catch (AlreadyPresentException | WrongDataException e){
            e.printStackTrace();
        }
    }
%>


<html>
<head>
    <title>Satellite area</title>
</head>
<body bgcolor="#f0f8ff">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Satellite Area </I></h2>
<br><br><br><br><hr>
<h3> Insert data of a new satellite </h3>
<br>
<form action="adm_insertSatellite.jsp" method="get" id="satellite">
    <fieldset>
        <legend> Insert satellite : </legend>
        <label for="name"> Name: </label>
        <input type="text" name="name" id="name">
        <br><br>
        <label> Beginning service date: </label>
        <label for="day">Giorno: </label>
        <select name="initialday" id="day">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
            <option value="21">21</option>
            <option value="22">22</option>
            <option value="23">23</option>
            <option value="24">24</option>
            <option value="25">25</option>
            <option value="26">26</option>
            <option value="27">27</option>
            <option value="28">28</option>
            <option value="29">29</option>
            <option value="30">30</option>
            <option value="31">31</option>
        </select>
        <label for="month"> Mese:</label>
        <select name="initialmonth" id="month">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select>
        <label for="year">Anno: </label>
        <input type="text" name="initialyear" id="year">
        <br><br>
        <label> Ending service date: </label>
        <label for="endday">Giorno:</label>
        <select name="endday" id="endday">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
            <option value="21">21</option>
            <option value="22">22</option>
            <option value="23">23</option>
            <option value="24">24</option>
            <option value="25">25</option>
            <option value="26">26</option>
            <option value="27">27</option>
            <option value="28">28</option>
            <option value="29">29</option>
            <option value="30">30</option>
            <option value="31">31</option>
        </select>
        <label for="endmonth"> Mese: </label>
        <select name="endmonth" id="endmonth">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select>
        <label for="endyear"> Anno: </label>
        <input type="text" name="endyear" id="endyear">
        <br><br>
        <label> Space agencies: </label>
        <input type="text" name="agencies1"> , </input>
        <input type="text" name="agencies2"> , </input>
        <input type="text" name="agencies3"> , </input>
        <input type="text" name="agencies4"> , </input>
        <input type="text" name="agencies5"> </input>
        <br><hr><br>
        <input name="register" type="submit" id="register" value="Register satellite" class="btn btn-info">
        <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
        <button name="back" id="back"> <a href="WelcomeAdministrator.jsp"> Come Back </a></button>
        <br>
    </fieldset>
</form>
</body>
</html>
