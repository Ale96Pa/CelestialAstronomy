<%@ page import="uniroma2.it.dicii.celestialAstronomy.Model.Star" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Control.StarController" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.TypeOfStars" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.NoDataFoundException" %>
<%@ page import="uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException" %>
<%--
  Created by IntelliJ IDEA.
  User: Alessandro
  Date: 16/02/2018
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Si dichiara la variabile regionBean e istanzia un oggetto RegionBean -->
<jsp:useBean id="regionBean" scope="request"  class="uniroma2.it.dicii.celestialAstronomy.View.RegionBean" />
<!-- Mappa automaticamente gli attributi dell'oggetto regionBean -->
<jsp:setProperty name="regionBean" property="longitudeCenter"/>
<jsp:setProperty name="regionBean" property="latitudeCenter"/>
<jsp:setProperty name="regionBean" property="base"/>
<jsp:setProperty name="regionBean" property="high"/>


<html>
<head>
    <title>Stars area</title>
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
    <img src="Image/logo.JPG" width="150" height="120" border="2" align="right"><h2><I> Calculate the rate of stars in a region </I></h2>
<br><br><br><br><hr>
<form action="req10.jsp" method="get" id="req10">
<fieldset>
    <legend> Insert data for a rectangle </legend>
    <label for="longitudeCenter">Center longitude:  </label>
    <input type="text" name="longitudeCenter" id="longitudeCenter">
    <label for="latitudeCenter">Center latitude:  </label>
    <input type="text" name="latitudeCenter" id="latitudeCenter">
    <br><br>
    <label for="base"> Base:  </label>
    <input type="text" name="base" id="base">
    <label for="high"> High:  </label>
    <input type="text" name="high" id="high">
    <br><br>
    <input name="query" type="submit" id="query" value="Query" class="btn btn-info">
    <input name="reset" type="reset" id="reset" value="Reset" class="btn btn-warning">
    <button name="back" id="back"> <a href="Welcome.jsp"> Come Back </a></button>
    <br>
</fieldset>
</form>

</body>
</html>


<%try {
    if(request.getParameter("query")!= null){
        //ArrayList<Star> starsInRectangle = StarController.findStarInRectangle(regionBean);
        if(regionBean.getBase()<=0 || regionBean.getHigh()<=0){
            %><p class="text-info"> <I><U><h3>
            <span style="color: red; ">  Base and High cannot be NEGATIVE or ZERO!  </span>
            </h3></U></I></p>
            <% throw new WrongDataException();
        }else {
            HashMap rateByTypeInFilament = StarController.rateByTypeInFilament(regionBean);
            HashMap rateByTypeOutFilament = StarController.rateByTypeOutFilament(regionBean);

            ArrayList<Star> outerStars = StarController.findStarOutOfFilament(regionBean);
            ArrayList<Star> innerStars = StarController.findStarInRectangleAndFilament(regionBean);

            if(outerStars.size() == 0 && innerStars.size() == 0){
                %><p class="text-info"> <I><U><h3>
                    <span style="color: red; "> There aren't stars with required feaures !</span>
                </h3> </U></I></p>
                <% throw new NoDataFoundException();
            }

            double rateOuterStar = (double) outerStars.size()/(outerStars.size()+innerStars.size())*100;
            double rateInnerStar = (double) 100-rateOuterStar;
            %><fieldset>
                <legend> <h4>Found <% out.print(rateOuterStar); %> % of stars OUT of filaments </h4></legend> <%
                for(TypeOfStars allType : TypeOfStars.values()){
                    String key = allType.toString();
                    Double value = (Double) rateByTypeOutFilament.get(key);
                    out.println(key + ":  " + value + "  %");
                %> <br> <%
                } %>
            </fieldset>
            <fieldset>
                <legend> <h4>Found <% out.print(rateInnerStar); %> % of stars INTO filaments </h4></legend> <%
                for(TypeOfStars allType : TypeOfStars.values()){
                    String key = allType.toString();
                    Double value = (Double) rateByTypeInFilament.get(key);
                    out.println(key + ":  " + value + "  %");
            %> <br> <%
                } %>
            </fieldset>
            <br><br>
            <table border="2">
                <tr>
                    <th>ID </th>
                    <th>Name </th>
                    <th>Type </th>
                    <th>Flux </th>
                    <th>Longitude </th>
                    <th>Latitude</th>
                    <th>Included into a filament </th>
                </tr> <%
                for (Star aStarsInRectangle : outerStars) {
            %>
                <tr>
                    <td><% out.print(aStarsInRectangle.getID()); %></td>
                    <td><% out.print(aStarsInRectangle.getName()); %></td>
                    <td><% out.print(aStarsInRectangle.getType()); %></td>
                    <td><% out.print(aStarsInRectangle.getFlux()); %></td>
                    <td><% out.print(aStarsInRectangle.getLongitude()); %></td>
                    <td><% out.print(aStarsInRectangle.getLatitude()); %></td>
                    <td><% out.print("NO"); %></td>
                </tr>
                <%}
                    for (Star aStarsInRectangle : innerStars) {
                %>
                <tr>
                    <td><% out.print(aStarsInRectangle.getID()); %></td>
                    <td><% out.print(aStarsInRectangle.getName()); %></td>
                    <td><% out.print(aStarsInRectangle.getType()); %></td>
                    <td><% out.print(aStarsInRectangle.getFlux()); %></td>
                    <td><% out.print(aStarsInRectangle.getLongitude()); %></td>
                    <td><% out.print(aStarsInRectangle.getLatitude()); %></td>
                    <td><% out.print("YES"); %></td>
                </tr>
                <%}%>
            </table> <%
            }
        }
    } catch (WrongDataException | NoDataFoundException e){
        e.printStackTrace();
    }%>