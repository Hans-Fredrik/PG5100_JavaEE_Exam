package repository;

import domain.User;

import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@Alternative
public class UserDAOLocalImpl implements UserDAO {
    
    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
