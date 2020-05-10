package prog4;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * A class to test BuildYourOwn class.
 * @author Krushn Gor, Jake Ippolito
 */
public class BuildYourOwnTest {

    ArrayList<String> toppings = new ArrayList<String>();
    BuildYourOwn SMpizza;
    BuildYourOwn MDpizza;
    BuildYourOwn LGpizza;

    /**
     * Tests BuildYourOwn pizzaPrice().
     * Starts with 1 topping and builds to 6 toppings for Small, Medium, and Large pies.
     * In total 18 conditions are tested.
     * The condition for no toppings or more than 6 toppings are handled when adding the toppings to the arraylist and
     *      do not need testing here as it has been tested in the JavaFX GUI.
     */
    @Test
    public void pizzaPrice() {
        for(int i = 1; i <= 6; i++) {
            toppings.add("SampleTopping");
            SMpizza = new BuildYourOwn("BuildYourOwn", "Small", toppings);
            MDpizza = new BuildYourOwn("BuildYourOwn", "Medium", toppings);
            LGpizza = new BuildYourOwn("BuildYourOwn", "Large", toppings);
            assert (SMpizza.pizzaPrice() == 5 + toppings.size() * 2);
            assert (MDpizza.pizzaPrice() == 7 + toppings.size() * 2);
            assert (LGpizza.pizzaPrice() == 9 + toppings.size() * 2);
        }
    }
}