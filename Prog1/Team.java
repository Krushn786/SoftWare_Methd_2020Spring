/**
 * Contains method to store a Team.
 * Uses a TeamMember variable to create team.
 * @author Jake Ippolito, Krushn Gor
 */
public class Team {
    private final int NOT_FOUND = -1;
    private final int GROW_SIZE = 4; //initial and grow size
    private TeamMember[] team;
    private int numMembers;

    /**
     * Creates a new array of TeamMember array!
     */
    public Team() {
        //this is the default constructor
        numMembers = 0;
        team = new TeamMember[GROW_SIZE];
    }

    /**
     * It takes a TeamMember as a parameter.
     * It will return a negative number if the list is empty, or the TeamMember not found
     * else will return the index number of the TeamMember's if found
     * @param m inputs a TeamMember Object
     */
    private int find(TeamMember m) {
        if (isEmpty()) {
            //Team empty
            return -2;
        } else {
            for (int i = 0; i < numMembers; ) {
                if (team[i].equals(m)) {
                    //Team Member Found
                    return i;
                } else
                    i++;
            }
            //No Team Member with that name and/or startDate
            return -1;
        }

    }
    /**
     * Whenever the Team is full and need's to add more TeamMember it will be called by add().
     * It will grow() the array by GROW_SIZE.
     */
    private void grow() {
        TeamMember[] tempTMHolder = team;

        team = new TeamMember[tempTMHolder.length + GROW_SIZE];

        if (tempTMHolder.length >= 0) System.arraycopy(tempTMHolder, 0, team, 0, tempTMHolder.length);

    }

    /**
     * Return's true if the array has a TeamMember
     * else return false
     * @return true if there are no team-members in team[]
     */
    public boolean isEmpty() {
        return numMembers == 0;
    }

    /**
     * will add a team-member to team[] if the name is date works out
     * @param m input a TeamMember object
     */
    public void add(TeamMember m) {
        if (numMembers == team.length) {
            grow();
        }
        team[numMembers] = new TeamMember(m.getName(), m.getStartDate());
        numMembers++;
    }

    /**
     * It will call find() to see if the given parameter of TeamMember exist
     * If the find() returns the (always positive)index no.
     * then object at index will be replaced with the last obj
     *  @param m input a TeamMember object
     * @return true if the team-member was removed successfully
     */
    public boolean remove(TeamMember m) {
        int temp = find(m);

        if (temp >= 0) {
            if (temp != numMembers) {
                team[temp] = team[numMembers-1];
            }
            team[numMembers-1] = null;

            numMembers--;
            return true;
        } else
            return false;
    }

    /**
     * Will call find() and if find returns positive no. it will return true
     * else return false
     *  @param m input a TeamMember object
     * @return true if team-member found
     */
    public boolean contains(TeamMember m) {
        return find(m) >= 0;
    }

    /**
     It will call the toString() method of TeamMember
     Will print the whole team and date the joined the team
     */
    public void print() {
        if (!isEmpty()) {
            for (int i = 0; i < numMembers; i++) {
                if (team[i] != null)
                    System.out.print(team[i].toString());
            }
        }
    }
}
