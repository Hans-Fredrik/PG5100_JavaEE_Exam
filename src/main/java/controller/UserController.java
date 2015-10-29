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
import javax.persistence.PreRemove;
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


        if(course.getUsers() == null){
            course.setUsers(new ArrayList<>());
        }

        course.getUsers().add(userin);
        courseDAO.update(course);
    }
}
