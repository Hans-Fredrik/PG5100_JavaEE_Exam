package repository;

import domain.User;

import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public interface UserDAO {

    User createUser(User user);

    User updateUser(User user);

    User getUser(int id);

    List<User> getAllUsers();

    boolean deleteUser(User user);

}
