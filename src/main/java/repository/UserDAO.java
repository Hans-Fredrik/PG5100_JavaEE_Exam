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
@Stateless
public class UserDAO extends GenericDAOImpl<User>{

    public UserDAO() {
        super(User.class);
    }

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        if(ic.getMethod().getName().equals("close")) return ic.proceed();

        System.out.println(UserDAO.class.getSimpleName() + " - " + ic.getMethod().getName() + " transaction begin");
        EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
        entityTransaction.begin();

        try {
            return ic.proceed();
        } finally {
            System.out.println(UserDAO.class.getSimpleName() + " - " + ic.getMethod().getName() + "transaction commit");
            entityTransaction.commit();
        }
    }

}
