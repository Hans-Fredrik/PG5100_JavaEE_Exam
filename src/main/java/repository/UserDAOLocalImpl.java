package repository;

import domain.User;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@Alternative
public class UserDAOLocalImpl implements UserDao {

    private ArrayList<User> users;

    public UserDAOLocalImpl() {
        users = new ArrayList<>();
        users.add(new User(1,"hans@NITH.no", "hansformers", "Teacher"));
        users.add(new User(2,"per@NITH.no", "heiper", "Student"));
        users.add(new User(3,"Lise@NITH.no", "heiLise", "Student"));
        users.add(new User(4,"Ola@NITH.no", "heiOla", "Student"));
    }

    @Override
    public boolean createUser(User user) {
        return users.add(user);
    }

    @Override
    public boolean updateUser(User user) {
        if(users.contains(user)){
            users.remove(user);
            return users.add(user);
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean deleteUser(User user) {
        return users.remove(user);
    }
}
