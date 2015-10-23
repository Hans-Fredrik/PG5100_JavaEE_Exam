package controller;

import domain.Course;
import domain.User;
import repository.RemoteQualifier;
import repository.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by hffb on 22/10/15.
 */
@Model
public class UserController {

    private final UserDAO userDAO;
    private User user;

    @Inject
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    public List<User> getAll(){
        System.out.println("getAll()");
        System.out.println(userDAO.getAll());
        return userDAO.getAll();
    }

}
