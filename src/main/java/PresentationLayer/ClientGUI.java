package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import DataLayer.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.chrono.JapaneseDate;

public class ClientGUI extends JFrame {

    private JButton viewButton = new JButton("View products");
    private JButton searchButton = new JButton("Search");
    private JButton createButton = new JButton("Create order");
    private JButton addButton = new JButton("Add product");
    private JComboBox viewProducts = new JComboBox();
    private JComboBox searchedProducts = new JComboBox();

    private JLabel pricelabel = new JLabel("Price: ");

    private JTextField jttitle = new JTextField(10);
    private JTextField jtrating = new JTextField(5);
    private JTextField jtcalories = new JTextField(5);
    private JTextField jtprotein = new JTextField(5);
    private JTextField jtfat = new JTextField(5);
    private JTextField jtsodium = new JTextField(5);
    private JTextField jtprice = new JTextField(5);
    private JTextField dateText = new JTextField("date");
    private JTextField dayText = new JTextField("day");

    private JTextField selectedProducts = new JTextField(50);
    private JPanel finalpanel = new JPanel();
    private JPanel textpanel = new JPanel();

    private DeliveryService dservice = new DeliveryService();

    public DeliveryService getDservice() {
        return dservice;
    }

    public void setDservice(DeliveryService dservice) {
        this.dservice = dservice;
    }

    public ClientGUI(){
        super("CLIENT");
        super.setSize(700,400);
        super.setVisible(true);
        finalpanel.setLayout(null);
        viewButton.setBounds(10,10,150,30);
        viewProducts.setBounds(10,50,600,30);
        searchButton.setBounds(10,90,150,30);
        searchedProducts.setBounds(170,90,600,30);
        addButton.setBounds(10,170,150,30);
        createButton.setBounds(10,210,150,30);
        pricelabel.setBounds(220,210,150,30);
        textpanel.add(jttitle);
        textpanel.add(jtrating);
        textpanel.add(jtcalories);
        textpanel.add(jtprotein);
        textpanel.add(jtfat);
        textpanel.add(jtsodium);
        textpanel.add(jtprice);
        textpanel.setBounds(10,130,610,30);
        selectedProducts.setBounds(10,250,400,30);
        dateText.setBounds(10,290,100,30);
        dayText.setBounds(110,290,100,30);

        finalpanel.add(viewButton);
        finalpanel.add(viewProducts);
        finalpanel.add(searchButton);
        finalpanel.add(addButton);
        finalpanel.add(createButton);
        finalpanel.add(textpanel);
        finalpanel.add(searchedProducts);
        finalpanel.add(selectedProducts);
        finalpanel.add(pricelabel);
        finalpanel.add(dateText);
        finalpanel.add(dayText);
        super.setContentPane(finalpanel);
        super.setVisible(true);
    }

    public JComboBox getViewProducts() {
        return viewProducts;
    }

    public void addViewButton(ActionListener a){
        viewButton.addActionListener(a);
    }

    public void addSearchButton(ActionListener a){
        searchButton.addActionListener(a);
    }

    public void addAddProductButton(ActionListener a){
        addButton.addActionListener(a);
    }

    public void addCreateButton(ActionListener a){
        createButton.addActionListener(a);
    }

    public JTextField getJttitle() {
        return jttitle;
    }

    public JTextField getJtrating() {
        return jtrating;
    }

    public JComboBox getSearchedProducts() {
        return searchedProducts;
    }

    public JTextField getJtcalories() {
        return jtcalories;
    }

    public JTextField getJtprotein() {
        return jtprotein;
    }

    public JTextField getJtfat() {
        return jtfat;
    }

    public JTextField getJtsodium() {
        return jtsodium;
    }

    public JTextField getJtprice() {
        return jtprice;
    }

    public JPanel getFinalpanel() {
        return finalpanel;
    }

    public JTextField getSelectedProducts() {
        return selectedProducts;
    }

    public JLabel getPricelabel() {
        return pricelabel;
    }

    public JTextField getDayText() {
        return dayText;
    }

    public JTextField getDateText() {
        return dateText;
    }
}
