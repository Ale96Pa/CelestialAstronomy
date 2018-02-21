<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 07/02/2018
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="loginBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.LoginBean"/>
<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean -->
<jsp:setProperty name="loginBean" property="username"/>
<jsp:setProperty name="loginBean" property="password"/>

<%
    if(request.getParameter("login")!= null){
        if(loginBean.validateUser()){
            if(loginBean.isAdministrator()){
                %> <jsp:forward page="WelcomeAdministrator.jsp" /> <%
            } else {
                %> <jsp:forward page="Welcome.jsp"/> <%
            }
        }
        else {
            %>
            <p class="text-info"> <I><U><h3>
                <span style="color: red; "> Invalid access: check your data and try again  </span>
            </h3> </U></I></p>
            <%
        }
    }
%>

<html>
  <head>
    <title>Login</title>
  </head>

  <body background="bbback.jpg" bgcolor="blue">
  <p align="center">
    <img src="logo.JPG" width="200" height="170" border="2">
  </p>

<span style="color: lightgoldenrodyellow;">
  <h2><I>
      <p align="center">
      Welcome on the system INAF-TV (Istituto Nazionale di AstroFisica - Tor Vergata)
      </p>
  </I></h2>

  <p align="center">
      <br>  Sign in !
  </p>

  <form id="loginForm" action="index.jsp" method="post">
      <fieldset>
          <legend> Login : </legend>
          <div class="row" align="center">
          <br>
              <label for="username">User-ID  </label>
              <input id="username" name="username" type="text">
          </div>
          <div class="row" align="center">
          <br>
              <label for="password">Password </label>
              <input id="password" name="password" type="password" align="username">
          </div>
          <div class="row" align="center">
          <br>
              <input name="login" type="submit" id="login" value="Sign-in" class="btn btn-info">
              <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
          </div>
      </fieldset>
  </form>
    </span>
  </body>
</html>
