package MockData;

import java.util.Objects;

public class MockDataMain {
    static User user1 = new User("Elio", "Al Alam", "3717406", "ealalam@unb.ca", "Elio");
    static User user2 = new User("Albertus", "Koesoama", "3725253", "albertus.university@unb.ca", "albert");

    public static boolean isAUser(String studentNumber){
        if(Objects.equals(studentNumber, user1.getStudentNumber())){
            return true;
        }
        else if(Objects.equals(studentNumber, user2.getStudentNumber())){
            return true;
        }
        else {
            return false;
        }
    }
}