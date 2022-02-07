package DataLayer;

import BusinessLayer.IDeliveryServiceProcessing;
import DataLayer.User;

public class Client extends User {
    private int clientID;
    private int ordersNr;

    public Client(){
        super();
    };

    public Client(String username,String password) {
        super(username, password);
    }

    public Client(String username, String password, int clientID) {
        super(username, password);
        this.clientID = clientID;
    }

    public int getOrdersNr() {
        return ordersNr;
    }

    public void setOrdersNr(int ordersNr) {
        this.ordersNr = ordersNr;
    }

    public int getClientID() {
        return clientID;
    }
}
