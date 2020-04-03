package PresentationLayer;

import FunctionLayer.LoginSampleException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    // method that's started and run by the browser when the program is run to connect the jsp pages to the their classes
    // NOTE: it 'looks through' the 'hashmap' to see if a jsp page exists and which class it belongs to..
    private static void initCommands() {
        commands = new HashMap<>();
        commands.put( "login", new Login() );
        commands.put( "register", new Register() );
        commands.put( "resultat", new Resultat() );
        commands.put("redirect", new Redirect() );
        commands.put("managecategory", new ManageCategory() );

    }

    // get's the 'target' parameter to know destination.
    static Command from( HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws LoginSampleException;

}
