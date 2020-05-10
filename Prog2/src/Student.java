/**
 * Student class that implements the Comparable interface.
 * Contains methods for creating and comparing a Student.
 * Also contains a toString for intended to be seen by the user when called from TuitionManager.
 *  method for Student(s).
 * @author Krushn Gor, Jake Ippolito
 */
public abstract class Student implements Comparable {
    private String fname; //First name of Student
    private String lname; //Last name of Student
    protected int credit; //Amount of credit hours for Student

    protected final int FULLTIME = 1441; //University fee for full time
    protected final int PARTTIME = 846; //University fee for part time
    protected final int  MINFULLCREDITS = 12; //Min credits for full time
    protected final int MAXFULLCREDITS = 15; //Max chargeable credits

    /**
     * Student constructor that initializes a Student.
     * @param fname String of first name.
     * @param lname String of last name
     * @param credit integer of number of credits taken.
     */
    public Student(String fname, String lname, int credit) {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }

    /**
     * Compares one Object to another
     * @param o an Object
     * @return 0 if o equals this Student, 1 if this fname and lname comes before o,
     *  -1 if this fname and lname comes after o, and -2 if o is not a Student.
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Student) {

            Student tempO= (Student)o;
            int tempCompareValue = ((this.fname+this.lname).compareToIgnoreCase((tempO.fname+tempO.lname)));
            if (tempCompareValue == 0)
                return 0;
            else if(tempCompareValue > 0)
                return 1;
            else
                return -1;

        }
        else
            //Not an instance
            return -2;
    }

    /**
     * Method to get information on Student instance variables.
     * @return String with fname, lname, and credits
     */
    @Override
    public String toString() {
        return "Name -> "+ this.fname + " " + this.lname + " - Credits ->  " + this.credit + " - ";
    }


    /**
     * Method to be implemented that returns the tuition due to the Student.
     * @return the tuition due for the Student.
     */
    public abstract int tuitionDue();

    ;
    // return 0 if fname and lname are equal, -1 if this < obj, 1 if this > obj
// Hint: use the .equals and compareToIgnoreCase methods of the String class to compare fname
// and lname; names are not case-sensitive public int compareTo(Object obj)
//return a string with fname, lname and credit hours; subclasses will be using this method. public String toString() {...}
//compute the tuition due; concrete implementation is required in the subclasses. public abstract int tuitionDue() {...} }
}