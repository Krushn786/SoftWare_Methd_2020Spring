package prog4;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.naming.CannotProceedException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for the main order window.
 * Handles mainly adding to the order and configuring the pizza.
 * @author Krushn Gor, Jake Ippolito
 */
public class BuildController {
    static Orders listOfOrder = new Orders();
    /**
     * All the variables that are associated to the Build.fxml attributes
     */
    @FXML
    AnchorPane tryHard, upPane;
    @FXML
    AnchorPane downPane;
    @FXML
    ComboBox<String> pizzaStyleBox;
    String[] pizzaStyles = {"Build Your Own", "Deluxe", "Hawaiian"};
    ObservableList<String> styleList = FXCollections.observableArrayList(pizzaStyles);
    @FXML
    ComboBox<String> pizzaSizeBox;
    String[] pizzaSizes = {"Medium-12\"", "Small-10\"", "Large-14\""};
    ObservableList<String> sizeList = FXCollections.observableArrayList(pizzaSizes);
    @FXML
    ImageView BuildView, DeluxeView, HView;
    @FXML
    Button showOrder;
    @FXML
    Button addTopping;
    @FXML
    Button remTopping;
    @FXML
    Button clearSelection;
    @FXML
    Button finalAdd;
    @FXML
    TextArea errorBox;
    @FXML
    ListView<String> toppingView;
    ObservableList<String> toppingList = FXCollections.observableArrayList();
    @FXML
    ListView<String> selectedToppingView;
    ObservableList<String> selectedList = FXCollections.observableArrayList();
    /**
     * Below four StringArrays are helping arrays for above two ListView
     */
    //Veg-and-NonVeg Toppings
    String[] meatToppings = {"Pepperoni", "Ham", "Chicken", "Bacon", "Sausage"};
    String[] veggieToppings = {"Pineapple", "Onion", "Green Pepper", "Mushroom", "Spinach"};

    //Default Pizza
    String[] deluxePizza = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
    String[] hawaiianPizza = {"Pineapple", "Ham"};


    /**
     * Set everything to default value
     */
    @FXML
    public void setMainButton() {
        setPizzaStyle();
        setPizzaSize();
        defaultTopping();
        errorBox.setDisable(true);
    }

    /**
     * Will call the method display() in PopOrderWindow class
     * It will pop up a new window!
     * @throws IOException if JavaFX throws an IOException
     */
    @FXML
    public void getMyOrder() throws IOException {

        new OrderWindow().display();
    }

    /**
     * Will use a ObservableList to set value to StyleComboBox
     */
    @FXML
    public void setPizzaStyle() {
        sizeList.clear();
        sizeList.addAll(pizzaStyles);
        pizzaStyleBox.setItems(sizeList);
        pizzaStyleBox.setValue("Build Your Own");
    }

    /**
     * Will use a ObservableList to set value to SizeComboBox
     */
    @FXML
    public void setPizzaSize() {
        styleList.clear();
        styleList.addAll(pizzaSizes);
        pizzaSizeBox.setItems(styleList);
        pizzaSizeBox.setValue("Medium-12\"");
    }

    /**
     * It uses the value from the pizzaStyleBox
     * The get value will be compared and will set the topping value on
     * Selected View, and Topping View accordingly
     */
    @FXML
    public void defaultTopping() {
        String value = pizzaStyleBox.getValue();

        if (value.equals("Deluxe")) {
            //Clear both list, for NO Repeat
            toppingList.clear();
            selectedList.clear();


            toppingList.addAll(veggieToppings);
            toppingList.addAll(meatToppings);
            for (String s : deluxePizza) {
                for (int j = 0; j < toppingList.size(); j++) {
                    if (s.equals(toppingList.get(j))) {
                        toppingList.remove(j);
                    }
                }
            }
            toppingView.setItems(toppingList);
            selectedList.addAll(deluxePizza);
            selectedToppingView.setItems(selectedList);
            DeluxeView.setVisible(true);
            HView.setVisible(false);
            BuildView.setVisible(false);
        } else if (value.equals("Hawaiian")) {
            //Clear both list, for NO Repeat
            toppingList.clear();
            selectedList.clear();


            toppingList.addAll(veggieToppings);
            toppingList.addAll(meatToppings);
            for (String s : hawaiianPizza) {
                for (int j = 0; j < toppingList.size(); j++) {
                    if (s.equals(toppingList.get(j))) {
                        toppingList.remove(j);
                    }
                }
            }
            toppingView.setItems(toppingList);
            selectedList.addAll(hawaiianPizza);
            selectedToppingView.setItems(selectedList);
            DeluxeView.setVisible(false);
            HView.setVisible(true);
            BuildView.setVisible(false);
        } else {
            //Clear both list, for NO Repeat
            toppingList.clear();
            selectedList.clear();

            toppingList.addAll(meatToppings);
            toppingList.addAll(veggieToppings);
            toppingView.setItems(toppingList);
            DeluxeView.setVisible(false);
            HView.setVisible(false);
            BuildView.setVisible(true);
        }


    }

