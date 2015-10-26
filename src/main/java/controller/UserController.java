package controller;

import domain.Course;
import domain.User;
import repository.CourseDAO;
import repository.RemoteQualifier;
import repository.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 22/10/15.
 */
@Model
public class UserController {

    private final UserDAO userDAO;
    private final CourseDAO courseDAO;
    private User user;

    @Inject
    public UserController(UserDAO userDAO, CourseDAO courseDAO) {
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
    }

    @PostConstruct
    public void init(){
        user = new User();
    }

    public User getUser(){
        return user;
    }

    public void persist(){
        userDAO.persist(user);
    }

    public void delete(User user){
        userDAO.remove(user);
    }

    public void edit(User user){
        System.out.println("Edit(u)" + user.toString());
        userDAO.update(user);
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }

    public void addCourse(Course course, User userin){
        System.out.println(userin.getId() + " --- --- --- -- -- -- - --- Going to add: " + course.getName());

        User user1 = userDAO.findById(userin.getId());
        System.out.println("HER HER HER HER : "+ user1.toString());

        if(course.getUsers() == null){
            course.setUsers(new ArrayList<>());
        }


        course.getUsers().add(user1);
        courseDAO.update(course);


        Course courseToDebug = courseDAO.findById(course.getId());
        System.out.println("DEBUG1234:");
        courseToDebug.getUsers().forEach(u -> System.out.println(u));
    }


}
