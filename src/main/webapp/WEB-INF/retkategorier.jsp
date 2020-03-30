<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--this tag gets code from the header file -->
<%@include file="../includes/header.inc" %>

<!-- ** This is the "retkategorier" Edit page redirected from "kategorier" after 'ret' is pressed ** -->

<h3>Ret sportskategori:</h3>

<form action="FrontController" method="post">
    <!--value: "where should the buttons go when they are pressed?" -->
    <input type="hidden" name="target" value="managecategory"/>

    <input type="hidden" name="action" value="opdater"/>
    <!-- works with 'sportid' parameter in ManageCategory -->
    <input type="hidden" name="sportid" value="${requestScope.sport.sports_id}"/>

    <!-- ** Creating a form/field for 'ret kategori' (to edit a specific category) ** -->
    <div class="form-group">
        <label for="kategori">Ret kategoriteksten</label>
        <input type="text" class="form-control" id="kategori" name="kategori" value="${requestScope.sport.name}" aria-describedby="heightHelp">
    </div>
    <!--** Create an 'update' button to update the edited category ** -->
    <button type="submit" name="opdater" class="btn btn-primary">Opdater</button>
</form>


    <ul>
        <!-- ** Create a hyperlink redirects to "index.jsp" when pressed by going through "Frontcontroller" to reach it.
        Note: works with "Redirect" class
         -> “redirect” is a command obj that carries a parameter “destination” set to the destination ** -->
        <li><a href="FrontController?target=redirect&destination=index">Til Forsiden</a></li>
    </ul>

    <!--this tag gets code from the footer file -->
    <%@include file="../includes/footer.inc" %>
