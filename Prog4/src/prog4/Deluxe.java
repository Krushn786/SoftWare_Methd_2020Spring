package prog4;

import java.util.ArrayList;

/**
 * Child class of Pizza that will call parent constructor and pass in
 * Style, Size, and Topping of pizza
 * <p>
 * It also calculates the pizza price by calculating the size.
 * Toppings are default! thus the price is calculated accordingly.
 * @author Krushn Gor, Jake Ippolito
 */
public class Deluxe extends Pizza {
    private final int SMALLPIZZA = 14;
    private String size;

    /**
     * Deluxe Pizza Constructor.
     * Calls parent Pizza's constructor and sets size.
     * @param style    style of the pizza
     * @param size     size of the pizza.
     * @param toppings toppings for the pizza.
     */
    public Deluxe(String style, String size, ArrayList<String> toppings) {
        super(style, size, toppings);
        this.size = size;
    }

    /**
     * Calculates the cost of a pizza. Cost is determined by the size of the pizza.
     * returns the calculated price of the pizza price!
     */
    @Override
    public int pizzaPrice() {
        if (size.equals("Small"))
            return SMALLPIZZA;
        else if (size.equals("Medium"))
            return SMALLPIZZA + 2;
        else
            return SMALLPIZZA + 4;
    }

    /**
     * Override method from Pizza Class
     * returns the super class toString() with price of the calculated pizza
     */
    @Override
    public String toString() {
        return super.toString() + pizzaPrice() + ".00\n";
    }
}
