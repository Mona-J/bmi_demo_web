<%@ page import="BmiUtil.Initializer" %>
<!-- tag needed to use "jstl" - only if it's not added in the pom.xmæ file with the dependiencis -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--this tag gets code from the header file -->
<%@include file="includes/header.inc" %>

<!-- special tag: used to insert java code -->
<%
    /*  "Stamping data on the request obj" - These attributes will get sent to DB by going through several classes (see notes).  **
        NOTE: This is done here, to display the gotten data from DB in the 'boxes' below.
       --> we are making sure that the lists exists in the "applicationScope", so they can be pulled below in the 'boxes'.
     */


    //1. if the gotten 'sportList' is null i.e "i am the first one using this application"
    if (request.getServletContext().getAttribute("sportList") == null) {
        //2. then call the 'getSportList()' from 'Initializer' class to set it.
        //NOTE: "getServletContext()" sets attribute on the whole application scope instead of a session of it.
        //NOTE: key: 'sportList', value: 'getSportList()' method from 'Initializer' class
        request.getServletContext().setAttribute("sportList", Initializer.getSportList());
    }
    //3. if the gotten 'infoList' is null i.e.  "i am the first one using this application"
    if (request.getServletContext().getAttribute("infoList") == null) {
        //4. then call 'getInfoList()' from 'Initializer' class to set it.
        request.getServletContext().setAttribute("infoList", Initializer.getInfoList());
    }
%>

