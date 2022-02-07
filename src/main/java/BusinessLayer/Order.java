package BusinessLayer;

import DataLayer.Client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private ArrayList<MenuItem> products = new ArrayList<>();
    private int orderID;
    private int clientID;
    private Date data;
    private String day;
    private String clientUsername;
    private int price;

    public void setProducts(ArrayList<MenuItem> products) {
        this.products = products;
    }

    public List<MenuItem> getProducts() {
        return products;
    }

    public Order(int orderID, int clientID, Date data, String day, String clientUsername){
        this.orderID = orderID;
        this.clientID = clientID;
        this.data = data;
        this.day = day;
        this.clientUsername = clientUsername;
    }


    public String listProduct(){
        String sb = "";
        for(MenuItem m: products ){
            if(m instanceof BaseProduct){
                BaseProduct bp = (BaseProduct)m;
                sb = sb + "" + bp.getTitle() + " ";
            } else{
                CompositeProduct cp = (CompositeProduct)m;
                sb = sb + "" + cp.getTitle() +" ";
            }
        }
        return sb;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void addItem(MenuItem me){
        products.add(me);
    }

    @Override
    public int hashCode(){
        int intValue = this.clientID + this.orderID;
        return intValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Order))
            return false;
        if (obj == this)
            return true;
        return this.getOrderID() == ((Order) obj).getOrderID();
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getDay() {
        return day;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
