package PresentationLayer;

import BusinessLayer.DeliveryService;
import DataLayer.Administrator;
import DataLayer.Client;
import DataLayer.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class Controller extends Observable {
   // private AdministratorGUI adminG = new AdministratorGUI();
   // private ClientGUI clientG = new ClientGUI();
   // private EmployeeGUI empG = new EmployeeGUI();
    private MainGUI mainG = new MainGUI();
    private DeliveryService deliverys = new DeliveryService();
    public Controller(){
        mainG.addRegisterButton(new registerButton());
        mainG.addSubmitButton(new submitButton());
    }

    class submitButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String user = mainG.getUserText().getText();
            String password = mainG.getPasswordText().getText();

            int type = deliverys.Login(user,password);
            if(type == 1){
                AdminController admnGUI = new AdminController();
                admnGUI.getAdminG().setDservice(deliverys);
            } else{
                if(type == 2){
                    ClientController clGUI = new ClientController();
                    clGUI.getCleintG().setDservice(deliverys);
                    clGUI.setClient(new Client(user,password));
                } else{
                    if(type == 3){
                        EmployeeGUI empGUY = new EmployeeGUI();
                    }
                }
            }

        }
    }

    class registerButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = mainG.getUserText().getText();
            String password = mainG.getPasswordText().getText();
            if(mainG.getSelectUser().isSelected()){
                deliverys.Register(user,password,1);
            } else
            if(mainG.getSelectUser1().isSelected()){
                deliverys.Register(user,password,2);
            } else {
                deliverys.Register(user,password,3);
            }
        }
    }

}
