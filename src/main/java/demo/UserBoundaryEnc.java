package demo;

public class UserBoundaryEnc {

    private String email;
    private Name name;
    private String birthdate;
    private String roles;

    public UserBoundaryEnc() {

    }

    public UserBoundaryEnc(String email, Name name, String birthdate,
            String roles) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Name getName() {
        return this.name;
    }

    public void setName(Name name) {
        this.name = name;
    }

}
