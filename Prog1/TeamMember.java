import java.lang.*;

/**
 * Contains method to store a team-member.
 * Implements a String variable to store name, and a Date variable to store joining date.
 * Overrides toString(), and equals() from object class.
 * @author  Jake Ippolito, Krushn Gor
 */
public class TeamMember {
    private String name;
    private Date startDate;

    /**
     * Overloaded Constructor
     * Store the given data into private variables
     * @param nm to input team-member name
     * @param date to input team-member joining date
     * */
    public TeamMember(String nm, Date date) {
        this.name = nm;
        this.startDate = date;
    }
    /**
     * For accessing the data stored in var : name
     * @return name of the team-member
     */
    public String getName() {

        return this.name;
    }
    /**
     * For accessing the data stored in var : startDate
     * @return date of the joining of team-member
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Overridden method from the Object Class.
     * Will take a object as a parameter, and inside will check
     * if obj is TeamMember than return true;
     * else return false
     * @param obj input a object that will be TeamMember
     *            if not the method will take care of it
     */
    @Override
    public boolean equals(Object obj) {
        //name and startDate must be the same
        if (obj instanceof TeamMember) {
            TeamMember temp = (TeamMember) obj;
            return ((temp.name.equals(this.name)) && (this.startDate.equals(temp.startDate)));

        } else
            return false;
    }

    /**
     * Overridden method from Object Class!
     * Takes no parameter, and upon call
     * @return Name, and Joining Date for Name
     */
    @Override
    public String toString() {
        //name + " " + startDate;
        return this.name + " " + this.startDate.toString() + "\n";
    }


    /**
     * Testbed.main().
     * Used to check if each and every method is working in the class!
     * */
    public static void main(String[] args) {

        System.out.println("This is testbed.main() for TeamMember");

        //Using Constructor and making new object!
        TeamMember obj1 = new TeamMember("Lily", new Date("01/22/2222"));
        TeamMember obj2 = new TeamMember("Lily", new Date("01/22/2222"));
        TeamMember obj3 = new TeamMember("Lily", new Date("01/22/2223"));

        //Checking Getter method in TeamMember
        System.out.println("--This is checking Getter--");
        System.out.println(obj1.getName()+obj1.getStartDate());
        System.out.println(obj2.getName()+obj2.getStartDate());
        System.out.println(obj3.getName()+obj3.getStartDate());

        //Checking equals method in TeamMember
        System.out.println("--This is checking Equals--");
        if (obj2.equals(obj1)) {
            System.out.println("It's equal");
        } else {
            System.out.println("It's not equal");
        }

        if (obj3.equals(obj1)) {
            System.out.println("It's equal");
        } else {
            System.out.println("It's not equal");
        }


        //Checking toString() Method
        System.out.println("--This is checking ToString()--");
        System.out.print(obj1.toString());
        System.out.print(obj2.toString());
        System.out.print(obj3.toString());



    }
}