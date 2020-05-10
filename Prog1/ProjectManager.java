import java.util.Scanner;

/**
 * Manages the project and accepts commands from the terminal.
 * The run method is intended to be called by a main driver to start the program.
 * Methods other than run are called from inside run with a corresponding command to System.in.
 * @author  Jake Ippolito, Krushn Gor
 */

public class ProjectManager {
    Scanner stdin;
    Team cs213;

    /**
     * Starts the program and contains a loop that runs until the Q command is inputted.
     * Add, remove, print and quit methods are called from corresponding commands.
     */
    public void run() {

        boolean done = false;
        cs213 = new Team();
        stdin = new Scanner(System. in);

        while (!done) {
            String command = stdin.next();
            switch (command.charAt(0)) {
                case 'A':
                    add();
                    break;
                case 'R':
                    remove();
                    break;
                case 'P':
                    print();
                    break;
                case 'Q':
                    print();
                    System.out.println("The team is ready to go!");
                    done = true;
                    break;
                default: //deal with bad command here
                    System.out.println("Command '" + command + "' not supported!");
                    stdin.nextLine();
            }
        }
        //write java code before you terminate the program
    } //run()

    /**
     * Constructs a TeamMember object and adds it to the team.
     * Does not add a TeamMember to the team if the team contains that member or the date is invalid.
     * Additional arguments are unused for the operation of the program
     */
    private void add() {

        //must call the contains() method to check if a given team member is in the team already
        String tempName = stdin.next();
        Date tempDate = new Date(stdin.next());
        if (!cs213.contains(new TeamMember(tempName, tempDate))) {
            if (tempDate.isValid()) {
                cs213.add(new TeamMember(tempName, tempDate));
                System.out.println(tempName + " " + tempDate + " " + " has joined the team.");
            }
            else{
               System.out.println(tempDate + " is not a valid date!");

            }
        }
        else
            {
            System.out.println(tempName + " " + tempDate + " " + " is already in the team.");
            }

        //must check if the date is valid

    }

    /**
     * Removes a TeamMember from the team.
     * If the TeamMember is not in the team the user is notified.
     */
    private void remove() {
        String tempName = stdin.next();
        Date tempDate = new Date(stdin.next());


        if (cs213.contains(new TeamMember(tempName, tempDate))) {
            cs213.remove(new TeamMember(tempName, tempDate));
            System.out.println(tempName + " " + tempDate + " " + " has left the team.");
        } else {
            System.out.println(tempName + " " + tempDate + " " + " is not a team member.");
        }


    }

    /**
     * Prints the entire team.
     * Notifies the user if the team has no members.
     */
    private void print() {
        //must check if the team has 0 members.
        if (cs213.isEmpty()) {
            System.out.println("We have 0 team members!");
        } else {
            System.out.println("We have following team members:");
            cs213.print();
            System.out.println("-- end of the list --");
        }
    }
} //ProjectManager
