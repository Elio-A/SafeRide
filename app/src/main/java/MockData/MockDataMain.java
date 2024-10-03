package MockData;

public class MockDataMain {
    User user1 = new User("Elio", "Al Alam", 3717406, "ealalam@unb.ca", "Elio");
    User user2 = new User("Albertus", "Koesoama", 3712385, "albertus.university@unb.ca", "albert");

    public User isAUser(int studentNumber){
        if(studentNumber == user1.getStudentNumber()){
            return user1;
        } else if(studentNumber == user2.getStudentNumber()){
            return user2;
        } else {
            return null;
        }
    }
}
