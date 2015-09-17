package repository;

import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
public class UserDAORemoteImpl implements UserDAO{

    private static final String PERSISTENCE_UNIT = "Forelesning1";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public UserDAORemoteImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

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
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        Query findAll = entityManager.createNamedQuery("findAll");
        List<User> userList = findAll.getResultList();
        return userList;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
