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



}
