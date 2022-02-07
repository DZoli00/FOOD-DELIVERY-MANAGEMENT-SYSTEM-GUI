package PresentationLayer;

import BusinessLayer.Order;
import DataLayer.Client;
import DataLayer.Employee;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame implements Observer {
    private JLabel orders = new JLabel();
    private JPanel finalpanel = new JPanel();
    public EmployeeGUI(){
        super("EMPLOYEE");
        super.setSize(350,240);
        super.setVisible(true);
    //    orders.setBounds(10,10,300,300);
       // orders.setEditable(false);
        finalpanel.add(orders);
        orders.setSize(100,200);
        super.setContentPane(finalpanel);
        super.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        orders.setText("New order is placed by client: " + arg);
    }

}
