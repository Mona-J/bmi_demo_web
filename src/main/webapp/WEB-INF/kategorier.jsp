<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--this tag gets code from the header file -->
<%@include file="../includes/header.inc" %>

<!-- ** This is the "kategorier" Edit page redirected from Admin page after logging in ** -->
        <h1>Hello ${sessionScope.email} </h1>

        <h3>Oversigt over sportskategorier:</h3>

        <!-- ** Create a table that contains the "sportList" info to be edited ** -->   <!-- -->
        <form action="FrontController" method="post">
                <!--value: "where should the buttons go when they are pressed?" -->
                <input type="hidden" name="target" value="managecategory"/>
                <!-- "table"  puts the table in a table form (with dividers) -->
                <table class="table">
                        <!-- 1. create a forEach loop to get and display the "sportList" from the applicationScope -->
                        <c:forEach var="sportItem" items="${applicationScope.sportList}"> <!-- items = loop though 'infoList' -->

                         <!-- Creating 4 rows with 2 columns each - "tr": table row, "td": table columns -->
                          <tr><td>${sportItem.name}</td><td>

                         <!-- ** Creating a "Fjern" and "Ret" button with sumbit type to sumbit data ** -->

                         <!--'name': used to differentiate the 'ret' and 'fjern' buttons. -->
                         <!--'value': used to send the name of the desired sportItem.. -->
                         <button type="submit" name="fjernknap" value="${sportItem.sports_id}" class="btn btn-primary">Fjern</button>
                         <button type="submit" name="retknap" value="${sportItem.sports_id}" class="btn btn-primary">Ret</button>


                          </td></tr>
                        </c:forEach>
                </table>
        </form>

        <ul>
        <!-- ** Create a hyperlink redirects to "index.jsp" when pressed by going through "Frontcontroller" to reach it.
        Note: works with "Redirect" class
         -> “redirect” is a command obj that carries a parameter “destination” set to the destination ** -->
        <li><a href="FrontController?target=redirect&destination=index">Til Forsiden</a></li>
        </ul>

<!--this tag gets code from the footer file -->
<%@include file="../includes/footer.inc" %>
