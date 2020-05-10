package prog3;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tuitionManager.*;

import javafx.fxml.FXML;

import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;

/**
 * Controller class that handles exceptions, creates appropriate
 * Student objects, creates a StudentList
 * adds / removes Students from the Student List.
 * Contains javafx.scene.*; variable that will help access the FXML file and manipulate data!
 * Contains method that use to manipulate the attributes in FXML file.
 * Can also print the current list of Student

 * @author Krushn Gor, Jake Ippolito
*/

public class Controller {
    StudentList list = new StudentList();
    @FXML
    private TextArea output;
    @FXML
    private TextField firstName, lastName, credits, funding;
    @FXML
    private RadioButton isInstate, isOutstate, isInternational;
    @FXML
    private CheckBox hasFunding, isTristate, isExchange;
    @FXML
    private ToggleGroup type;
    @FXML
    private Button submitAdd, submitRemove;
    @FXML
    private Pane startPane;
    @FXML
    private TextArea liveError;

    /**
     * Hides Introduction Pane!
     * Will set Add button clicked as default value!
     */
    @FXML
    public void startTheList(){
        startPane.setVisible(false);
        addClicked();
    }

    /**
     * Set OutState and International RadioButton not selected
     * Set funding TextBox disable
     * Set Funding CheckBox enable
     * Set TriState and Exchange CheckBox disable
     */
    @FXML
    public void isInstateClicked() {
        hasFunding.setDisable(false);
        funding.setDisable(true);


        isTristate.setDisable(true);
        isTristate.setSelected(false);
        isExchange.setDisable(true);
        isExchange.setSelected(false);
    }

    /**
     * Set InState and International RadioButton not selected
     * Set funding TextBox disable
     * Set TriState CheckBox enable
     * Set InState and Exchange CheckBox disable
     */
    @FXML
    public void isOutstateClicked() {
        hasFunding.setDisable(true);
        hasFunding.setSelected(false);
        funding.setDisable(true);
        funding.setText("");

        isTristate.setDisable(false);

        isExchange.setDisable(true);
        isExchange.setSelected(false);
    }

    /**
     * Set InState and OutState RadioButton not selected
     * Set funding TextBox disable
     * Set Exchange CheckBox enable
     * Set InState and Tristate disable
     */
    @FXML
    public void isInternationalClicked() {
        hasFunding.setDisable(true);
        hasFunding.setSelected(false);
        funding.setDisable(true);
        funding.setText("");
        isTristate.setDisable(true);
        isTristate.setSelected(false);

        isExchange.setDisable(false);
    }

    /**
     * Call reset(), and visible() to reset the default view of GUI
     * Set FirstName, LastName, Credit TextField enable
     * Set InState, OutState, International RadioButton enable
     * Set Funding, TriState, Exchange CheckBox disable
     * Set funding TextBox disable
     */
    @FXML
    public void addClicked() {
        reset();
        visiable();
        firstName.setVisible(true);
        lastName.setVisible(true);
        credits.setVisible(true);
        isInstate.setVisible(true);
        isTristate.setVisible(true);
        isExchange.setVisible(true);

        isOutstate.setVisible(true);
        isInternational.setVisible(true);

        hasFunding.setVisible(true);
        funding.setVisible(true);
        credits.setVisible(true);

        submitAdd.setDisable(false);
        submitAdd.setVisible(true);

        submitRemove.setVisible(false);

    }

    /**
     * Call reset() to reset the default view of GUI
     * Check if FirstName or LastName all character are alphabetical
     * Check if Credit and Funding is all numerical
     * Add the student with the specific info if everything is successful
     * @throws NullPointerException is the fields are empty (WhiteSpace excluded)
     */
    @FXML
    public void submitAddClicked() {

        try {
            if(firstName.getText().equals("")||lastName.getText().equals("") || credits.getText().equals("")){
                throw new NullPointerException("NullPointerException: Missing first or last name! Or missing credits");
            }
            rightName(firstName.getText());
            rightName(lastName.getText());
            checkCredit(credits.getText());

            if (type.getSelectedToggle() == isInstate) {

                add("I", firstName.getText(), lastName.getText(), Integer.parseInt(credits.getText()), hasFunding.isSelected() ? funding.getText() : "0");
            } else if (type.getSelectedToggle() == isOutstate) {
                add("O", firstName.getText(), lastName.getText(), Integer.parseInt(credits.getText()), isTristate.isSelected() ? "T" : "F");
            } else if (type.getSelectedToggle() == isInternational) {
                add("N", firstName.getText(), lastName.getText(), Integer.parseInt(credits.getText()), isExchange.isSelected() ? "T" : "F");
            }
            reset();
        } catch (Exception e) {
            output.setText(e.getMessage() + "\n");
        }
    }

