package DataLayer;

import BusinessLayer.IDeliveryServiceProcessing;
import DataLayer.User;

public class Administrator extends User {
    private int adminID;

    public Administrator(String username, String password) {
        super(username, password);
    }

    public Administrator(String username, String password, int adminID){
        super(username, password);
        this.adminID = adminID;
    }


}
