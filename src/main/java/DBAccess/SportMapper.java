package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Sport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This class Connects (maps) to DB and gets/updates/removes the "sport" data from it.
public class SportMapper {

    //** Method that gets the sport data **
    //1. create a method that returns a list of Sport objs - Sport = Class from function Layer
    public static List<Sport> getAllSports() throws LoginSampleException {
        List<Sport> sportList = null; //create an empty list 'sportList'

        //try-catch block in case an error occurs.
        try {
            //2. start the connection by calling ".connection()" method from the "Connector" class
            Connection con = Connector.connection();
            //3. create an SQL statement - select all from the 'sport' table
            String SQL = "SELECT * FROM sport";
            //4. insert the SQL statement into the ".preparedStatment()" method - it sends the SQL statement to the DB
            PreparedStatement ps = con.prepareStatement(SQL);
            //5. call the ".executeQuery()" to execute the SQL statement and return the result (stored in ResultSet).
            ResultSet rs = ps.executeQuery(); //works with getters/setters from "Sport" class

            //6. while there is a next 'rs' (result i.e element) - do the following code:
            while (rs.next())
            {
                //if the 'sportList' is empty
                if (sportList == null) {
                    sportList = new ArrayList<>(); //design choice - to easily switch to ArrayList implementation

                }
                //get the result of the 'sport_id' and 'name' (the data rows)
                int sport_id = rs.getInt("sport_id");
                String name = rs.getString("name");
                //create a new sport obj of 'Sport' class and pass the gotten data in it (sport_id & name)
                Sport sport = new Sport(sport_id, name);// data gets stored in 'sport'
                //add the gotten 'sport' data to the 'sportList'
                sportList.add(sport);
            }
        }
            //catch the SQLException
            catch(ClassNotFoundException | SQLException ex ){
                throw new LoginSampleException(ex.getMessage()); //get the error message
            }
            // return the gotten 'sport' data from the DB
            return sportList;
        }

        //** Method that updates the sport data  **
        //1. create a void method that updates the data into DB - nothing to return, just show/update.
        //NOTE: these are the same parameters as the ones in the 'sport' table in DB.
        public static void updateSport (int sport_id, String name) throws LoginSampleException
        {
            //try-catch block in case an error occurs.
            try {
                //2. start the DB connection by calling ".connection()" method from the "Connector" class
                Connection con = Connector.connection();
                //3. create an SQL statement - to Update the 'sport' table
                String SQL = "UPDATE sport SET name = ? WHERE sport_id = ?"; //creating 2 placeholders to insert values
                //4. insert the SQL statement into the ".preparedStatment()" method - it sends the SQL statement to the DB
                PreparedStatement ps = con.prepareStatement(SQL);
                //put the value of name into placeholder 1 (height) etc.
                ps.setString(1, name);
                ps.setInt(2, sport_id);
                //5. call the ".executeUpdate()" to execute/insert/ run the SQL statement and return the rows affected. Also to, update 'sport' table.
                int result = ps.executeUpdate(); //inserts the first 'sport' result in DB and saves it in 'result'
            }
            //catch the SQLException
            catch (ClassNotFoundException | SQLException ex)
            {
                throw new LoginSampleException(ex.getMessage()); //get the error message
            }
        }

    //** Method that removes a sport data  **

    //1. create a void method that removes a data into DB - nothing to return, just show/update.
    //NOTE: this is the same parameter as the one in the 'sport' table in DB.
    public static void deleteSport (int sport_id) throws LoginSampleException {

        //try-catch block in case an error occurs.
        try {
            //2. start the DB connection by calling ".connection()" method from the "Connector" class
            Connection con = Connector.connection();
            //3. create an SQL statement - to Update the 'sport' table
            String SQL = "DELETE FROM sport WHERE sport_id = ?"; //creating 1 placeholder
            //4. insert the SQL statement into the ".preparedStatment()" method - it sends the SQL statement to the DB
            PreparedStatement ps = con.prepareStatement(SQL);
            //put the value of sport_id into placeholder 1
            ps.setInt(1, sport_id);
            //5. call the ".executeUpdate()" to execute/insert/ run the SQL statement and return the rows affected. Also to, update 'sport' table.
            int result = ps.executeUpdate(); //inserts the first 'sport' result in DB and saves it in 'result'
        }
        //catch the SQLException
        catch (ClassNotFoundException | SQLException ex)
        {
            if(ex.getMessage().startsWith("Cannot delete or update a parent row"))
            {
                throw new LoginSampleException("Du kan ikke fjerne en sportskategori som allerede er anvendt i DB"); //get the error message
            }
            else {
                throw new LoginSampleException(ex.getMessage());
            }
        }
    }
}
