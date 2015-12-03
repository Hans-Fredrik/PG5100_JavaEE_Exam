package repository;

import domain.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Created by hffb on 03/12/15.
 */
@Stateless
public class EventDAO extends GenericDAOImpl<Event> {

    public EventDAO() {
        super(Event.class);
    }

    public EventDAO(EntityManager entityManager){
        super(entityManager, Event.class);
    }
}

