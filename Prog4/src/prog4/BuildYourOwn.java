package prog4;

import java.util.ArrayList;

/**
 * Child class of Pizza that will call parent constructor and pass in
 * Style, Size, and Topping of pizza
 * <p>
 * It also calculates the pizza price by calculating the size and toppings
 *  * @author Krushn Gor, Jake Ippolito
 */
public class BuildYourOwn extends Pizza {
    private final int SMALLPIZZA = 5;
    private String size;
    private int toppinsNo;

    /**
     * BuildYourOwn pizza constructor.
     * Calls parent Pizza's constructor and sets size and toppinsNo.
     * @param style    style of the pizza
     * @param size     size of the pizza.
     * @param toppings toppings for the pizza.
     */
    public BuildYourOwn(String style, String size, ArrayList<String> toppings) {
        super(style, size, toppings);
        this.size = size;
        this.toppinsNo = toppings.size();
    }

    /**
     * Calculates the cost of a pizza. Cost is determined by the size and number of toppings.
     * @return the calculated price of the pizza price!
     */
    @Override
    public int pizzaPrice() {
        if (size.equals("Small"))
            return ((SMALLPIZZA) + toppinsNo * 2);
        else if (size.equals("Medium"))
            return ((SMALLPIZZA + 2) + toppinsNo * 2);
        else
            return ((SMALLPIZZA + 4) + toppinsNo * 2);
    }

    /**
     * Override method from Pizza Class
     *
     * @return the super class toString() with price of the calculated pizza
     */
    @Override
    public String toString() {
        return super.toString() + pizzaPrice() + ".00\n";
    }
}
