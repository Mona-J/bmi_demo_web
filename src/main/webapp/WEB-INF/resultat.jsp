
<!-- tag needed to use "jstl" - only if it's not added in the pom.xmæ file with the dependiencis -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--this tag gets code from the header file -->
<%@include file="../includes/header.inc"%> <!-- "../" allows the tag to go 2 levels up to find the include folder -->

    <!-- ** columns and rows are used to make the allignment of the output responsive l. 22, 24, 56** -->
    <!-- 1. creating a row to make columms -->
    <div class="row">
        <!--2. creating columm 1 in the row with a size 4/12 on a medium (md) screen.
         This reserves the first 4 grids on the page-->
        <div class="col-md-4"></div>
        <!--3. creating columm 2 in the row with a size 4/12 on a medium (md) screen.
        This includes all fields -->
        <div class="col-md-4">

            <!-- class="text-center": alligns the text in the middle of the page -->
            <h1 class="text-center">BMI Resultat</h1>

            <!-- ** Creating a table that will contain rows/columns to display the output ** -->
            <!-- "table"  puts the table in a table form (with dividers) -->
            <!-- "table-striped" makes the table  grey and white (stripes-pattern) -->
            <table class="table table-striped">
                <!-- Creating 4 rows with 2 columns each - "tr": table row, "td": table columns -->
                <tr>
                    <td>Højde</td>
                    <!-- getting the "height" attribute from the "Frontcontroller" who got it from "Resultat" -->
                    <td>${requestScope.height}</td>
                </tr>
                <tr>
                    <td>Vægt</td>
                    <!-- getting the "weight" attribute from the "Frontcontroller" who got it from "Resultat" -->
                    <td>${requestScope.weight}</td>
                </tr>
                <tr>
                    <td>BMI</td>
                    <!-- getting the "bmi" attribute from the "Frontcontroller" who got it from "Resultat" -->
                    <td>${requestScope.bmi}</td>
                </tr>
                <tr>
                    <td>Kategori</td>
                    <!-- getting the "kategori" attribute from the "Frontcontroller" who got it from "Resultat" -->
                    <td>${requestScope.kategori}</td>
                </tr>
            </table>

            <!--printing out the 'gender', 'sport' and List values from "Resultat" -->
            <p>${requestScope.gender}</p>
            <p>${requestScope.sport}</p>
            <p>
                <!-- forEach to print List - 'infoitem': a var/string attribute to store 'infos'
                1. create 'infoitem'
                2. Loop through 'infos'
                3. save the looped 'infos' in  'infoitem'
                4. print out the values of 'infoitem' (which is now 'infos') -->
                <c:forEach var="infoitem" items="${requestScope.infos}">
                id: ${infoitem}<br/> <!-- "br" creates a break after every printed value-->
                </c:forEach>
            </p>

            <!-- alligning the hyperlink to the center -->
            <div class="text-center">
                <!-- Creating a hyperlink that goes through the Frontcontroller to reach the index page (BMI page).
                 “redirect” is a command obj that carries a parameter “destination” set to the destination -->
                <a href="FrontController?target=redirect&destination=index">Til Forsiden</a>
            </div>

        </div> <!-- column 2 closing tag -->
        <!-- 4. creating column 3 in the row with a size 4/12 on a medium (md) screen.
        This reserves the last 4 grids on the page-->
        <div class="col-md-4"></div>
    </div>  <!-- row closing tag -->

<!--this tag gets code from the footer file -->
<%@include file="../includes/footer.inc"%> <!-- "../" allows the tag to go 2 levels up to find the include folder -->
