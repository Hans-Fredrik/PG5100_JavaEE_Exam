package repository;

import domain.Location;
import domain.User;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by hffb on 15/10/15.
 */
@Stateless
public class LocationDAO extends GenericDAOImpl<Location>{

    public LocationDAO() {
        super(Location.class);
    }

    public LocationDAO(EntityManager entityManager){
        super(entityManager, Location.class);
    }

}
