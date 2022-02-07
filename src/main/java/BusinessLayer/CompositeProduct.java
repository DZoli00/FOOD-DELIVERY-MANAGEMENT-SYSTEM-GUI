package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem{
    private List<MenuItem> finalMenu = new ArrayList<>();
    private String title;
    private double price;
    private double calories;
    private double sodium;
    private double fat;
    private double rating;
    private double protein;
    private int ordered = 1;

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public void addItem(MenuItem item){
        finalMenu.add(item);
    }

    public void printItems(){
        for(MenuItem m:finalMenu){

        }
    }

    @Override
    public void compositePrice() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.price += bp.getPrice();
        }
    }

    public void compositeProtein() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.protein += bp.getProteins();
        }
    }

    public void compositeSodium() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.sodium += bp.getSodium();
        }
    }

    public void compositeRating() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.rating += bp.getRating();
        }
    }

    public void compositeCalories() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.calories += bp.getCalories();
        }
    }

    public void compositeFat() {
        for(MenuItem m: finalMenu){
            BaseProduct bp = (BaseProduct)m;
            this.fat += bp.getFats();
        }
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public double getRating() {
        return 0;
    }

    @Override
    public double getCalories() {
        return 0;
    }

    @Override
    public double getProteins() {
        return 0;
    }

    @Override
    public double getFats() {
        return 0;
    }

    @Override
    public double getSodium() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public void setFinalMenu(List<MenuItem> finalMenu) {
        this.finalMenu = finalMenu;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return title +
                ", " + rating +
                ", " + calories +
                ", " + protein +
                ", " + fat +
                ", " + sodium +
                ", " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CompositeProduct))
            return false;
        if (obj == this)
            return true;
        return this.getTitle().equals(((CompositeProduct) obj).getTitle());
    }
}
