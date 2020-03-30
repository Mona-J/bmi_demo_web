package BmiUtil;

import FunctionLayer.Sport;

import java.util.List;

public class BmiHelperFunctions
{
    //** Creating independent BMI related Methods -  Independent methods, makes Unit-testing easier/efficient **

    //Create a method to calculcate BMI
    public static Double calcBmi(Double height, Double weight) //height and weight in its parameters.
    {
        //return the Bmi equation
        return weight/(height * height) *100 *100;
    }

    //Create a method to set the bmi 'kategori' conditions
    public static String findKategori(Double bmi)//take in 'bmi' as a parameter.
    {
        //initialising 'kategori'
        String kategori = "";

        //if statements that set the bmi 'kategori' conditions
        if (bmi <18.5) //if bmi is less than 18.5
        {
            kategori = "Undervægtig"; //then set kategori to "Undervægtig"
        }
        else if (bmi < 25.0)
        {
            kategori = "Normalvægtig";
        }

        else if (bmi > 30.0)
        {
            kategori = "Svært overvægtig";
        }
        else // else, if none of the above
        {
            kategori = "Overvægtig"; //then set kategori to "Overvægtig"
        }
        //the condition is returned after the bmi calculation is done
        return kategori;
    }

    //create a method that contains 'Sport' obj (Class) to get a sport category getting the sportList.
    public static Sport getSportCategoryById(int id)
    {
        //Call/get the 'sportlist' from "Initializer" and save it in the 'sportList' which contains a list of Sport obj.
        List<Sport> sportList = Initializer.getSportList();

        // loop through the sport_Id in sportList.
        for(Sport sport : sportList)
        {
            if(sport.getSports_id() == id)
            {
                return sport;
            }
        }
        // return nothing if it doesn't exist.
        return null;
    }
}
