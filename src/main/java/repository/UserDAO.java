package repository;

import domain.User;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@RemoteQualifier
@Stateful
public class UserDAO implements GenericDAO<User> {

    @PersistenceContext(unitName = "egentrening")
    private EntityManager entityManager;

    public UserDAO(){
        entityManager =  Persistence.createEntityManagerFactory("egentrening").createEntityManager();
    }

    @Override
    public User persist(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }


    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public List<User> getAll() {
        Query findAll = entityManager.createNamedQuery("findAll");
        List<User> userList = findAll.getResultList();
        return userList;
    }

    @Override
    public boolean remove(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
        return !entityManager.contains(user);
    }


    /*
    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        if(ic.getMethod().getName().equals("close")) return ic.proceed();

        System.out.println(UserDAO.class.getSimpleName() + " - " + ic.getMethod().getName() + " transaction begin");
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            return ic.proceed();
        } finally {
            System.out.println(UserDAO.class.getSimpleName() + " - " + ic.getMethod().getName() + "transaction commit");
            entityTransaction.commit();
        }
    } */

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close(){
        entityManager.close();
    }
}
