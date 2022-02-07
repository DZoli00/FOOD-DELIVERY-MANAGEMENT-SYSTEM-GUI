package BusinessLayer;
import DataLayer.Administrator;
import DataLayer.Client;
import DataLayer.Employee;
import DataLayer.User;
import PresentationLayer.EmployeeGUI;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing{
    HashMap<Order, ArrayList<MenuItem>> orderInformation = new HashMap<>();
    Set<MenuItem> menu = new HashSet<>();
    ArrayList<MenuItem> menus;
    ArrayList<MenuItem> allOrdered = new ArrayList<>();
    List<Client> clients= new ArrayList<>();
    private int orderID = 1;
    private int clientID = 1;
    private int adminID = 1;
    private int employeeID = 1;
    private HashMap<String, User> users = new HashMap<>();
    private int billID = 1;
    private FileWriter myWriter;
    private File fileName;

    public static Function<String, BaseProduct> mapToMenu = (line) ->{
        String[] linie = line.split(",");
        BaseProduct prod =  new BaseProduct(linie[0],Float.parseFloat(linie[1]),Float.parseFloat(linie[2]),
                Float.parseFloat(linie[3]),Float.parseFloat(linie[4]),Float.parseFloat(linie[5]),
                Float.parseFloat(linie[6]));
        return prod;
    };
    @Override
    public void importProduct() throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("products.csv"));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        this.menu = br.lines().skip(1).sorted().map(mapToMenu).collect(Collectors.toCollection(HashSet::new));

    }
    @Override
    public void manageProduct(BaseProduct bs, int manageType) throws FileNotFoundException {
            if(manageType == 0){
                addItem(bs);
            }
            MenuItem deletingMN = null;
            if(manageType == 1){
                for(MenuItem mn: menu){
                    if(mn.getTitle().equals(bs.getTitle())){
                        System.out.println("Deleted!");
                        menu.remove(mn);
                        deletingMN = mn;
                        break;
                    }
                }
                menu.remove(deletingMN);
            }
            if(manageType == 2){
                for(MenuItem mn: menu){
                    if(mn.getTitle().equals(bs.getTitle())){
                        System.out.println("Modofied!");
                            if(mn instanceof BaseProduct){
                                ((BaseProduct) mn).setTitle(bs.getTitle());
                                ((BaseProduct) mn).setCalories(bs.getCalories());
                                ((BaseProduct) mn).setFats(bs.getFats());
                                ((BaseProduct) mn).setPrice(bs.getPrice());
                                ((BaseProduct) mn).setProteins(bs.getProteins());
                                ((BaseProduct) mn).setRating(bs.getRating());
                                ((BaseProduct) mn).setSodium(bs.getSodium());
                            }
                    }
                }
            }
    }

    @Override
    public List<Order> generateReportBetweenHours(int h1, int h2) {
        Map<Order,List<MenuItem>> raport = orderInformation.entrySet().stream().
                filter(e -> e.getKey().getData().getHours() >= h1)
                .filter(e -> e.getKey().getData().getHours() <= h2).
                        collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
        List<Order> items = new ArrayList<>(raport.keySet());
            return items;
    }

    @Override
    public List<MenuItem> generateReportOrderedProducts(int nr) {
        List<MenuItem> items = allOrdered.stream().filter(e -> e.getOrdered() >= nr).collect(Collectors.toList());
        List<MenuItem> itemsD = items.stream().distinct().collect(Collectors.toList());
        return itemsD;
    }


    @Override
    public List<Client> generateReportNrAndPrice(int nr, int price) {
        List<Client> cl = clients.stream().filter(cli -> cli.getOrdersNr() >= nr).collect(Collectors.toList());

        List<Client> click = new ArrayList<>();
        for(Client idk:cl){
            Map<Order,ArrayList<MenuItem>> res = orderInformation.entrySet().stream().
                    filter(e->e.getKey().getClientID() == idk.getClientID()).filter(e->e.getKey().getPrice()>=price).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
            if(res != null){
                click.add(idk);
            }
        }
        return click;
    }

    @Override
    public List<MenuItem> generateReportDayAndNr(int day) {
        List<MenuItem> items = new ArrayList<>();
        Map<Order,ArrayList<MenuItem>> maps = orderInformation.entrySet().stream().
                filter(e->e.getKey().getData().getDay() == day).collect(Collectors.
                toMap(e->e.getKey(),e->e.getValue()));
        for(Order ord: maps.keySet()){
            items.addAll(maps.get(ord));
        }
        return items;
    }


    @Override
    public Set<MenuItem> viewProducts() {
        return this.menu;
    }

    @Override
    public void createOrder(Date dataOrder, String dayS, List<BaseProduct> producties, Client user) {
            Order order = new Order(orderID++,clientID++,dataOrder,dayS,user.getUsername());
             int ok =0, price =0;
            for(Client c:clients){
                if(c.getUsername().equals(user.getUsername())){
                    c.setOrdersNr(c.getOrdersNr()+1);
                    ok = 1;
                }
            }
            if(ok == 0){
                user.setOrdersNr(user.getOrdersNr()+1);
                clients.add(user);
            }
            menus = new ArrayList<>();
            for(BaseProduct bp: producties){
                order.addItem(bp);
                menus.add(bp);
                for(MenuItem mi: allOrdered) {
                    BaseProduct bpprot = (BaseProduct) mi;
                    if (bpprot.getTitle().equals(bp.getTitle())) {
                        bpprot.setOrdered(bpprot.getOrdered() + 1);
                        break;
                    }
                }
                allOrdered.add(bp);
                price += bp.getPrice();
            }
            order.setPrice(price);
            orderInformation.put(order,  menus);
            createBill(order);
            addObserver(new EmployeeGUI());
            setChanged();
            notifyObservers(user.getUsername());

    }

    @Override
    public List<MenuItem> searchFood(String t, double rtg, double cal, double pro, double fat, double so , double pr, int sbn ) throws FileNotFoundException {
        List<MenuItem> searchedByClient = new ArrayList<>();
            if(sbn == 1) searchedByClient = menu.stream().distinct().filter(menu -> menu.getTitle().toLowerCase(Locale.ROOT).contains(t))
            .filter(menu -> menu.getCalories() <= cal).filter(menu -> menu.getPrice() <= pr)
                    .filter(menu -> menu.getProteins() <= pro).filter(menu -> menu.getFats() <= fat)
                    .filter(menu -> menu.getRating() <= rtg).filter(menu -> menu.getSodium() <= so).collect(Collectors.toList());
            return searchedByClient;
    }

    @Override
    public void Register(String username, String password, int usertype) {
        if(users.get(username) == null) {
            if (usertype == 1) {
                Administrator admin = new Administrator(username, password, adminID++);
                users.put(username, admin);
            } else {
                if (usertype == 2) {
                    Client client = new Client(username, password, clientID++);
                    client.setOrdersNr(client.getOrdersNr()+1);
                    clients.add(client);
                    users.put(username, client);
                } else {
                    if (usertype == 3) {
                        Employee empl = new Employee(username, password, employeeID++);
                        users.put(username, empl);
                    }
                }
            }
        }
        else{
            JFrame jf1= new JFrame();
            jf1.setSize(200,200);
            JTextField jt1 = new JTextField("User already exists!");
            jf1.setContentPane(jt1);
            jf1.setVisible(true);
        }
    }

    @Override
    public int Login(String username, String password) {
        if(users.get(username) != null) {
            if (users.get(username).getPassword().equals(password)) {
                if (users.get(username) instanceof Administrator) {
                    return 1;

                } else if (users.get(username) instanceof Client) {
                    return 2;

                } else {
                    if(users.get(username) instanceof Employee) {
                        return 3;
                    }
                }
            } else {
                JFrame jf1 = new JFrame("ERROR");
                JLabel messageError = new JLabel("Incorrect password!");
                jf1.setContentPane(messageError);
                jf1.setSize(100,100);
                jf1.setVisible(true);
            }
        } else{
            JFrame jf1 = new JFrame("ERROR");
            JLabel messageError = new JLabel("User does not exist!");
            jf1.setContentPane(messageError);
            jf1.setSize(100,100);
            jf1.setVisible(true);
        }
        return 0;
    }

    public void createBill(Order order){
            System.out.println("----------" +(billID)+"----------");
            System.out.println("Order number:" + order.getOrderID());
            System.out.println("Client number and username:" + order.getClientID() + " " + order.getClientUsername());
            System.out.println("Date and day:" + order.getData() + " " + order.getDay() );
            System.out.println("Menu list:");
            for(MenuItem mi : order.getProducts()) {
                BaseProduct bp = (BaseProduct)mi;
                System.out.println("* " + bp.getTitle());
            }
            System.out.println("Orders price: " + order.getPrice() );
            try {
                fileName = new File("order"+(billID)+".txt");
                myWriter = new FileWriter("bill"+(billID)+".txt");

                myWriter.write("----------" +(billID++)+"----------\n");
                myWriter.write("Order number:" + order.getOrderID()+"\n");
                myWriter.write("Client number and username:" + order.getClientID() + " " + order.getClientUsername()+"\n");
                myWriter.write("Date and day:" + order.getData() + " " + order.getDay() +"\n");
                myWriter.write("Menu list:"+"\n");
                for(MenuItem mi : order.getProducts()) {
                    BaseProduct bp = (BaseProduct)mi;
                    myWriter.write("* " + bp.getTitle()+"\n");
                }
                myWriter.write("Orders price: " + order.getPrice()+"\n" );
                myWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Map<Order, ArrayList<MenuItem>> getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(Map<Order, ArrayList<MenuItem>> orderInformation) {
        this.orderInformation = (HashMap<Order, ArrayList<MenuItem>>) orderInformation;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }

    public void addItem(MenuItem m){
        menu.add(m);
    }
}