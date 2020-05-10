/**
 * Prog2 class intended to run the Program 2 Assignment.
 * @author Krushn Gor, Jake Ippolito
 */
public class Prog2
{
    /**
     * Main driver that gives details on what commands to use.
     * Calls run in TuitionManager.
     * @param args additional arguments unused.
     */
    public static void main(String [] args)
    {
        System.out.println("\nWelcome to R University\n");
        System.out.println("Command are as follows:" +
                            "\nAdding student:                     v     - A command - " +
                               "FORMAT: <command> <student-type> <fname> <lname> <credit> <specific-data>" +

                            "\nRemoving student:                        - R command - " +
                               "FORMAT: <command> <fname> <lname>" +

                            "\nPrint the list of current student:       - P command - " +
                               "FORMAT: <command>" +

                            "\nQuit the program with final Print:       - Q command - " +
                               "FORMAT: <command>\n");

        System.out.println("Student type , and there specific data:" +
                            "\nInstate-type:       I      Specific-data: governmental funds(only numerical)" +
                            "\nOutstate-type:      O      Specific-data: TriState student(only T[true] OR F[false])" +
                            "\nInternational-type: N      Specific-data: Exchange student(only T[true] OR F[false])\n");
        System.out.println("NOTE: If the FORMAT is not followed than you will receive and error.");
        System.out.println("Let's enter new students:\n");

        new TuitionManager().run();

    }
}
