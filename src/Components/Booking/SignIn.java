package Components.Booking;

public class SignIn {

    private String name;
    private String password;

    public SignIn() {

    }

    public SignIn(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
