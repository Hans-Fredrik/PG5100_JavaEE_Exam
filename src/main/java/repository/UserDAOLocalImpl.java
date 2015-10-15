package repository;

import domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public class UserDAOLocalImpl implements UserDAO {

    private ArrayList<User> users;

    public UserDAOLocalImpl() {
        users = new ArrayList<>();
        users.add(new User(1,"hans@NITH.no", "hansformers", "Teacher"));
        users.add(new User(2,"per@NITH.no", "heiper", "Student"));
        users.add(new User(3,"Lise@NITH.no", "heiLise", "Student"));
        users.add(new User(4,"Ola@NITH.no", "heiOla", "Student"));
    }

    @Override
    public User persist(User user) {
        if(users.add(user)){
            return user;
        }
        return null;
    }

    @Override
    public User update(User user) {
        if(users.contains(user)){
            users.remove(user);
             users.add(user);
            return user;
        }
        return null;
    }

    @Override
    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean remove(User user) {
        return users.remove(user);
    }

}
