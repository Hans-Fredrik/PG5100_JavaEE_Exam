package controller;

import domain.User;
import repository.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by hffb on 22/10/15.
 */
@Model
public class UserController {

    private final UserDAO userDAO;
    private User user;
    private int selectedId;

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

    public void delete(User user){
        userDAO.remove(user);
    }

    public String update(int id){
        System.out.println("\n From update -> ID: " + id);
        User userToUpdate = userDAO.findById(id);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUserType(user.getUserType());
        userDAO.update(userToUpdate);
        return  "users";
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }

    public void initUser(){
        this.user = userDAO.findById(selectedId);
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }
}
