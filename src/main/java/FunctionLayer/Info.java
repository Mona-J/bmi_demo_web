package FunctionLayer;

//This class creates attributes that are identical to the ones in the 'info' table in DB
//Setters and Getters are used to allow access (get and set) the info data from DB (works with InfoMapper)
public class Info {

    private int info_id;
    private String name;

    //Constructor to initialize the attributes before they're used.
    public Info(int info_id, String name)
    {
        this.info_id = info_id;
        this.name = name;
    }

    //GETTERS
    public int getInfo_id() {
        return info_id;
    }

    public String getName() {
        return name;
    }

    //SETTERS
    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
