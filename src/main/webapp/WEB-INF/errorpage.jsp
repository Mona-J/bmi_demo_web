
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--this tag gets code from the header file -->
<%@include file="../includes/header.inc" %>

<dic class="alert alert-danger" role="alert">
        <c:if test = "${requestScope.error!= null}" >

           <h2>Der er opstået et fejl! </h2>
            ${requestScope.error}
        </c:if>
</dic>
<!-- ** Create a hyperlink redirects to "index.jsp" when pressed by going through "Frontcontroller" to reach it.
Note: works with "Redirect" class
-> “redirect” is a command obj that carries a parameter “destination” set to the destination ** -->
<p><a href="FrontController?target=redirect&destination=index">Til Forsiden</a></p>

<!--this tag gets code from the footer file -->
<%@include file="../includes/footer.inc" %>