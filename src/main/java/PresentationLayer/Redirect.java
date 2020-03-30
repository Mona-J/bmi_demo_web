package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This class redirects the destination of "resultat.jsp" to "index.jsp".
public class Redirect extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException
    {
        //creating an attribute that gets the "destination" parameter from the "resultat.jsp" page.
        String destination = request.getParameter("destination");

        //switch block to set more attributes on the page that’s being sent out before it reaches the destination.
        switch (destination)
        {
            //if the redirected destination is 'index', then go there with a message - set this 'key-value' Attribute to:
            //NOTE: the value will be displayed on "index.jsp" using “${requestScope.message}”. after 'resultat.jsp' gets redirected to it.
            case "index": request.setAttribute("message","Alt er godt!");
            break; //break out of the switch block.

            //if the destination is 'about', then go there with a message - set this 'key-value' Attribute to:
            case "about": request.setAttribute("message","Alt er super godt!");
            break;

            //if the redirected destination is 'login', then just go there - no message to be shown.
            case "login":
            break;

            //if the redirected destination is 'kategorier', then just go there - no message to be shown.
            case "kategorier":
            break;
            //create a default case if none of the above cases match - i.e. if the redirected destination page doesn't exist.
            default: request.setAttribute("message","siden findes ikke!");//then display this message
        }
        //return destination after it's gotten
        return destination;
    }
}
