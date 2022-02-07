package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import DataLayer.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AdminController{
    AdministratorGUI adminG = new AdministratorGUI();
    List<BaseProduct> producties = new ArrayList<BaseProduct>();

    public AdminController(){
        adminG.addImportButton(new importButton());
        adminG.addAddButton(new AddButton());
        adminG.addDeleteButton(new DeleteButton());
        adminG.addModifyButton(new ModifyButton());
        adminG.addAddToMenuButton(new AddToMenuButton());
        adminG.addGenerateMenuButton(new CreateMenuButton());
        adminG.addRaportButton(new GenerateReportButton());
    }

    public AdministratorGUI getAdminG() {
        return adminG;
    }

    class importButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            adminG.getProductList().removeAllItems();
            try {
                adminG.getDservice().importProduct();
                adminG.getProductList().addItem("Title  rating  calories  proteins  fats  sodium  price:");
                for(MenuItem bp: adminG.getDservice().getMenu()){
                    BaseProduct bpm = (BaseProduct)bp;
                    //System.out.println(bpm.getTitle());
                    adminG.getProductList().addItem(bpm.toString());
                }
                adminG.getProductList().setVisible(true);

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    class AddButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            BaseProduct bp1 = new BaseProduct(adminG.getJttitle().getText(), Double.parseDouble(adminG.getJtrating().getText()),Double.parseDouble(adminG.getJtcalories().getText()),Double.parseDouble(adminG.getJtprotein().getText()),Double.parseDouble(adminG.getJtfat().getText()),Double.parseDouble(adminG.getJtsodium().getText()),Double.parseDouble(adminG.getJtprice().getText()));
            adminG.getProductList().addItem(bp1.toString());
            try {
                adminG.getDservice().manageProduct(bp1,0);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            adminG.getProductList().setVisible(true);
        }
    }

    class DeleteButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) adminG.getProductList().getSelectedItem();
            System.out.println(s);
            String[] linie =  s.split(", ");
            BaseProduct deletedProduct = new BaseProduct(linie[0],Float.parseFloat(linie[1]),Float.parseFloat(linie[2]),
                    Float.parseFloat(linie[3]),Float.parseFloat(linie[4]),Float.parseFloat(linie[5]),
                    Float.parseFloat(linie[6]));
            try {
                adminG.getDservice().manageProduct(deletedProduct,1);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            adminG.getProductList().removeItem(adminG.getProductList().getSelectedItem());
        }
    }

    class ModifyButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) adminG.getProductList().getSelectedItem();
            System.out.println(s);
            String[] linie =  s.split(", ");
            BaseProduct modifiedProduct = new BaseProduct(linie[0],Float.parseFloat(linie[1]),Float.parseFloat(linie[2]),
                    Float.parseFloat(linie[3]),Float.parseFloat(linie[4]),Float.parseFloat(linie[5]),
                    Float.parseFloat(linie[6]));
            if(!adminG.getJttitle().getText().equals("")){
                modifiedProduct.setTitle(adminG.getJttitle().getText());
            }
            if(!adminG.getJtrating().getText().equals("")){
                modifiedProduct.setRating(Double.parseDouble(adminG.getJtrating().getText()));
            }
            if(!adminG.getJtcalories().getText().equals("")){
                modifiedProduct.setCalories(Double.parseDouble(adminG.getJtcalories().getText()));
            }
            if(!adminG.getJtprotein().getText().equals("")){
                modifiedProduct.setProteins(Double.parseDouble(adminG.getJtprotein().getText()));
            }
            if(!adminG.getJtfat().getText().equals("")){
                modifiedProduct.setFats(Double.parseDouble(adminG.getJtfat().getText()));
            }
            if(!adminG.getJtsodium().getText().equals("")){
                modifiedProduct.setSodium(Double.parseDouble(adminG.getJtsodium().getText()));
            }
            if(!adminG.getJtprice().getText().equals("")){
                modifiedProduct.setPrice(Double.parseDouble(adminG.getJtprice().getText()));
            }
            try {
                adminG.getDservice().manageProduct(modifiedProduct,2);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            adminG.getProductList().removeAllItems();
            adminG.getProductList().addItem("Title  rating  calories  proteins  fats  sodium  price:");
            for(MenuItem bp: adminG.getDservice().getMenu()){
                BaseProduct bpm = (BaseProduct)bp;
                //System.out.println(bpm.getTitle());
                adminG.getProductList().addItem(bpm.toString());
            }
            adminG.getProductList().setVisible(true);

        }
    }

    class AddToMenuButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) adminG.getProductList().getSelectedItem();
            System.out.println(s);
            String[] linie =  s.split(", ");
            BaseProduct product = new BaseProduct(linie[0],Float.parseFloat(linie[1]),Float.parseFloat(linie[2]),
                    Float.parseFloat(linie[3]),Float.parseFloat(linie[4]),Float.parseFloat(linie[5]),
                    Float.parseFloat(linie[6]));
           producties.add(product);
           adminG.getMenuItems().setText(adminG.getMenuItems().getText()+ ", " + product.getTitle());
        }
    }

    class CreateMenuButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct cp1 = new CompositeProduct();
            for(BaseProduct bp: producties){
                cp1.addItem(bp);
                cp1.setTitle(cp1.getTitle() + " " + bp.getTitle());
            }
            if(!adminG.getJttitle().getText().equals("")){
                cp1.setTitle(adminG.getJttitle().getText());
            }
            else{

            }
            cp1.compositePrice();
            cp1.compositeCalories();
            cp1.compositeFat();
            cp1.compositeProtein();
            cp1.compositeRating();
            cp1.compositeSodium();
            adminG.getDservice().addItem(cp1);
            adminG.getProductList().addItem(cp1.toString());
            adminG.getProductList().setVisible(true);
        }
    }

    class GenerateReportButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!adminG.getH1().getText().equals("") && !adminG.getH2().getText().equals("")){
                int h1 = Integer.parseInt(adminG.getH1().getText());
                int h2 = Integer.parseInt(adminG.getH2().getText());
                List<Order> raport1 =  adminG.getDservice().generateReportBetweenHours(h1,h2);
                int i = 0;
                Object[][] rap = new Object[raport1.size()][6];

                for(Order o:raport1){
                    rap[i][0] = o.getOrderID();
                    rap[i][1] = o.getClientID();
                    rap[i][2] = o.getData();
                    rap[i][3] = o.getDay();
                    rap[i][4] = o.getClientUsername();
                    rap[i][5] = o.getPrice();
                    i++;
                }
                String[] names = {"OrderID", "ClientID", "data", "day", "username", "price"};
                JFrame jf1 = new JFrame();
                jf1.setSize(400,400);
                JTable jt1 = new JTable(rap,names);

                JPanel jfinal = new JPanel();
                jfinal.add(jt1);
                jf1.setContentPane(jfinal);
                jf1.setVisible(true);
            } else{
               if(!adminG.getNrOtxt().getText().equals("")){
                   int nr = Integer.parseInt(adminG.getNrOtxt().getText());
                   List<MenuItem> raport1 =  adminG.getDservice().generateReportOrderedProducts(nr);
                   int i = 0;
                   Object[][] rap = new Object[raport1.size()][7];

                   for(MenuItem o:raport1){
                       rap[i][0] = o.getTitle();
                       rap[i][1] = o.getCalories();
                       rap[i][2] = o.getRating();
                       rap[i][3] = o.getSodium();
                       rap[i][4] = o.getProteins();
                       rap[i][5] = o.getFats();
                       rap[i][5] = o.getPrice();
                       i++;
                   }
                   String[] names = {"Title","Calories","Raring","Sodium","Proteins","Fats","Price"};
                   JFrame jf1 = new JFrame();
                   jf1.setSize(400,400);
                   JTable jt1 = new JTable(rap,names);

                   JPanel jfinal = new JPanel();
                   jfinal.add(jt1);
                   jf1.setContentPane(jfinal);
                   jf1.setVisible(true);
               } else{
                   if(!adminG.getdS1().getText().equals("")){
                       int nr = Integer.parseInt(adminG.getdS1().getText());
                       List<MenuItem> raport1 =  adminG.getDservice().generateReportDayAndNr(nr);
                       int i = 0;
                       Object[][] rap = new Object[raport1.size()][7];

                       for(MenuItem o:raport1){
                           rap[i][0] = o.getTitle();
                           rap[i][1] = o.getCalories();
                           rap[i][2] = o.getRating();
                           rap[i][3] = o.getSodium();
                           rap[i][4] = o.getProteins();
                           rap[i][5] = o.getFats();
                           rap[i][5] = o.getPrice();
                           i++;
                       }
                       String[] names = {"Title","Calories","Raring","Sodium","Proteins","Fats","Price"};
                       JFrame jf1 = new JFrame();
                       jf1.setSize(400,400);
                       JTable jt1 = new JTable(rap,names);

                       JPanel jfinal = new JPanel();
                       jfinal.add(jt1);
                       jf1.setContentPane(jfinal);
                       jf1.setVisible(true);
                   }else{
                       if(!adminG.getCo1().getText().equals("") && !adminG.getCo2().getText().equals("")){
                           int nr1 = Integer.parseInt(adminG.getCo1().getText());
                           int nr2 = Integer.parseInt(adminG.getCo1().getText());
                           List<Client> raport1 =  adminG.getDservice().generateReportNrAndPrice(nr1,nr2);
                           int i = 0;
                           Object[][] rap = new Object[raport1.size()][6];

                           for(Client o:raport1){
                               rap[i][0] = o.getUsername();
                               rap[i][1] = o.getClientID();
                               i++;
                           }
                           String[] names = {"Username", "ClientID"};
                           JFrame jf1 = new JFrame();
                           jf1.setSize(400,400);
                           JTable jt1 = new JTable(rap,names);

                           JPanel jfinal = new JPanel();
                           jfinal.add(jt1);
                           jf1.setContentPane(jfinal);
                           jf1.setVisible(true);
                       }
                   }
               }
            }
        }
    }
}
