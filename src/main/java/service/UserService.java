package service;

import domain.User;
import repository.UserDAO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public class UserService {

    @Inject
    private UserDAO userDAO;


    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean createUser(User user){
        return userDAO.createUser(user);
    }

    public boolean deleteUserByID(int id){
        User user = userDAO.getUser(id);
        return userDAO.deleteUser(user);
    }

    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }

}