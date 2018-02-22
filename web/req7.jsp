<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Filament" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.FilamentController" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.NoDataFoundException" %>
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
<jsp:setProperty name="filamentBean" property="minNumOfSegment"/>
<jsp:setProperty name="filamentBean" property="maxNumOfSegment"/>




<html>
<head>
    <title>Filament area</title>
</head>
<body bgcolor="#f0f8ff">
<p align="right">
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Search the filaments by number of segments </I></h2>
<br><br><br><br><hr>
<form action="req7.jsp" method="get" id="req7">
<fieldset>
    <legend> Insert data </legend>
    <label for="minNumOfSegment">Minimum number of segments:  </label>
    <input type="number" name="minNumOfSegment" id="minNumOfSegment">
    <label for="maxNumOfSegment">Maximum number of segments:  </label>
    <input type="number" name="maxNumOfSegment" id="maxNumOfSegment">
    <br><br>
    <input name="query" type="submit" id="query" value="Query" class="btn btn-info">
    <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
    <button name="back" id="back"> <a href="Welcome.jsp"> Come Back </a></button>
    <br>
</fieldset>
</form>
</body>
</html>

<%
    int pages;
    int totalResult;
    if(request.getParameter("query")!= null){
        try{
            if(filamentBean.getMinNumOfSegment() <= 2){
%>              <p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: minimum number of segment has to be at least 3!  </span>
                </h3> </U> </I> </p> <%
                throw new WrongDataException();
            }
            else if(filamentBean.getMinNumOfSegment() > filamentBean.getMaxNumOfSegment()){
%>              <p class="text-info"> <I> <U> <h3>
                <span style="color: red; "> Check your data: minimum limit has to be LOWER than maximum !  </span>
                </h3> </U> </I> </p> <%
                    throw new WrongDataException();
                }
                else{
                    ArrayList<Filament> filaments =FilamentController.findFilamentBySegments(filamentBean, -1);
                    totalResult = filaments.size();
                    pages = ((totalResult - (totalResult%20))/20);
                    if(totalResult%20 != 0)
                        pages+=1;
                    filamentBean.setPagese(pages);
                        if(totalResult==0){
                            %><p class="text-info"> <I><U><h3>
                            <span style="color: red; "> No result found! </span>
                            </h3></U></I></p> <%
                            throw new NoDataFoundException();
                        }
                } %>
                <form method="get" action="req7DONE.jsp">
                    There are  <% out.print(totalResult); %>  results. Choose a page to view: <select name="page" id="page"> <%
                    for(int i=0; i<pages; i++){
                %><option value="<%= i %>"> Page <% out.print(i+1); %> </option> <%
                    }
                %></select>
                    <button><input type="hidden" name="offset"> Go! </button>
                </form> <%
        } catch (WrongDataException | NoDataFoundException e){
            e.printStackTrace();
        }
    }
%>
