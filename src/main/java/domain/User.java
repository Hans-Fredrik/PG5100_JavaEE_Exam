package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
    private int id;
    private String email;
    private String password;
    private String user_type;

    public User(){

    }


    public User(int id, String email, String password, String user_type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
