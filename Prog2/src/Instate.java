/**
 * InState Student
 * Is a Student with the additional stipulation that they can have funds
 *  applied to their tuition if they are full time.
 * @author Krushn Gor, Jake Ippolito
 */
public class Instate extends Student {
    private int funds; //amount of funds accredited to InState Student.
    private final static int COSTPERCREDIT = 433; //cost of credit for InState Student(s).


    /**
     * Construct an Instate Student.
     * Calls Student constructor, initializes funds accredited.
     * @param fname first name of Student.
     * @param lname last name of Student.
     * @param credit number of credit hours taken by Student.
     * @param funds amount of funds for Instate Student.
     */
    public Instate(String fname, String lname, int credit, int funds) {
        super(fname, lname, credit);
        this.funds = funds;
    }


    /**
     * Calculates the tuition due for the InState Student
     * Takes into account the credits a student takes, the max chargeable credits,
     *  the full and part time fees, and the funds accredited to the Student.
     * @return the tuition due for the Student.
     */
    @Override
    public int tuitionDue() {
        int tempBeforeFunds;
        //If the credits taken exceeds the min for full time credits
        if (super.credit >= super.MINFULLCREDITS)
            //Calculate full time cost, if the max chargeable credits is exceeded multiply by the max
            return (((COSTPERCREDIT * (super.credit >= super.MAXFULLCREDITS ? super.MAXFULLCREDITS : super.credit))
                    + super.FULLTIME)-funds);

        if (super.credit >= super.MINFULLCREDITS) {
            tempBeforeFunds = ((COSTPERCREDIT * (super.credit >= super.MAXFULLCREDITS ? super.MAXFULLCREDITS : super.credit)) + super.FULLTIME);
            int tempTDue = (tempBeforeFunds - funds);
            return (tempTDue < 0 ? 0 : tempTDue);
        } else
            return (COSTPERCREDIT * super.credit) + super.PARTTIME;

    }

    /**
     * Constructs the toString for an InState Student.
     * @return String in format "InState: " + Student toString + " Funds: " + funds.
     */
    @Override
    public String toString() {
        int tempBeforeFunds;
        //for full-time students
        if (super.credit >= super.MINFULLCREDITS) {
            tempBeforeFunds = ((COSTPERCREDIT * (super.credit >= super.MAXFULLCREDITS ? super.MAXFULLCREDITS : super.credit)) + super.FULLTIME);
            int tempTDue = (funds - tempBeforeFunds);

            if (tempTDue > 0)
                //When there are remaining funds
                return "InState: " + super.toString() + " Funds given/remaining -> " + this.funds + "/" + (this.funds - tempBeforeFunds);
            else
                //When all the funds are used
                return "InState: " + super.toString() + " Funds given/remaining -> " + this.funds + "/" + 0;

        }
        //For part-time students
        else
            return "InState: " + super.toString() + " Funds given/remaining -> " + 0 + "/" + 0;
    }

    /**
     * Testbed.main for Instate
     * Intended to test parameters that could break the program.
     * @param args additional arguments unused
     */
    public static void main(String[] args) {

        System.out.println("--Testbed.main for Instate--");

        System.out.println();
        System.out.println("Check if max chargeable credits is applied:");

        Instate test1 = new Instate("Tommy", "Wooders", 15, 1000);
        Instate test2 = new Instate("Jared", "Chang", 8, 1000);
        Instate test3 = new Instate("Bryana", "Martinez", 16, 1000);
        Instate test4 = new Instate("Aaron", "Williams", 14, 1000);
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

        System.out.println();
        System.out.println("Check if part time condition is applied:");

        test1 = new Instate("Tommy", "Wooders", 12, 1000);
        test2 = new Instate("Jared", "Chang", 12, 0);
        test3 = new Instate("Bryana", "Martinez", 9, 1000);
        test4 = new Instate("Aaron", "Williams", 9, 0);
        System.out.println("    1. " + test1.toString()  + " - Tuition Due -> $" + test1.tuitionDue());
        System.out.println("    2. " + test2.toString() + " - Tuition Due -> $" + test2.tuitionDue());
        System.out.println("    3. " + test3.toString() + " - Tuition Due -> $" + test3.tuitionDue());
        System.out.println("    4. " + test4.toString() + " - Tuition Due -> $" + test4.tuitionDue());

    }
}
