package repository;

import domain.User;

import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public interface UserDAO {

    User persist(User user);

    User update(User user);

    User findById(int id);

    List<User> getAllUsers();

    boolean remove(User user);

}
