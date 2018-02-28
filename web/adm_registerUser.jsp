<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.UserController" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 10/02/2018
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile userBean e istanzia un oggetto UserBean -->
<jsp:useBean id="userBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.UserBean" />
<!-- Mappa automaticamente tutti gli attributi dell'oggetto userBean -->
<jsp:setProperty name="userBean" property="*"/>

<%
    if(request.getParameter("register")!= null){
        try{
            if(userBean.validateUser()){
                boolean esitoInserimento = UserController.registerUser(userBean);
                if(!esitoInserimento){
%>                  <I><U><h3>
                    <span style="color: red; "> User is already present in DataBase  </span>
                    </h3></U></I> <%
                    throw new AlreadyPresentException();
                } else
%>                  <jsp:forward page="adm_registrationDONE.jsp" /> <%
            } else{
%>              <p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: username and password must have more than 6 character  </span>
                </h3> </U> </I> </p>
<%          }
        } catch (AlreadyPresentException e){
            e.printStackTrace();
        }
    }
%>



<html>
<head>
    <title>Users area</title>
</head>

<body bgcolor="#f0f8ff">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> User Area </I></h2>
<br><br><br><br><hr>
<h3> Register a new user </h3>
<br>
<form action="adm_registerUser.jsp" method="post" id="newuser">
    <fieldset>
        <legend> Insert new user : </legend>
        <label for="nome"> Name: </label>
        <input type="text" id="nome" name="nome">
        <br><br>
        <label for="cognome"> Surname: </label>
        <input type="text" id="cognome" name="cognome">
        <br><br>
        <label for="username"> Username: </label>
        <input type="text" id="username" name="username">
        <br><br>
        <label for="password"> Password: </label>
        <input type="text" id="password" name="password">
        <br><br>
        <label for="email"> E-mail: </label>
        <input type="text" id="email" name="email">
        <br><br>
        <label for="administrator"> Administrator: </label>
        <input type="checkbox" id="administrator" name="administrator">
        <br><hr><br>

        <input name="register" type="submit" id="register" value="Register user" class="btn btn-info">
        <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
        <button name="back" id="back"> <a href="WelcomeAdministrator.jsp"> Come Back </a></button>
        <br>
    </fieldset>
</form>

</body>
</html>