    /**
     * Deluxe, and Hawaiian Pizza are special pizza!
     * Helper method for default error.
     * This method stops user from add or removing toppings when the user chooses this pizza.
     * @throws Exception if the user tries to remove a topping from a static topping pizza.
     */

    public void blockTopping() throws Exception {

        if (pizzaStyleBox.getValue().equals("Deluxe")) {
            //throw new CannotProceedException("CannotProceedException: 'Deluxe Style' has default toppings you cannot add or remove");
            throw new CannotProceedException("CannotProceedException: 'Deluxe Style' has default toppings\nyou cannot add or remove");
        } else if (pizzaStyleBox.getValue().equals("Hawaiian")) {
            //throw new CannotProceedException("CannotProceedException: 'Hawaiian Style' has default toppings you cannot add or remove");
            throw new CannotProceedException("CannotProceedException: 'Hawaiian Style' has default toppings\nyou cannot add or remove");
        }
    }

    /**
     * Transfer the selected topping from toppingView to selectedView
     * Delete the selected topping from toppingView
     */
    @FXML
    public void addTopping() {
        try {
            if (toppingView.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Exception: Must select to add.\nSelect a topping from left to use add button!");
            }
            blockTopping();
            selectedList.add(toppingView.getSelectionModel().getSelectedItem());
            selectedToppingView.setItems(selectedList);
            selectedToppingView.refresh();

            toppingView.getItems().remove(toppingView.getSelectionModel().getSelectedIndex());
            toppingView.getSelectionModel().clearSelection();
            toppingView.refresh();
        } catch (Exception e) {
            printError(e.getMessage());
        }

    }

    /**
     * Transfer the selected topping from selectedView t0 toppingView
     * Delete the selected topping from selectedView
     */
    @FXML
    public void removeTopping() {
        try {
            if (selectedToppingView.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Exception: Must select to remove.\nSelect a topping from right to use remove button!");
            }
            blockTopping();
            toppingList.add(selectedToppingView.getSelectionModel().getSelectedItem());
            toppingView.setItems(toppingList);
            toppingView.refresh();

            selectedToppingView.getItems().remove(selectedToppingView.getSelectionModel().getSelectedIndex());
            selectedToppingView.getSelectionModel().clearSelection();
            selectedToppingView.refresh();
        } catch (Exception e) {
            printError(e.getMessage());
        }

    }

    /**
     * If any Transfer ALL from selectedView to toppingView
     * Delete ALL from selectedView
     * @throws NullPointerException when there is nothing to clear.
     */
    @FXML
    public void setClearSelection() throws NullPointerException {

        try {
            if (!selectedList.isEmpty()) {
                toppingList.addAll(selectedList);
                selectedList.removeAll(selectedList);
                selectedToppingView.setItems(selectedList);
            } else
                throw new NullPointerException("NullPointerException: Cannot clear the list.\nNothing in the list");
        } catch (NullPointerException e) {
            printError(e.getMessage());
        }
    }


    /**
     * Finalize the order.
     * If every expectation is matched add the pizza to the Order object
     * else throw an Exception as needed
     */
    @FXML
    public void setFinalAdd() {

        try {
            if (!selectedList.isEmpty()) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.addAll(selectedList);

                String tempStyle = pizzaStyleBox.getValue();
                String tempSize = pizzaSizeBox.getValue();

                listOfOrder.add(tempStyle, tempSize, tempList);
                printError("Order Added Successfully!");


                defaultTopping();
                pizzaSizeBox.setValue("Medium-12\"");
                pizzaStyleBox.setValue("Build Your Own");
            } else
                throw new NullPointerException("NullPointerException: Must Add at least one toppings\nto proceed with the order");
        } catch (Exception e) {
            printError(e.getMessage());
        }

    }

    public void printError(String msg) {
        errorBox.setText(msg);
    }


}
