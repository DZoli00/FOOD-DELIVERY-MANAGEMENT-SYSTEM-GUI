package BusinessLayer;

public class BaseProduct implements MenuItem{
    private String title;
    private double rating;
    private double calories;
    private double proteins;
    private double fats;
    private double sodium;
    private double price;
    private int ordered = 1;

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public BaseProduct(String t, double r, double c, double p, double f, double s, double price){
        this.title = t;
        this.rating = r;
        this.calories = c;
        this.proteins = p;
        this.fats = f;
        this.sodium = s;
        this.price = price;
    }

    public BaseProduct(String t){
        this.title = t;
    }
    @Override
    public void compositePrice() {
        this.price = price;
    }

    public  String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getSodium() {
        return sodium;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title +
                ", " + rating +
                ", " + calories +
                ", " + proteins +
                ", " + fats +
                ", " + sodium +
                ", " + price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof BaseProduct))
            return false;
        if (obj == this)
            return true;
        return this.getTitle().equals(((BaseProduct) obj).getTitle());
    }
}
