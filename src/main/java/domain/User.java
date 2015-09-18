package domain;

import javax.persistence.*;

/**
 * Created by hffb on 17/09/15.
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "findByEmail", query = "SELECT u FROM User u WHERE u.email like :mail")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String userType;

    public User(){

    }


    public User(String email, String password, String userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + userType + '\'' +
                '}';
    }
}
