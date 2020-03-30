package DBAccess;

import FunctionLayer.Info;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.List;


/* This class Connects (maps) to DB and inserts the "bmi" data into the 'bmi_entry' table,
and the 'checkbox' (interest) data of each bmi entry into the 'link_bmi_info' table using the 'bmi_id' and 'info_id'.
 */
public class BmiMapper
{
    //1. create a void method that inserts bmi user entry data into DB - nothing to return, just show.
    //NOTE: these are the same parameters as the ones in the 'bmi_entry' table in DB.
    public static void insertBmiItem (double height, double weight, String category, double bmi,
                                      String gender, int sport_id, List <String> infoList) throws LoginSampleException {

        int newId = 0; // used for "idResultset" below

        //try-catch block in case an error occurs.
        try {
            //2. start the DB connection by calling ".connection()" method from the "Connector" class
            Connection con = Connector.connection();
            //3. create an SQL statement - to Insert into the 'bmi_entry' table values of these parameters:
            String SQL = "INSERT INTO bmi_entry (height, weight, category," +
                    "bmi, gender, sport_id) VALUES (?,?,?,?,?,?)"; //creating 6 placeholders to insert values
            //4. insert the SQL statement into the ".preparedStatment()" method - it sends the SQL statement to the DB
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS); //used with the "generatedKeys" method below
            //put the value of height into placeholder 1 (height) etc.
            ps.setDouble(1, height);
            ps.setDouble(2, weight);
            ps.setString(3, category);
            ps.setDouble(4, bmi);
            ps.setString(5, gender);
            ps.setInt(6, sport_id);
            //5. call the ".executeUpdate()" to execute/insert, to run the SQL statement and return the rows affected. Also to, update 'bmi_entry' table.
            int result = ps.executeUpdate(); //inserts the first 'bmi_entry' result in DB and saves it in 'result'
            //6. call the "getGeneratedKeys()" method to get the auto incremented key (id) in DB table (1 key) - gets a data list (columns)
            ResultSet idResultset = ps.getGeneratedKeys();

            //7. if there is a new ('next') inserted bmi_entry result in DB (Note: pulled by 'id')
            if(idResultset.next())
            {
                //then get the int value - 'get the id of the interest info (chosen checkbox)' and save it in 'newID'
                newId = idResultset.getInt(1); //Note: name on 0, value on 1 - this gets the 'value'

                //and insert it into the 'link_bmi_info' table in DB
                SQL = "INSERT INTO link_bmi_info (bmi_id, info_id) VALUES (?,?)"; //?,? - place holders to insert values
                ps = con.prepareStatement(SQL);//run the SQL statement

                //then loop through the 'infoList' to get the chosen 'checkbox' info.
                for (String info_id : infoList) //and save each result in 'info_id'.
                {
                    //insert a newID for every new 'checkbox' info that comes in the 'infoList'
                    ps.setInt(1, newId); //newID at placeholder 1
                    ps.setInt(2, Integer.parseInt(info_id)); //converts String to int - inserts the gotten 'checkbox' info at placeholder 2.
                    ps.executeUpdate(); // and execute/insert in DB, the SQL statement.
                }
            }
        }
        //catch the SQLException
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new LoginSampleException(ex.getMessage()); //get the error message
        }
    }
}
