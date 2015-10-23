package repository;

import domain.User;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

/**
 * Created by hffb on 17/09/15.
 */
@Stateless
public class UserDAO extends GenericDAOImpl<User>{

    public UserDAO() {
        super(User.class);
    }


    public UserDAO(EntityManager entityManager){
        super(entityManager, User.class);
    }
}
