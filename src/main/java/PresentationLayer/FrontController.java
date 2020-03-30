/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LoginSampleException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 @author kasper
 */
@WebServlet( name = "FrontController", urlPatterns = { "/FrontController" } )
public class FrontController extends HttpServlet {

    /**
     Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     methods.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        //try-catch to Fix Danish Characters.
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        try {
            Command action = Command.from( request );
            //** Setting the destination condition **
            String view = action.execute( request, response );
            //if the "view" (the page to be viewed) is 'index
            if (view.equals("index"))
            {
                //then call the ".getRequestDispatcher()" to send out the page to it's destionation.
                // Note: this happens if the destination page is NOT in the 'WEB-INF' folder
                request.getRequestDispatcher(view + ".jsp").forward(request,response);//send out the request/response objs too
            }
            //else, if the destination page is in the 'WEB-INF' folder then...
            else {
                request.getRequestDispatcher( "/WEB-INF/" + view + ".jsp" ).forward( request, response );
            }
        }
        catch ( LoginSampleException ex )
        {
            //"ex.getMessage()" method that gets the error message when a new LoginSampleException is thrown
            request.setAttribute( "error", ex.getMessage() );//key: "error", value: "method to get the error message"
            //.getRequestDispatcher() method that sends the error to the page provided in its parameter.
            request.getRequestDispatcher( "/WEB-INF/login.jsp" ).forward( request, response );
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     Handles the HTTP <code>GET</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
