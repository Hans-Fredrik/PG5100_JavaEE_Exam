package repository;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

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
