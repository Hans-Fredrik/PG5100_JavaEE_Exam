package service;

import domain.User;
import repository.GenericDAO;
import repository.RemoteQualifier;
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
        return userDAO.getAll();
    }

    public User createUser(User user){
        return userDAO.persist(user);
    }

    public boolean deleteUserByID(int id){
        if(id < 0) return false;

        User user = userDAO.findById(id);

        if(user == null){
            return false;
        }

        return userDAO.remove(user);
    }

    public User updateUser(User user){
        return userDAO.update(user);
    }

    public User getUserByID(int id){
        return userDAO.findById(id);
    }

}