package tuitionManager;
/**
 * International Student
 * Is a Student with the additional stipulation that they are not charged per credit and only
 *  charged the university fee and the international student fee if they are an exchange student.
 * @author Krushn Gor, Jake Ippolito
 */

public class International extends Student {
    private boolean exchange; //true if student is exchanged, false otherwise
    private final static int COSTPERCREDIT = 945; //Cost per credit for International Student
    private final static int INTERNATIONALSTUDENTFEE = 350; //International Student Fee

    /**
     * Construct an International Student.
     * Calls Student constructor, initializes exchange boolean.
     * The constructor takes a char from which the boolean
     *  exchange is set.
     * @param fname first name of Student.
     * @param lname last name of Student.
     * @param credit number of credit hours taken by Student.
     * @param exchange 'T' if Student is an exchange student,
     *                 'F' otherwise.
     */
    public International(String fname, String lname, int credit, char exchange) {
        super(fname, lname, credit);

        this.exchange = exchange == 'T';
    }

    /**
     * Calculates the tuition due for the International Student
     * Takes into account the credits a student takes, the max chargeable credits,
     *  the minimum amount of credits to be taken, the full and part time fees,
     *  and the international fees.
     * Exchange students are also taken into account.
     * @return the tuition due for the Student.
     */
    @Override
    public int tuitionDue() {

        //Only Exchange Student
        if(exchange)
            return (super.credit>=super.MINFULLCREDITS ? super.FULLTIME : super.PARTTIME) + INTERNATIONALSTUDENTFEE;

        //Non Exchange fulltime student
        else if ( super.credit >= super.MINFULLCREDITS)
            return ((COSTPERCREDIT * ((super.credit >= MAXFULLCREDITS) ? MAXFULLCREDITS : super.credit))
                    + super.FULLTIME) + INTERNATIONALSTUDENTFEE;

        //Non Exchange partime student
        else
            return ((COSTPERCREDIT * super.credit) + super.PARTTIME) + INTERNATIONALSTUDENTFEE;

    }

    @Override
    public String toString() {
        return "International: " + super.toString() + "Exchange -> " + exchange;
    }

    /**
     * Testbed.main for International
     * Intended to test parameters that could break the program.
     * @param args additional arguments unused
     */
    public static void main(String[] args) {

        System.out.println("--Testbed.main for International--");

        System.out.println();
        System.out.println("Check if max chargeable credits is applied:");

        International test1 = new International("Tommy", "Wooders", 15, 'F');
        International test2 = new International("Jared", "Chang", 8, 'F');
        International test3 = new International("Bryana", "Martinez", 16, 'F');
        International test4 = new International("Aaron", "Williams", 14, 'F');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

        System.out.println();
        System.out.println("Check if part time condition is applied:");

        test1 = new International("Tommy", "Wooders", 12, 'F');
        test2 = new International("Jared", "Chang", 14, 'F');
        test3 = new International("Bryana", "Martinez", 9, 'F');
        test4 = new International("Aaron", "Williams", 11, 'F');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

        System.out.println();
        System.out.println("Check if exchange condition is applied:");

        test1 = new International("Tommy", "Wooders", 12, 'T');
        test2 = new International("Jared", "Chang", 12, 'F');
        test3 = new International("Bryana", "Martinez", 9, 'T');
        test4 = new International("Aaron", "Williams", 9, 'F');
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

    }
}
