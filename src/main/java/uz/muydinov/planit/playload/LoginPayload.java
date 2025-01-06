package uz.muydinov.planit.playload;

public class LoginPayload {
    private String email;
    private String password;

    // No-argument constructor
    public LoginPayload() {
    }

    @Override
    public String toString() {
        return "LoginPayload{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginPayload(String email) {
        this.email = email;
    }

    public LoginPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }
}