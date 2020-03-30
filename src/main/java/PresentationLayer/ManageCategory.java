package PresentationLayer;

import BmiUtil.BmiHelperFunctions;
import BmiUtil.Initializer;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCategory extends Command
{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException
    {
        //..if "edit" button is pressed then go to a jsp page where it can be changed to something else (edited).??

            //get the 'fjernknap' parameter data that's carried by the request obj and store it in 'fjernId'
            //NOTE: catches this parameter while its going through "FrontController".
            String fjernId = request.getParameter("fjernknap");
            //get the 'retknap' parameter data that's carried by the request obj and store it in 'retId'
            String retId = request.getParameter("retknap");
            //get the 'action' parameter (edited category) from the 'retkategori.jsp' page.
            String action = request.getParameter("action");

            int retIdInt = 0;

        //if the 'fjernId' doesn't exist??.
        if (fjernId != null)
        {
            action = "fjern";
        }

        else if(retId != null)
        {
            action = "ret";
        }
        else if(action != null)
        {
            action = "opdater";
        }

        switch (action)
        {
            case "fjern":

                //convert fjernId from int to String so it works with 'sport' below.
                int fjernIdInt = Integer.parseInt(fjernId);
                //TODO - When 'Fjern' button is pressed:
                System.out.println("Fjernid: " + fjernIdInt);
                // 1. Remove a category in 'Sport' table by connecting to DB.
                LogicFacade.deleteSport(fjernIdInt);
                Initializer.initSportList();
                // TODO 2. Update the changes in the 'sportList' in application scope.
                request.getServletContext().setAttribute("sportList", Initializer.getSportList());
                // TODO 3. go back to "kategorier.jsp"

                return "kategorier";

            case "ret":

                retIdInt = Integer.parseInt(retId);
                //TODO: - When 'Fjern' button is pressed:
                System.out.println("Retid: " + retId);
                request.setAttribute("retId", retId);
                request.setAttribute("sport", BmiHelperFunctions.getSportCategoryById(retIdInt));

                return "retkategorier";


            case "opdater":

                    // TODO 1. Update a category in 'Sport' table by connecting to DB.
                    //gets 'sportid' parameter from the 'retkategorirer.jsp' page.
                    retIdInt = Integer.parseInt(request.getParameter("sportid"));
                    //the new text that will get updated in DB
                    String kategoritekst = request.getParameter("kategori");
                    LogicFacade.updateSport(retIdInt, kategoritekst);
                    Initializer.initSportList();
                    //TODO 2. Update the changes in the 'sportList' in application scope.
                    request.getServletContext().setAttribute("sportList", LogicFacade.getAllSports());
                    //TODO  3. go back to "kategorier.jsp"

                    return "kategorier";

            default : return "kategori";
        }
    }
}