    /**
     * If Funding CheckBox is selected will enable funding TextBox
     * else disable funding TextBox
     */
    @FXML
    public void fundingClicked(){
        if(!hasFunding.isSelected())
            funding.setDisable(true);
        else
        funding.setDisable(false);
    }

    /**
     * Call reset(), and visible() to reset the default view of GUI
     * Set FirstName, LastName, Credit TextField enable
     * Credit TextField invisible
     * Set InState, OutState, International RadioButton invisible
     * Set Funding, TriState, Exchange CheckBox invisible
     * Set funding TextBox invisible
     */
    @FXML
    public void removeClicked() {
        submitAdd.setVisible(false);
        visiable();
        firstName.setVisible(true);
        lastName.setVisible(true);
        submitRemove.setDisable(false);
        submitRemove.setVisible(true);

        reset();
    }

    /**
     * Call reset() to reset the default view of GUI
     * Check if FirstName or LastName all character are alphabetical
     * @throws NullPointerException is the fields are empty (WhiteSpace excluded)
     * Removes person if everything command goes successful!
     */
    @FXML
    public void submitRemoveClicked() {
        try {
            if(firstName.getText().equals("")||lastName.getText().equals("")){
                throw new NullPointerException("NullPointerException: Missing first or last name!");
            }
            remove(firstName.getText(), lastName.getText());
            reset();
        } catch (Exception e) {
            output.setText(e.getMessage() + "\n");
        }
    }

    /**
     * Set everything to invisible
     * Only the printing box available
     */
    @FXML
    public void printClicked() {
        try{
        visiable();
        reset();
        submitAdd.setVisible(false);
        submitRemove.setVisible(false);
        print();
        }
        catch (Exception e) {
            output.setText(e.getMessage() + "\n");
        }
    }

    /**
     * Will set all the TextBox to empty string
     * Will set all the CheckBox and Radiobutton unchecked
     */
    private void reset() {
        firstName.setText("");
        lastName.setText("");
        credits.setText("");
        funding.setText("");
        credits.setText("");

        hasFunding.setDisable(true);
        isTristate.setDisable(true);
        isExchange.setDisable(true);

        hasFunding.setSelected(false);
        funding.setText("");


        isOutstate.setSelected(false);
        isInternational.setSelected(false);

        isInstate.setSelected(false);
        isTristate.setSelected(false);
        isExchange.setSelected(false);
    }

    /**
     * Will set all the TextBox to invisible
     * Will set all the CheckBox and Radiobutton invisible
     */
    private void visiable(){
        isInstate.setVisible(false);
        isTristate.setVisible(false);
        isExchange.setVisible(false);

        isOutstate.setVisible(false);
        isInternational.setVisible(false);

        hasFunding.setVisible(false);
        funding.setVisible(false);
        credits.setVisible(false);

        firstName.setVisible(false);
        lastName.setVisible(false);


    }


