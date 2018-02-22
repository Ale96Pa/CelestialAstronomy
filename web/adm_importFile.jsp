<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FileController" %><%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 10/02/2018
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Si dichiara la variabile csvFileBean e istanzia un oggetto CsvFileBean -->
<jsp:useBean id="csvFileBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean" />
<!-- Mappa automaticamente tutti gli attributi dell'oggetto csvFileBean -->
<jsp:setProperty name="csvFileBean" property="filename"/>
<jsp:setProperty name="csvFileBean" property="numrows"/>
<jsp:setProperty name="csvFileBean" property="offset"/>

<%
    if(request.getParameter("import")!= null){
        if(csvFileBean.getFilename() != null){
            int num = FileController.importFile(csvFileBean);
            if(num < 0){
%>          <I><U><h3>
            <span style="color: red; "> Failure on import file: no element update or insert.
                Maybe you violated foreign key constraint(if you insert filament's segments or perimeter but filament
                isn't in DB)
            </span>
            </h3></U></I> <%
            }
            else if(csvFileBean.getNumrows()<0 || csvFileBean.getOffset()<0){
%>          <I><U><h3>
            <span style="color: red; "> Insert valid number of rows and offset! </span>
            </h3></U></I> <%
            } else {
                System.out.println(csvFileBean.getNumrows() + "  " + csvFileBean.getOffset());
%>          <jsp:forward page="adm_registrationDONE.jsp" /> <%
            }
        }
    }
%>


<html>
<head>
    <title>Files area</title>
</head>

<body bgcolor="#f0f8ff">

<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> File Area </I></h2>
<br><br><br><br><hr>
<h3> Import a new file </h3>
<br>
<form action="adm_importFile.jsp" method="get" id="importation">
    <fieldset>

        <legend> Import new file : </legend>
        <label for="filename"> Choose the file: </label>
        <select name="filename" id="filename">
            <option value="1"> Filament perimeter: HERSCHEL satellite</option>
            <option value="2"> Filament perimeter: SPITZER satellite</option>
            <option value="3"> Filament skeleton: HERSCHEL satellite</option>
            <option value="4"> Filament skeleton: SPITZER satellite</option>
            <option value="5"> Filament: HERSCHEL satellite</option>
            <option value="6"> Filament: SPITZER satellite</option>
            <option value="7"> Stars: HERSCHEL satellite</option>
        </select>
        <br><br>
        <label for="numrows">How many rows do you want import? <I>Insert ZERO (0) to add the whole file </I></label>
        <input id="numrows" name="numrows" type="number">
        <br><br>
        <label for="offset">From which line you want to start importing? </label>
        <input id="offset" name="offset" type="number">
        <br><br>
        <input name="import" type="submit" id="import" value="Import file" class="btn btn-info">
        <button name="back" id="back"> <a href="WelcomeAdministrator.jsp"> Come Back </a></button>
        <br>

    </fieldset>
</form>

</body>
</html>
