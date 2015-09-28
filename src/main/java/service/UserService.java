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
    public UserDAO userDAO;


    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean createUser(User user){
        return userDAO.createUser(user);
    }

    public boolean deleteUserByID(int id){
        if(id < 0) return false;

        User user = userDAO.getUser(id);

        if(user == null){
            return false;
        }

        return userDAO.deleteUser(user);
    }

    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }

    public User getUserByID(int id){
        return userDAO.getUser(id);
    }

}