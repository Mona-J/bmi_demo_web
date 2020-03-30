package BmiUtil;


import FunctionLayer.Info;
import FunctionLayer.LogicFacade;
import FunctionLayer.Sport;

import java.util.List;

/*
NOTE 1: This class ‘initializes’ (gives a start value) to the called methods from the “LogicFacade”,
 in case they don’t have one already.


NOTE 2: what's exactly being returned?
        the "sportList" which calls ".getAllSports()" method
       from “LogicFacade” which calls the “getAllSports()” from the “SportMapper”
       --> i.e. we are calling a method that connects to DB,
       creates list of sport obj and gets its data (rows) from DB in each 'Getter'.
*/
public class Initializer
{
    //** private - so they need a 'Getters' that can call their data **

    //Initialize a list 'sportList' that contains "Sport" objs to null - 'Sport' --> class
    private static List<Sport> sportList = null;//'start value = empty'
    //Initialize a list 'infoList' that contains "Info" objs to null - 'Info' --> class
    private static List <Info> infoList = null;//'start value = empty'

    //** Method to reload the sportList - used fo reloading when 'ret' is pressed.
    public static void initSportList()
    {
        try //try-catch block in case of error
        {
            //then initialize it - make it call ".getAllSports()" method from the "LogicFacade" class
            sportList = LogicFacade.getAllSports();
        }
        catch (Exception e)
        {
            e.printStackTrace();//print what went wrong.
        }
    }

    //** Method to reload the infoList - used fo reloading when 'ret' is pressed.
    public static void initInfoList()
    {
        try //try-catch block in case of error
        {
            //then initialize it - make it call ".getAllSports()" method from the "LogicFacade" class
            infoList = LogicFacade.getAllInfos();
        }
        catch (Exception e)
        {
            e.printStackTrace();//print what went wrong.
        }
    }
    //** SPORT GETTER **

    //1. Create a public method that gets the private 'sportList' data by calling "getAllSports()" method
    public static List<Sport> getSportList()
    {
        //if the 'sportList' is empty
        if (sportList == null)
        {
            try //try-catch block in case of error
            {
                //then initialize it - make it call ".getAllSports()" method from the "LogicFacade" class
               sportList = LogicFacade.getAllSports();
            }
            catch (Exception e)
            {
                e.printStackTrace();//print what went wrong.
            }
        }
        //else just return the 'sportList'
        return sportList;
    }

    //** INFO GETTER **
    //2. Create a public method that gets the 'private infoList' data by calling "getAllInfos()" method
    public static List<Info> getInfoList()
    {
        //if the 'infoList' is empty
        if(infoList == null)
        {
            try //try-catch block in case of error
            {
                //then initialize it - make it call ".getAllInfos()" method from the "LogicFacade" class
                infoList = LogicFacade.getAllInfos();
            }
            catch (Exception e)
            {
                e.printStackTrace();//print what went wrong.
            }
        }
        //else just return the 'infoList'
        return infoList;
    }
}
