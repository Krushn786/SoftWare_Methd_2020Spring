package prog4;

import java.util.ArrayList;

/**
 * Order class that as a ArrayList of Pizza object.
 * Contains method for add and remove order from the list.
 * @author Krushn Gor, Jake Ippolito
 */
public class Orders {
    ArrayList<Pizza> obj = new ArrayList<>();
    private int orderCount = 0;
    private int totalPrice = 0;


    /**
     * Add the pointer to object in a ArrayList
     * Call the constructor of child class of Pizza and adds the value to them
     *
     * @param style    String value. has the style of pizza
     * @param size     String value. has the size of pizza;
     * @param toppings ArrayList. has toppings on pizza
     * @throws Exception if pizza toppings are more than 6 or less than 1
     */
    public void add(String style, String size, ArrayList<String> toppings) throws Exception {
        if (toppings.size() > 6)
            throw new Exception("Exception: Pizza toppings min 1 and max 6");

        if (style.equals("Hawaiian")) {
            obj.add(orderCount, new Hawaiian(style, size, toppings));
        } else if (style.equals("Deluxe")) {
            obj.add(orderCount, new Deluxe(style, size, toppings));
        } else {
            obj.add(orderCount, new BuildYourOwn(style, size, toppings));
        }
        orderCount++;

    }

    /**
     * Clears the list of all the objects from the local ArrayList obj;
     */
    public void clearOrder() {
        obj.clear();
        orderCount = 0;
        totalPrice = 0;
    }

    /**
     * Override method from object class!
     *
     * @return the calculated the total price of order, and arranges all the order.
     */
    @Override
    public String toString() {
        String str = new String();
        for (int i = 0; i < orderCount; i++) {
            str = str.concat(obj.get(i).toString());
            totalPrice += obj.get(i).pizzaPrice();
        }
        return str + "\nTotal: $ " + totalPrice + ".00";
    }

    /**
     * Helper Method.
     *
     * @return the total no. of pizza ordered
     */
    public int getOrderCount() {
        return orderCount;
    }
}
