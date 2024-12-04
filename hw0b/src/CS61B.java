public class CS61B {
    // Variables (part b)
    int capacity;
    static String university = "UC Berkeley";
    String semester;
    int[] students;
    CS61BStudent[] idToStudent;

    // Constructor (parts a, c)
    public CS61B(int cap, int[] ids, String semester) {
        this.capacity = cap;
        this.semester = semester;
        students = new int[cap];
        for (int i=0; i<ids.length; i++) {
            students[i] = ids[i];
        }
    }

    // Methods (part d)
    /* makes every CS61BStudent in this semester of the course watch lecture */

    /* Takes in a student id sid and point value points. Adds points to the grade of
    the CS61BStudent corresponding to sid and returns their new grade
     */

    /* Take in a new university name newUniversity and changes the university for all
    semesters of CS61B to newUniversity
     */


}
