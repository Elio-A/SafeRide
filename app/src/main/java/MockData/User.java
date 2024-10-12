package MockData;

public class User {
    private String firstName;
    private String lastName;
    private final int studentNumber;
    private String email;
    private String password;

    public User(String firstName, String lastName, int studentNumber, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
