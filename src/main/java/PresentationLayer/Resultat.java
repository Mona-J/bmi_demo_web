package PresentationLayer;

import BmiUtil.BmiHelperFunctions;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class Resultat extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException
    {
        //initializing the attributes (that'll be typed by the user)
        Double weight = 0.0;
        Double height = 0.0;

        //** These parameters/attributes will be carried by the request obj throughout the page-to-page communication/navigation. **

        //Getting the parameters from the "index page" and storing them in attributes
        try
        { // try - catch block: in case of error from user side.
            //using "Double.parseDouble()" method to convert and return the gotten string as a double.
            weight = Double.parseDouble(request.getParameter("weight"));//"index page" parameter that'll be typed in by the user
            height = Double.parseDouble(request.getParameter("height"));
        }
        catch (Exception e)
        {
            //This message will be sent to the “Frontcontroller”, which has a method: “ex.getMessage()” to print it.
            //It will then be sent to the “login.jsp” page using the “.getRequestDispatcher()” method.
            throw new LoginSampleException("Indtast et tal some højde eller vægt.");
        }

        //creating a "bmi" double attribute that calls the calcBmi() method from the "BmiHelperFunctions" in "BmiUtil" folder.
        //This method contains the bmi calculation - Note: this gets carried by the request obj throughout the communication Scope.
        Double bmi = BmiHelperFunctions.calcBmi(height,weight);//take in height and weight as parameters.

        //creating a string attribute that calls the ".format()" method to convert the calculated bmi to 2 decimal places.
        String bmi_to_dec = String.format("%.2f", bmi);

        //Creating a string attrbute that calls the "findkategori" method from the "BmiHelperFunctions" in "BmiUtil" folder.
        String kategori = BmiHelperFunctions.findKategori(bmi);//set the condition on the calculated "bmi"

        //Creating a string attribute that 'catches' (gets) the "gender" parameter from the "index.jsp" page
        String gender = request.getParameter("gender");

        //Creating an int variable that will 'catch' (get) the "sport" parameter from the "index.jsp" page
        int sport = 0;
        //try-catch block to convert the gotten "sport" parameter from string to int - done because its value is an int.
        try
        {
            sport= Integer.parseInt(request.getParameter("sport"));//converting String to Int
        }
        //exception unlikely to appear as it's not something that's being prompted from a user.
        catch (Exception e)
        {
            throw new LoginSampleException("Fejl i sport parameter");
        }

        //Creating a String Arraylist "infos" to store and get the values of the sport "info" from "index.jsp"
        String[] infos = request.getParameterValues("info");

        // ** Creating a list that converts the above ArrayList to a List - done to convert the 'original' array to a list **

        //initilizing the List to null (empty)
        List<String> infoList = null;

        //if the arrayList 'infos'(checkboxes) is not 'empty' then convert it to a List
        if (infos != null) // this is done to avoid getting an empty List.
        {
            infoList = Arrays.asList(infos);//convert "infos" to a List
        }

        //** "Stamping data on the request obj" - These attributes will go through the "frontcontroller" and then get sent to "resultat.jsp" **
        //NOTE: This is done to use the data on the "modtager" page (resultat.jsp)
        //NOTE: this data could also be an Arraylist from the database - a forEach loop would be used on the jsp page to print it out.

        //Setting the gotten parameters to the attributes that's storing them
        request.setAttribute("height",height);
        request.setAttribute("weight",weight);//key: 'weight', value: 'the gotten weight parameter'

        //key: the gotten 'kategori' from frontcontroller, value: 'the if statements'
        request.setAttribute("kategori", kategori);
        //Key: "calculated bmi", value: "the converted result to 2 dp"
        request.setAttribute("bmi", bmi_to_dec);//"a calculation" so no parameter is gotten from the user.
        request.setAttribute("gender", gender);
        request.setAttribute("sport", sport);
        request.setAttribute("infos", infoList); //key: "the ArrayList", value: "The converted ArrayList"

        //call "insertBmiItem()" method from "LogicFacade" and insert the above 'stamped' attributes into it.
        LogicFacade.insertBmiItem(height, weight, kategori, bmi, gender, sport, infoList); //This data will be displayed in DB

        return "resultat";

    }
}