    /**
     * Adds a student to the StudentList.
     * Will not add a Student if the arguments do not match parameters for that particular Student.
     *
     * @param type         the type of Student.
     * @param fname        first name.
     * @param lname        last name.
     * @param credits      number of credit hours.
     * @param specificData type specific data.
     * @throws IllegalArgumentException       when the argument is not of expected type or invalid value.
     * @throws InstanceAlreadyExistsException when the Student currently exists.
     */
    protected void add(String type, String fname, String lname, int credits, String specificData)
            throws Exception {

        boolean tempCheck = true;
            boolean b = specificData.charAt(0) == 70 || specificData.charAt(0) == 84;
            switch (type) {

                case "I":
                    if (specificData.charAt(0) >= 48 && specificData.charAt(0) < 57) {
                        if (credits < 1)
                            throw new IllegalArgumentException("IllegalArgumentException: Credits must be numerical. Instate Students must take " +
                                    "more than 0 credits! Input " + credits + " not valid.");
                        tempCheck = list.add(new Instate(fname, lname, credits, Integer.parseInt(specificData)));
                        if (tempCheck)
                            output.setText("Student " + fname + " " + lname + " added successfully");
                    } else
                        throw new IllegalArgumentException("Error: Funds are numerical. Non numerical command '" +
                                specificData + "' not supported");

                    break;
                case "O":
                    if (b) {
                        if (credits < 1)
                            throw new IllegalArgumentException("IllegalArgumentException: Credits must be numerical. Outstate Students must take " +
                                    "more than 0 credits! Input " + credits + " not valid.");
                        tempCheck = list.add(new Outstate(fname, lname, credits, specificData.charAt(0)));

                        if (tempCheck)
                            output.setText("Student " + fname + " " + lname + " added successfully");
                    } else
                        throw new IllegalArgumentException("IllegalArgumentException: Only T or F valid. Command '" + specificData +
                                "' not supported!");


                    break;
                case "N":
                    if (b) {
                        if (credits < 9)
                            throw new IllegalArgumentException("IllegalArgumentException: Credits must be numerical. International Students must " +
                                    "take equal or more than 9 credits! Input " + credits + " not valid.");

                        tempCheck = list.add(new International(fname, lname, credits, specificData.charAt(0)));

                        if (tempCheck)
                            output.setText("Student " + fname + " " + lname + " added successfully");
                    } else
                        throw new IllegalArgumentException("IllegalArgumentException: Error: Only T or F valid. Command " +
                                "'" + specificData + "' not supported!");
                    break;

            }

        if (!tempCheck) {
            throw new InstanceAlreadyExistsException("InstanceAlreadyExistsException: student - " + fname + " " +
                    lname + " - already exist in database!");
        }

    }

    /**
     * Removes a Student from StudentList.
     * Will not remove a Student not in the StudentList.
     * @param fname first name of Student.
     * @param lname last name of Student.
     * @throws NoSuchElementException when Student does not exist in StudentList.
     */
    protected void remove(String fname, String lname) throws NoSuchElementException {

        boolean tempCheck;
        tempCheck = list.remove(new Instate(fname, lname, 0, 0));

        if (!tempCheck) {
            throw new NoSuchElementException("NoSuchElementException: student - " + fname + " " + lname + " - doesn't exist in database.");
        } else
            output.setText("Student " + fname + " " + lname + " removed successfully");
    }

    /**
     * Prints all the Students along with the relevant information for each.
     * If the list is not empty, calls list.print().
     * list is a StudentList.
     */
    protected void print(){
        //must check if the StudentList has 0 Students.
        if (list.isEmpty()) {
            output.setText("We have no Students enlisted in database!\n");
        } else {
            output.setText("We have the following Students enlisted in database:");
            output.setText(list.toString());
        }
    }

    @FXML
    public void checkName(){
        try {
            if (!(firstName.getText() == null)) {
                rightName(firstName.getText());
            }
            if (!(lastName.getText() == null)) {
                rightName(lastName.getText());
            }
        }
        catch (Exception e){
                liveError.setText(e.getMessage() + "\n");
            }
    }
    /**
     * Checks the passed String has any symbols, or numbers
     * @throws IllegalArgumentException if there are any symbols or numbers
     * @param tempName a String[firstName/lastName]
     */

    protected void rightName(String tempName) throws IllegalArgumentException {

        if((tempName.charAt(0) <= 64) || (tempName.charAt(0) > 90)){
            throw new IllegalArgumentException("IllegalArgumentException: First/Last name's first alphabet must be capital." +
                    "The name cannot contain any number, or symbols." +
                    "\nInput '" + tempName + "' not valid.");
        }
        else{
            output.setText("");
        }
        for (int i = 0; i < tempName.length(); ) {
            if ((tempName.charAt(i) > 64 && tempName.charAt(i) <= 90) || (tempName.charAt(i) > 96 && tempName.charAt(i) <= 122)) {
                output.setText("");
                i++;
            }
            else
                throw new IllegalArgumentException("IllegalArgumentException: The name cannot contain any number, or symbols." +
                        "\nInput '" + tempName + "' not valid.");
        }
    }
    /**
     * Checks the passed String has any symbols, or alphabet
     * @throws IllegalArgumentException if there are any symbols or alphabet
     * @param tempCredit a String[firstName/lastName]
     */
    protected void checkCredit(String tempCredit) {
        for (int i = 0; i < tempCredit.length(); ) {
            if ((tempCredit.charAt(i) > 47 && tempCredit.charAt(i) <= 57))
                i++;
            else
                throw new IllegalArgumentException("IllegalArgumentException: Credits must be numerical. And must be more than 0");
        }
    }

}
