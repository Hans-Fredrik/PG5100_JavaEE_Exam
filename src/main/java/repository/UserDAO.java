package repository;

import domain.User;

import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public interface UserDAO {

//  Changes this to return a usre object instead-!
    boolean createUser(User user);

    boolean updateUser(User user);

    User getUser(int id);

    List<User> getAllUsers();

    boolean deleteUser(User user);



}
