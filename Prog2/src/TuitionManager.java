import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TuitionManager class that handles exeptions, creates appropriate
 * Student objects, creates a StudentList, and adds / removes
 * Students from the Student List.
 * Can also print the current list and quit the program.
 *
 * @author Krushn Gor, Jake Ippolito
 */
public class TuitionManager {
    protected Scanner stdin;
    private StudentList list;

    //Check the return value: true means added/removed
    // false means not-added/doesn't exist


    /**
     * Runs the Tuition Manager program
     * Reads from a scanner(System.in) commands in order to
     * add, remove, print or quit the program.
     * Bad commands are also dealt with.
     * @throws IllegalArgumentException if the command is not supported.
     */
    protected void run() {
        String ARPQ;

        boolean done = false;
        list = new StudentList();
        stdin = new Scanner(System.in);

        while (!done) {
            ARPQ = stdin.next();
            String command;
            String fname;
            String lname;
            String credit;
            String specificData;

            try {
                switch (ARPQ.charAt(0)) {
                    //Add
                    case 'A':
                        command = stdin.next();

                        fname = stdin.next();
                        rightName(fname);

                        lname = stdin.next();
                        rightName(lname);

                        credit = stdin.next();
                        specificData = stdin.next();
                        add(command, fname, lname, Integer.parseInt(credit), specificData);
                        stdin.nextLine();
                        break;
                    //Remove
                    case 'R':
                        fname = stdin.next();
                        rightName(fname);
                        lname = stdin.next();
                        rightName(lname);
                        remove(fname, lname);
                        stdin.nextLine();
                        break;
                    //Print
                    case 'P':
                        print();
                        stdin.nextLine();
                        break;
                    //Quit
                    case 'Q':
                        print();
                        System.out.println("Program Terminated");
                        done = true;
                        break;
                    default: //deal with bad command here
                        throw new IllegalArgumentException("IllegalArgumentException: Command '" + ARPQ +
                                "' not supported!");

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                stdin.nextLine();

            }

        }
        //write java code before you terminate the program
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
        if (type.charAt(0) != 73 && type.charAt(0) != 79 && type.charAt(0) != 78)
            throw new IllegalArgumentException("IllegalArgumentException: Student type can only be I, O, N. Scroll up for details." + " Input " + type + " not valid.");

        else {
            boolean b = specificData.charAt(0) == 70 || specificData.charAt(0) == 84;
            switch (type) {

                case "I":
                    if (specificData.charAt(0) >= 48 && specificData.charAt(0) < 57) {
                        if (credits < 1)
                            throw new IllegalArgumentException("IllegalArgumentException: Credits must be numerical. Instate Students must take " +
                                    "more than 0 credits! Input " + credits + " not valid.");
                        tempCheck = list.add(new Instate(fname, lname, credits, Integer.parseInt(specificData)));
                        if (tempCheck)
                            System.out.println("Student " + fname + " " + lname + " added successfully");
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
                            System.out.println("Student " + fname + " " + lname + " added successfully");
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
                            System.out.println("Student " + fname + " " + lname + " added successfully");
                    } else
                        throw new IllegalArgumentException("IllegalArgumentException: Error: Only T or F valid. Command " +
                                "'" + specificData + "' not supported!");
                    break;

            }
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
            System.out.println("Student " + fname + " " + lname + " removed successfully");
    }

    /**
     * Prints all the Students along with the relevant information for each.
     * If the list is not empty, calls list.print().
     * list is a StudentList.
     */
    protected void print(){
        //must check if the StudentList has 0 Students.
        if (list.isEmpty()) {
            System.out.println("We have no Students enlisted in database!");
        } else {
            System.out.println("We have the following Students enlisted in database:");
            list.print();
        }
    }

    /**
     * Checks the passed String has any symbols, or numbers
     * @throws IllegalArgumentException if there are any symbols or numbers
     * @param tempName a String[firstName/lastName]
     */
    private void rightName(String tempName) throws IllegalArgumentException {

        if((tempName.charAt(0) <= 64) || (tempName.charAt(0) > 90)){
            throw new IllegalArgumentException("IllegalArgumentException: First/Last name's first alphabet must be capital." +
                    "The name cannot contain any number, or symbols." +
                    " Input '" + tempName + "' not valid.");
        }
        for (int i = 0; i < tempName.length(); ) {
            if ((tempName.charAt(i) > 64 && tempName.charAt(i) <= 90) || (tempName.charAt(i) > 96 && tempName.charAt(i) <= 122)) {
                i++;
            }
            else
                throw new IllegalArgumentException("IllegalArgumentException: The name cannot contain any number, or symbols." +
                        " Input '" + tempName + "' not valid.");
        }
    }

}
