package BusinessLayer;

import DataLayer.Client;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IDeliveryServiceProcessing {

    public void importProduct() throws FileNotFoundException;
    public void manageProduct(BaseProduct bp, int manageType) throws FileNotFoundException;
    public List<Order> generateReportBetweenHours(int h1, int h2);
    public List<MenuItem> generateReportOrderedProducts(int nr);
    public List<Client> generateReportNrAndPrice(int nr, int price);
    public List<MenuItem> generateReportDayAndNr(int day);

    public Set<MenuItem> viewProducts();
    public void createOrder(Date dataOrder, String dayS, List<BaseProduct> producties, Client user);
    public List<MenuItem> searchFood(String t, double rtg, double cal, double pro, double fat, double so , double pr, int sbn ) throws FileNotFoundException;

    public void Register(String username, String password, int usertype);
    public int Login(String username, String password);
}
