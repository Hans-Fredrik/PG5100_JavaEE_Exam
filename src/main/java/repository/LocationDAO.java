package repository;

import domain.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

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
