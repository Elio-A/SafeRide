package MockData;

public class MockDataMain {
    static User user1 = new User("Elio", "Al Alam", "3717406", "ealalam@unb.ca", "Elio");
    static User user2 = new User("Albertus", "Koesoama", "3712385", "albertus.university@unb.ca", "albert");

    public static boolean isAUser(String studentNumber){
        if(studentNumber == user1.getStudentNumber()){
            return true;
        }
        else if(studentNumber == user2.getStudentNumber()){
            return true;
        }
        else {
            return false;
        }
    }
}