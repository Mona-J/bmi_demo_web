<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--this tag gets code from the header file -->
<%@include file="../includes/header.inc" %>

<!-- ** This is the Admin page after logging in ** -->
        <h1>Hello ${sessionScope.email} </h1>

        <h3>Her kan du gøre følgende:</h3>
        <!-- ** Create an unordered list that contains hyperlinks - redirects to desired page **-->
        <ul>
        <!-- 1. redirects to "kategorier.jsp" when pressed by going through "Frontcontroller" to reach it.
        Note: works with "Redirect" class
         -> “redirect” is a command obj that carries a parameter “destination” set to the destination  -->
         <li><a href="FrontController?target=redirect&destination=kategorier">Rette kategorier</a></li>
        <!-- 2. redirects to "index.jsp" when pressed by.. -->
        <li><a href="FrontController?target=redirect&destination=index">Til Forsiden</a></li>
        </ul>

<!--this tag gets code from the footer file -->
<%@include file="../includes/footer.inc" %>
