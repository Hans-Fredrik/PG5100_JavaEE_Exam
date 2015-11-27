package domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "findByEmail", query = "SELECT u FROM User u WHERE u.email like :mail"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Pattern(regexp = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}$")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = {CascadeType.DETACH})
    private List<Course> courses;

    public User(){

    }

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(int id,String email, String password, UserType userType) {
        this.id = id;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @PreRemove
    private void removeCourseFromUser(){
        for(Course course: this.courses){
            course.getUsers().remove(this);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
