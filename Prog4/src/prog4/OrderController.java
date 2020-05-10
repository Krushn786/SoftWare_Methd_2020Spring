package prog4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Helper Controller to the OrderWindow.
 * @author Krushn Gor, Jake Ippolito
 */
public class OrderController {
    @FXML
    Button clearMenu, returnBack;

    @FXML
    Label orderList;

    /**
     * On action it will close OrderWindow
     */
    @FXML
    public void closeWindow() {
        OrderWindow.stage2.close();
    }

    /**
     * On action it will clear all the pizza of the list
     * Will refresh the list that had the orders.
     */
    @FXML
    public void clearYourOrder() {
        try {
            if (BuildController.listOfOrder.getOrderCount() != 0) {
                BuildController.listOfOrder.clearOrder();
                orderList.setText(BuildController.listOfOrder.toString());
            } else
                throw new NullPointerException("NullPointerException: Pizza List is empty.\nCannot clear an empty list");
        } catch (NullPointerException e) {
            orderList.setText(e.getMessage());
        }
    }


    /**
     * Sets the list to the contents of the user's order.
     * If there are no pizzas, set the orderList to "No Orders Yet!"
     * @throws IOException if JavaFX throws an IOException
     */
    public void setOrderList() throws IOException {
        if (BuildController.listOfOrder.getOrderCount() != 0)
            orderList.setText(BuildController.listOfOrder.toString());
        else
            orderList.setText("No Orders Yet!");
    }


}
