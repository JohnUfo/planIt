package uz.muydinov.planit.playload;

public class RegisterPayload {
    private String fullname;
    private String email;
    private String password;

    public RegisterPayload() {
    }

    @Override
    public String toString() {
        return "RegisterPayload{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public RegisterPayload(String fullname) {
        this.fullname = fullname;
    }

    public RegisterPayload(String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }
}
