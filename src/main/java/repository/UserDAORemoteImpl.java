package repository;

import domain.User;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@RemoteQualifier
@Stateless
public class UserDAORemoteImpl implements UserDAO {

    @PersistenceContext(unitName = "UserDatabase")
    private EntityManager entityManager;
    public EntityManagerFactory entityManagerFactory;

    public UserDAORemoteImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory("UserDatabase");
        entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public User persist(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public User update(User user) {
        User updated = entityManager.find(User.class, user.getId());
        updated.setEmail(user.getEmail());
        updated.setPassword(user.getPassword());
        updated.setUserType(user.getUserType());
        return updated;
    }


    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public List<User> getAllUsers() {
        Query findAll = entityManager.createNamedQuery("findAll");
        List<User> userList = findAll.getResultList();
        return userList;
    }


    @Override
    public boolean remove(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
        return !entityManager.contains(user);
    }


    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        if(ic.getMethod().getName().equals("close")) return ic.proceed();

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
