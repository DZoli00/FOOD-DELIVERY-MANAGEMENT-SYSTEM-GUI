package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import DataLayer.Client;
import DataLayer.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientController extends Observable{
    ClientGUI cleintG = new ClientGUI();
    Client client = new Client();
    List<BaseProduct> producties = new ArrayList<BaseProduct>();
    private double priceOrder=0;
    public ClientController(){
        cleintG.addViewButton(new ViewButton());
        cleintG.addSearchButton(new SearchButton());
        cleintG.addAddProductButton(new AddToMenuButton());
        cleintG.addCreateButton(new CreateOrderButton());
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientGUI getCleintG() {
        return cleintG;
    }

    class ViewButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cleintG.getViewProducts().removeAllItems();
             Set<MenuItem> foods = cleintG.getDservice().viewProducts();
            cleintG.getViewProducts().addItem("Title  rating  calories  proteins  fats  sodium  price:");
            for(MenuItem bp: foods){
                BaseProduct bp1 = (BaseProduct) bp;
                cleintG.getViewProducts().addItem(bp1.toString());
            }
            cleintG.getViewProducts().setVisible(true);
        }
    }

    class SearchButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = cleintG.getJttitle().getText();
            double rating = 10000,calories = 10000,protein= 10000,fat= 10000,sodium=10000,price=10000;
            if(!cleintG.getJtrating().getText().equals("")) {
                rating = Double.parseDouble(cleintG.getJtrating().getText());
            }
            if(!cleintG.getJtcalories().getText().equals("")) {
                 calories = Double.parseDouble(cleintG.getJtcalories().getText());
            }
            if(!cleintG.getJtprotein().getText().equals("")) {
                 protein = Double.parseDouble(cleintG.getJtprotein().getText());
            }
            if(!cleintG.getJtfat().getText().equals("")) {
                fat = Double.parseDouble(cleintG.getJtfat().getText());
            }
            if(!cleintG.getJtsodium().getText().equals("")) {
                 sodium = Double.parseDouble(cleintG.getJtsodium().getText());
            }
            if(!cleintG.getJtprice().getText().equals("")) {
                 price = Double.parseDouble(cleintG.getJtprice().getText());
            }
            int searchByName;
            if(title.equals("")){
                 searchByName = 0;
            } else
            {
                searchByName = 1;
            }
            List<MenuItem> searches = null;
            try {
                searches = cleintG.getDservice().searchFood(title,rating,calories,protein,fat,sodium,price,searchByName);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            cleintG.getSearchedProducts().removeAllItems();
            cleintG.getSearchedProducts().addItem("Title  rating  calories  proteins  fats  sodium  price:");
            for(MenuItem mn: searches){
                BaseProduct bp = (BaseProduct) mn;
                cleintG.getSearchedProducts().addItem(bp.toString());
            }
            cleintG.getFinalpanel().add(cleintG.getSearchedProducts());
            cleintG.getSearchedProducts().setVisible(true);
        }
    }

    class AddToMenuButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) cleintG.getSearchedProducts().getSelectedItem();
            System.out.println(s);
            String[] linie =  s.split(", ");
            BaseProduct product = new BaseProduct(linie[0],Float.parseFloat(linie[1]),Float.parseFloat(linie[2]),
                    Float.parseFloat(linie[3]),Float.parseFloat(linie[4]),Float.parseFloat(linie[5]),
                    Float.parseFloat(linie[6]));
            producties.add(product);
            priceOrder += product.getPrice();
            cleintG.getSelectedProducts().setText(cleintG.getSelectedProducts().getText()+ ", " + product.getTitle());
            cleintG.getPricelabel().setText("Price: "+ priceOrder);
        }
    }

    class CreateOrderButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String dataS = cleintG.getDateText().getText();
            SimpleDateFormat dataFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date dataOrder = null;
            try {
                dataOrder = dataFormatter.parse(dataS);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            String dayS = new String();
            if(dataOrder.getDay() == 0) dayS = "Sunday";
            if(dataOrder.getDay() == 1) dayS = "Monday";
            if(dataOrder.getDay() == 2) dayS = "Tuesday";
            if(dataOrder.getDay() == 3) dayS = "Wednesday";
            if(dataOrder.getDay() == 4) dayS = "Thursday";
            if(dataOrder.getDay() == 5) dayS = "Friday";
            if(dataOrder.getDay() == 6) dayS = "Saturday";

           // Order order = new Order(cleintG.getDservice().getOrderID(),cleintG.getDservice().getClientID(),dataOrder,dayS)
            cleintG.getDservice().createOrder(dataOrder,dayS,producties,client);

            producties = new ArrayList<>();
            cleintG.getDateText().setText("date");
            cleintG.getDayText().setText("day");
            cleintG.getSelectedProducts().setText("");

        }
    }

}
