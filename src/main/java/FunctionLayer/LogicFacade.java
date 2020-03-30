package FunctionLayer;

import DBAccess.BmiMapper;
import DBAccess.InfoMapper;
import DBAccess.SportMapper;
import DBAccess.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */

/* This class contains the 'Logic' part - it puts all the 'logic' together by calling the created methods to run them.
    NOTE: These are methods that call the created the methods.
 */

public class LogicFacade {

    //call the static method that logs a user in - static = can be called without creating an obj.
    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    }

    //call the static method that creates a user - static = can be called without creating an obj.
    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    //call the static method that gets the sports data from DB - static = can be called without creating an obj.
    public static List<Sport> getAllSports() throws LoginSampleException
    {
        return SportMapper.getAllSports(); //return 'getAllSports()' method from SportMapper

    }

    //call the static method that gets the info (interests) data from DB - static = can be called without creating an obj.
    public static List<Info> getAllInfos() throws LoginSampleException
    {
        return InfoMapper.getAllInfos(); //return 'getAllinfos()' method from InfoMapper
    }

    //call the static method that inserts the bmi_entry data into DB - static = can be called without creating an obj.
    public static void insertBmiItem (double height, double weight, String category, double bmi,
                                      String gender, int sport_id, List <String> infoList) throws LoginSampleException {

        //call the "insertBmiItem" method from "BmiMapper" and pass in it every bmi_entry data.
        BmiMapper.insertBmiItem(height, weight, category, bmi, gender, sport_id, infoList);
    }

    //call the static that updates the 'sport' data into DB - static = can be called without creating an obj.
    public static void updateSport (int sport_id, String name) throws LoginSampleException
    {
        //call the 'updateSport' method from  'SportMapper' and pass in the 'sport_id and name' to be updated.
        SportMapper.updateSport(sport_id, name);
    }
    //call the static that deletes the 'sport' data into DB - static = can be called without creating an obj.
    public static void deleteSport(int sport_id) throws LoginSampleException
    {
        //call the 'deleteSport' method from  'SportMapper' and pass in the 'sport_id' to be deleted.
        SportMapper.deleteSport(sport_id);
    }
}
