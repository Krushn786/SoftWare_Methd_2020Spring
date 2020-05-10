/**
 * Contains methods to create an array of Students.
 * @author Krushn Gor, Jake Ippolito
 */
public class StudentList {
    private final int GROW_SIZE = 4; //initial and grow size
    private Student[] currentStudent;
    private int numStudents;

    /**
     * Default constructor that initializes numStudents and currentStudent.
     * currentStudent is an array of Student(s) initialized with size GROW_SIZE.
     * numStudents is initialized with 0 and indicates the number of Student(s) in currentStudent.
     */
    public StudentList() {
        //this is the default constructor
        numStudents = 0;
        currentStudent = new Student[GROW_SIZE];
    }

    /**
     * Method to find a Student in a StudentList and return its index in currentStudent.
     * @param s the Student object to be found.
     * @return the index of Student if found, -1 if not found or list empty.
     */
    private int findStudent(Student s) {

        if(!isEmpty()) {
            for (int i = 0; i < numStudents; i++) {
                if (currentStudent[i].compareTo(s) == 0)
                    //Team Member Found
                    return i;
            }
            //No Team Member with that name and/or startDate
        }
        return -1;


    }

    /**
     * Grows currentStudent by GROW_SIZE.
     * Creates a new array with length currentStudent.length + GROW_SIZE.
     * Copies the contents of the old array into the new one.
     */
    private void grow() {
        Student[] tempTMHolder = currentStudent;

        currentStudent = new Student[tempTMHolder.length + GROW_SIZE];

        if (tempTMHolder.length >= 0) System.arraycopy(tempTMHolder, 0, currentStudent, 0, tempTMHolder.length);

    }

    /**
     * Checks if StudentList is empty
     * @return true if the StudentList is empty, false otherwise.
     */
    protected boolean isEmpty() {
        return numStudents == 0;
    }


    /**
     * Adds a Student object to a StudentList.
     * Checks if the Student is already in the list.
     * If Student is found, the Student is not added.
     * @param s Student to be added.
     * @return true if s was added, false otherwise.
     */
    protected boolean add(Student s) {

        int temp = findStudent(s);

        if(temp>=0){
            return false;
        }

        else {

            if (numStudents == currentStudent.length) {
                grow();
            }
            currentStudent[numStudents] = s;
            numStudents++;
            return true;
        }
    }

    /**
     * Removes a Student from a StudentList.
     * Finds Student index, does not remove Student if not found.
     * If there are [x] student in list, and a student is removed from [0] to [x-1] than
     * [xth] position will replace student removed, and [xth position will be assigned null]
     * @param s Student to be removed.
     * @return true if Student was removed, false otherwise.
     */
    protected boolean remove(Student s) {
        int temp = findStudent(s);

        if(isEmpty()){
            throw new NullPointerException("NullPointerException: The list is empty first add to remove!");
        }
        if (temp >= 0) {
            if (temp != numStudents) {
                currentStudent[temp] = currentStudent[numStudents-1];
            }
            currentStudent[numStudents-1] = null;

            numStudents--;
            return true;
        } else
            return false;
    }

    /**
     * Prints the contents of a StudentList.
     * Prints in the format of Student toString + " tuition due -> $" + Student tuitionDue
     */
    protected void print() {
        if (!isEmpty()) {
            for (int i = 0; i < numStudents; i++) {
                if (currentStudent[i] != null)
                    System.out.println(currentStudent[i].toString() + " - tuition due: $"
                            + currentStudent[i].tuitionDue()+".00");
            }
        }
    }



}
