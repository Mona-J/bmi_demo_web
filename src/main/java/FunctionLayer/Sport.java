package FunctionLayer;

//This class creates attributes that are identical to the ones in the 'sport' table in DB
//Setters and Getters are used to allow access (get and set) the sport data from DB (works with SportMapper)
public class Sport {

        private int sports_id;
        private String name;

    //Constructor to initialize the attributes before they're used
    public Sport(int sports_id, String name)
    {
        this.sports_id = sports_id;
        this.name = name;
    }

    //GETTERS
    public int getSports_id() {
        return sports_id;
    }

    public String getName() {
        return name;
    }

    //SETTERS
    public void setSports_id(int sports_id) {
        this.sports_id = sports_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
