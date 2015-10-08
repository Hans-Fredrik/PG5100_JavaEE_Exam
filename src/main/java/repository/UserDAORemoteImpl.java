package repository;

import domain.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@RemoteQualifier
public class UserDAORemoteImpl implements UserDAO {

    private static String PERSISTENCE_UNIT = "Forelesning1";
    public EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;


    public UserDAORemoteImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public UserDAORemoteImpl(String persistenceUnit){
        this.PERSISTENCE_UNIT = persistenceUnit;
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public User updateUser(User user) {
        User updated = entityManager.find(User.class, user.getId());
        updated.setEmail(user.getEmail());
        updated.setPassword(user.getPassword());
        updated.setUserType(user.getUserType());
        return updated;
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
        entityManager.remove(entityManager.find(User.class, user.getId()));
        return entityManager.contains(user);
    }


    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        System.out.println(UserDAORemoteImpl.class.getSimpleName() + " - " + ic.getMethod().getName() + " transaction begin");
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            return ic.proceed();
        } finally {
            System.out.println(UserDAORemoteImpl.class.getSimpleName() + " - " + ic.getMethod().getName() + "transaction commit");
            entityTransaction.commit();
        }
    }



    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
