package tuitionManager;
/**
 * Outstate Student
 * Is a Student with the addtional stipulation that they can have a discount
 *  per credit if they are from the tri-state area.
 * @author Krushn Gor, Jake Ippolito
 */

public class Outstate extends Student {
    private boolean triState; //true if Student is from triState, false otherwise
    private final static int COSTPERCREDIT = 756; //Cost per credit of OutstateStudent
    private final static int DISCOUNTPERCREDIT = 200; //Discount for triState students

    /**
     * Construct an Outstate Student.
     * Calls Student constructor, initializes triState boolean.
     * The constructor takes a char from which the boolean
     *  triState is set.
     * @param fname first name of Student.
     * @param lname last name of Student.
     * @param credit number of credit hours taken by Student.
     * @param triState 'T' if Student is from the tri-state
     *                 area, false otherwise.
     */
    public Outstate(String fname, String lname, int credit, char triState) {
        super(fname, lname, credit);

        this.triState = triState == 'T';
    }


    /**
     * Calculates the tuition due for the Outstate Student
     * Takes into account the credits a student takes, the max chargeable credits,
     *  the full and part time fees, and the discount accredited to the Student if
     *  they are from the tri-state area.
     * @return the tuition due for the Student.
     */
    @Override
    public int tuitionDue() {

        if ( super.credit >= super.MINFULLCREDITS){
            return ((COSTPERCREDIT-(triState ? DISCOUNTPERCREDIT:0)) *
                    ((super.credit >= MAXFULLCREDITS) ? MAXFULLCREDITS : super.credit)) + super.FULLTIME;
        }

        else
            return ((COSTPERCREDIT * super.credit) + super.PARTTIME);

    }

    /**
     * Outstate toString that calls the Student toString.
     * Adds additional information on the type and triState status.
     * @return Student toString with Student information.
     */
    @Override
    public String toString() {
        return "OutState: " + super.toString() + "TriState -> " + triState;
    }

    /**
     * Testbed.main for Outstate
     * Intended to test parameters that could break the program.
     * @param args additional arguments unused
     */
    public static void main(String[] args) {

        System.out.println("--Testbed.main for Outstate--");

        System.out.println();
        System.out.println("Check if max chargeable credits is applied:");

        Outstate test1 = new Outstate("Tommy", "Wooders", 15, 'T');
        Outstate test2 = new Outstate("Jared", "Chang", 8, 'T');
        Outstate test3 = new Outstate("Bryana", "Martinez", 16, 'T');
        Outstate test4 = new Outstate("Aaron", "Williams", 14, 'T');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

        System.out.println();
        System.out.println("Check if part time condition is applied:");

        test1 = new Outstate("Tommy", "Wooders", 12, 'T');
        test2 = new Outstate("Jared", "Chang", 14, 'T');
        test3 = new Outstate("Bryana", "Martinez", 9, 'T');
        test4 = new Outstate("Aaron", "Williams", 11, 'T');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

        System.out.println();
        System.out.println("Check if Tri-State condition with discount is applied:");

        test1 = new Outstate("Tommy", "Wooders", 12, 'T');
        test2 = new Outstate("Jared", "Chang", 12, 'F');
        test3 = new Outstate("Bryana", "Martinez", 9, 'T');
        test4 = new Outstate("Aaron", "Williams", 9, 'F');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

    }
}