<!-- ** columns and rows are used to make the allignment of the input responsive l. 22, 24, 56** -->
<!-- 1. creating a row to make columms -->
<div class="row">
    <!--2. creating columm 1 in the row with a size 4/12 on a medium (md) screen.
     This reserves the first 4 grids on the page-->
    <div class="col-md-4"></div>
    <!--3. creating columm 2 in the row with a size 4/12 on a medium (md) screen.
    This includes all fields -->
    <div class="col-md-4">

        <!-- ** Creating a hyperlink that Redirects to login page by going through "Frontcontroller" to reach it.
        Note: works with "Redirect" class
         -> “redirect” is a command obj that carries a parameter “destination” set to the destination ** -->
        <p class="text-center"><a href="FrontController?target=redirect&destination=login">Log ind</a></p>

        <!-- class="text-center": alligns the text in the middle of the page -->
        <h1 class="text-center">BMI Beregner</h1>
        <!-- Form action: go to this page using 'HTTP post' method, when the 'sumbit' button is pressed.-->
        <form action="FrontController" method="post"> <!-- "afsender side" -->
            <!-- Specifying that the target page is "resultat.jsp -->
            <input type="hidden" name="target" value="resultat"/>

            <!-- ** Creating a form/field for height (a place to intype info) **  -->
            <!--NOTE: the "value" here is the value of the input typed by the user  -->
            <form>
                <div class="form-group"> <!-- this tag creates space between the elements within it -->
                    <!--label and input fields work together - "label for" and  "id" should be the same for it to work-->
                    <label for="height">Indtast din højde i cm:</label>
                    <!--This: name="height" is the name of the form parameter.
                    it allows the height attribute to reach the frontcontroller -->
                    <!-- id is required when the 'label for' is used. the names must match -->
                    <input type="text" class="form-control" id="height" name="height" aria-describedby="heightHelp">
                </div>

                <!-- ** Creating a form/field for weight (a place to intype info) ** -->
                <!--NOTE: the "value" here is the value of the input typed by the user  -->
                <div class="form-group"> <!-- this tag creates space between the elements within it -->
                    <!--label and input fields work together - "label for" and  "id" should be the same for it to work-->
                    <label for="weight">Indtast din vægt i kg:</label>
                    <!-- input type is set to "text" as we are working with simple text-->
                    <!--This: name="weight" is the name of the form parameter.
                   it allows the height attribute to reach the frontcontroller -->
                    <input type="text" class="form-control" id="weight" name="weight">
                    <!--creates a small transparent text under the weight field -->
                    <!-- id is required when the 'label for' is used. the names must match -->
                    <small id="heightHelp" class="form-text text-muted">Det er vores lille hemmelighed :-)</small>
                </div>

                <!-- ** Creating an new input type: "Radio box" - to choose gender ** -->
                <!-- 1st Radio box
                NOTE: make sure "value" is correct - used to get the value in 'backend' when its called with its attribute.
                NOTE: "name" is the form parameter and attribute for value and it must match for both for Radio boxes.
                NOTE: "id" and "for" must match so it works -->
                <div class="form-check form-check-inline">
                    <!--form-check-inline is used to make the form in one line -->
                    <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="kvinde"
                           checked>
                    <label class="form-check-label" for="exampleRadios1">
                        Kvinde
                    </label>
                </div>

                <!-- 2nd Radio box
                NOTE: make sure "value" is correct - used to get the value in 'backend'
                NOTE: "name" is the form parameter and attribute for value and it must match for both for Radio boxes.
                NOTE: "id" and "for" must match so it works -->
                <div class="form-check form-check-inline">
                    <!--form-check-inline is used to make the form in one line -->
                    <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="mand">
                    <label class="form-check-label" for="exampleRadios2">
                        Mand
                    </label>
                </div>

                <!-- ** Creating an new input type: "Drop-down box" - to choose a workout  ** -->
                <div class="form-group"> <!-- this tag creates space between the elements within it -->
                    <label for="exampleFormControlSelect1">Angiv din primære idræt:</label>
                    <select class="form-control" name="sport" id="exampleFormControlSelect1">
                        <!--added: Form name "name="sport""-->

                        <!-- using forEach loop to get and display the 'sportList' data from the applicationScope -->
                        <c:forEach var="sportsItem" items="${applicationScope.sportList}"> <!-- items = loop though 'sportList' -->
                            <!-- get 'sports_id' and 'name' from 'sportsItem' to generate the options -->
                            <option value="${sportsItem.sports_id}">${sportsItem.name}</option>
                        </c:forEach>
                    </select>
                </div>

                    <!--** OLD "Drop-down" box - made 'dynamic' using ForEach code above -->

                    <!--  TMP displayed options
                     <label for="exampleFormControlSelect1">Angiv din primære idræt:</label>
                <select class="form-control" name="sport" id="exampleFormControlSelect1">
                    <option value="1">Jogging</option>  tmp Value: later it will be gotten from  DB
                    <option value="2">Fodbold</option>
                    <option value="3">Håndbold</option>
                    <option value="4">Gymnastik</option>
                    <option value="5">Yoga</option>
                    <option value="6">Andet</option>
                    </select>
                    </div>
                    -->

                <!-- ** Creating an new input type: "Check box" - to choose interests (info) ** -->

                <!-- using forEach loop to get and display the 'infoList' data from the applicationScope -->
                <c:forEach var="infoItem" items="${applicationScope.infoList}"> <!-- items = loop though 'infoList' -->
                    <div class="form-check">
                        <!-- 'id' & 'for' must match-->
                        <input class="form-check-input" type="checkbox" name="info" value="${infoItem.info_id}" id=for="defaultCheck${infoItem.info_id}">
                        <label class="form-check-label" for="defaultCheck${infoItem.info_id}">
                            <!--printing the name of each info_id -->
                                ${infoItem.name}
                        </label>
                    </div>
                </c:forEach>

                <!-- ** OLD Checkboxes - made "dynamic" using Foreach above ** -->
                <!--added: Form name "name="info"" for all-->

                <!-- OLD 1st Checkbox
                <div class="form-check">
                NOTE: tmp Value: used to get something specific from e.g DB
               <input class="form-check-input" type="checkbox" name="info" value="1" id="defaultCheck1">  NOTE: "id" and "for" must match
                <label class="form-check-label" for="defaultCheck1">
                Jeg går op i sund kost. NOTE: this will be something that's gotten from the DB with an "id"
                </label>
                </div>
                 -->

                <!-- OLD: 2nd Checkbox
                <div class="form-check">
                NOTE: tmp Value: used to get something specific from e.g DB
                <input class="form-check-input" type="checkbox" name="info" value="2" id="defaultCheck2"> NOTE: "id" and "for" must match
                <label class="form-check-label" for="defaultCheck2">
                    Jeg har et sommer hus. NOTE: this will be something that's gotten from the DB with an "id"
                </label>
                </div>
                -->

                <!-- OLD: 3rd Checkbox
                <div class="form-check">
                NOTE: tmp Value: used to get something specific from e.g DB  -
                <input class="form-check-input" type="checkbox" name="info" value="3" id="defaultCheck3"> NOTE: "id" and "for" must match
                <label class="form-check-label" for="defaultCheck3">
                    Jeg har et kældedyr. NOTE: this will be something that's gotten from the DB with an "id"
                </label>
                </div>
                -->

                <!-- ** Creating a button with sumbit type to sumbit the inputted data ** -->
                <div class="text-center"> <!--alligning the button to the center -->
                    <button type="submit" class="btn btn-primary">Beregn BMI</button>
                </div>
            </form>

            <!-- printing out the value of the 'message' attribute from the "Redirect" class -->
            <h4>${requestScope.message}</h4>

    </div> <!-- column 2 closing tag-->
    <!-- 4. creating column 3 in the row with a size 4/12 on a medium (md) screen.
    This reserves the last 4 grids on the page-->
    <div class="col-md-4"></div>
</div>
<!-- row closing tag-->

<!--this tag gets code from the footer file -->
<%@include file="includes/footer.inc" %>
