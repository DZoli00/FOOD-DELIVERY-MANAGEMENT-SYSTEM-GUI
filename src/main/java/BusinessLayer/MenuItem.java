package BusinessLayer;

import java.util.Collection;

public interface MenuItem {
    public void compositePrice();

    public String getTitle();
    public double getRating();
    public double getCalories();
    public double getProteins();
    public double getFats();
    public double getSodium();
    public double getPrice();

    public int getOrdered();

    @Override
    public boolean equals(Object obj);
}
