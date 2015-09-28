package repository;

import domain.User;

import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@Alternative
public class UserDAONewRemoteImpl implements UserDaoNew {

    private static final String PERSISTENCE_UNIT = "Forelesning1";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public UserDAONewRemoteImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public boolean createUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();

        return entityManager.contains(user);
    }


    @Override
    public boolean updateUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        User updated = entityManager.find(User.class, user.getId());
        updated.setEmail(user.getEmail());
        updated.setPassword(user.getPassword());
        updated.setUserType(user.getUserType());

        entityTransaction.commit();

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
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.remove(entityManager.find(User.class, user.getId()));
        entityTransaction.commit();

        return !entityManager.contains(user);
    }
}
