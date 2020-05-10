package prog4;

import java.util.ArrayList;

/**
 * Abstract Parent Class that will store.
 * Style, Size, and Topping of pizza.
 * @author Krushn Gor, Jake Ippolito
 */
public abstract class Pizza {
    protected String style;
    protected String size;
    protected ArrayList<String> toppings;

    /**
     * Overloaded Constructor that will assign value to variables style, size, toppings to class!
     *
     * @param style    intakes a String value for a style of pizza that users wants
     * @param size     intakes a String value for the size of pizza the user wants to order
     * @param toppings intakes a list of toppings that user wants on pizza
     */
    public Pizza(String style, String size, ArrayList<String> toppings) {
        this.style = style;
        this.size = size;
        this.toppings = toppings;
    }

    /**
     * Overloaded Constructor that will assign value to variables style, size, toppings to class!
     *
     * @param style intakes a String value for a style of pizza that users wants
     * @param size  intakes a String value for the size of pizza the user wants to order
     */
    public Pizza(String style, String size) {
        this.style = style;
        this.size = size;
    }

    public abstract int pizzaPrice();

    /**
     * Override method from Object Class
     * returns the style of the pizza, size of the pizza, and all the toppings on pizza
     */
    @Override
    public String toString() {

        return "\tStyle: " + style + "\n\t\tSize: " + size + "\n\t\tToppings: " + toppings.toString() + "\n\t\tPizza Prize: $";
    }
}
