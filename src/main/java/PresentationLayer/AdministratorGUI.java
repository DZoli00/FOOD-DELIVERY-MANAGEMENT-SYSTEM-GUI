package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AdministratorGUI extends JFrame {

    private JButton importButton = new JButton("Import products");
    private JButton addButton = new JButton("Add product");
    private JButton modifyButton = new JButton("Modify product");
    private JButton deleteButton = new JButton("Delete product");
    private JButton createButton = new JButton("Create menu");
    private JButton addToMenu = new JButton("Add to Menu");
    private JTextField menuItems = new JTextField(50);
    private JComboBox productList = new JComboBox();

    private JTextField jttitle = new JTextField(10);
    private JTextField jtrating = new JTextField(5);
    private JTextField jtcalories = new JTextField(5);
    private JTextField jtprotein = new JTextField(5);
    private JTextField jtfat = new JTextField(5);
    private JTextField jtsodium = new JTextField(5);
    private JTextField jtprice = new JTextField(5);

    private JButton raportButton = new JButton("Generate Raport");
    private JPanel textpanel = new JPanel();

    private JLabel hoursLabel = new JLabel("Time interval:");
    private JTextField h1 = new JTextField(3);
    private JTextField h2 = new JTextField(3);
    private JLabel orderNumber = new JLabel("Nr of orders:");
    private JTextField nrOtxt = new JTextField(3);
    private JLabel clientsOrder = new JLabel("Specified nr and value:");
    private JTextField co1 = new JTextField(5);
    private JTextField co2 = new JTextField(5);
    private JLabel daySpecified = new JLabel("Specified day and nr:");
    private JTextField dS1 = new JTextField(5);
    private JTextField dS2 = new JTextField(5);

    private JPanel finalpanel = new JPanel();

    private DeliveryService dservice ;

    public DeliveryService getDservice() {
        return dservice;
    }

    public void setDservice(DeliveryService dservice) {
        this.dservice = dservice;
    }

    public AdministratorGUI(){
        super("ADMINISTRATOR");
        super.setSize(780,340);
        //super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
        finalpanel.setLayout(null);
        importButton.setBounds(10,10,150,30);
        addButton.setBounds(10,40,150,30);
        modifyButton.setBounds(160,40,150,30);
        deleteButton.setBounds(310,40,150,30);
        createButton.setBounds(460,40,150,30);
        textpanel.add(jttitle);
        textpanel.add(jtrating);
        textpanel.add(jtcalories);
        textpanel.add(jtprotein);
        textpanel.add(jtfat);
        textpanel.add(jtsodium);
        textpanel.add(jtprice);
        textpanel.setBounds(10,70,610,30);
        addToMenu.setBounds(10,100,150,30);
        menuItems.setBounds(160,100,400,30);
        raportButton.setBounds(10,140,150,30);
        hoursLabel.setBounds(10,170,100,30);
        h1.setBounds(100,175,50,20);
        h2.setBounds(160,175,50,20);
        orderNumber.setBounds(10,200,100,30);
        nrOtxt.setBounds(100,205,50,20);
        clientsOrder.setBounds(10,230,150,20);
        co1.setBounds(150,235,50,20);
        co2.setBounds(210,235,50,20);
        daySpecified.setBounds(10,260,120,30);
        dS1.setBounds(130,265,50,20);
        dS2.setBounds(190,265,50,20);
        productList.setBounds(160,10,600,30);

        finalpanel.add(addToMenu);
        finalpanel.add((importButton));
        finalpanel.add(addButton);
        finalpanel.add(modifyButton);
        finalpanel.add(deleteButton);
        finalpanel.add(createButton);
        finalpanel.add(textpanel);
        finalpanel.add(raportButton);
        finalpanel.add(hoursLabel);
        finalpanel.add(h1);
        finalpanel.add(h2);
        finalpanel.add(orderNumber);
        finalpanel.add(nrOtxt);
        finalpanel.add(clientsOrder);
        finalpanel.add(co1);
        finalpanel.add(co2);
        finalpanel.add(daySpecified);
        finalpanel.add(dS1);
        finalpanel.add(productList);
        finalpanel.add(menuItems);

        super.setContentPane(finalpanel);
        super.setVisible(true);
    }

    public JButton getImportButton() {
        return importButton;
    }

    public JComboBox getProductList() {
        return productList;
    }

    public JTextField getJttitle() {
        return jttitle;
    }

    public JTextField getJtrating() {
        return jtrating;
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

    public void setProductList(JComboBox productList) {
        this.productList = productList;
    }

    public void addImportButton(ActionListener a){
        importButton.addActionListener(a);
    }

    public void addAddButton(ActionListener a){
        addButton.addActionListener(a);
    }
    public void addDeleteButton(ActionListener a){
        deleteButton.addActionListener(a);
    }
    public void addModifyButton(ActionListener a){
        modifyButton.addActionListener(a);
    }
    public void addAddToMenuButton(ActionListener a){
        addToMenu.addActionListener(a);
    }
    public void addGenerateMenuButton(ActionListener a){
        createButton.addActionListener(a);
    }

    public void addRaportButton(ActionListener a){
        raportButton.addActionListener(a);
    }
    public JTextField getMenuItems() {
        return menuItems;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getH1() {
        return h1;
    }

    public JTextField getH2() {
        return h2;
    }

    public JTextField getNrOtxt() {
        return nrOtxt;
    }

    public JTextField getCo1() {
        return co1;
    }

    public JTextField getCo2() {
        return co2;
    }

    public JTextField getdS1() {
        return dS1;
    }

    public JTextField getdS2() {
        return dS2;
    }


}
