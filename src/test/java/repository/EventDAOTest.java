package repository;

import domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class EventDAOTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EventDAO eventDAO;


    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("egentrening");
        entityManager = entityManagerFactory.createEntityManager();
        eventDAO = new EventDAO(entityManager);
    }


    @After
    public void tearDown() throws Exception {
        eventDAO.close();
    }

    @Test
    public void testGetEvent() throws Exception {
        Event event = eventDAO.findById(1);
        assertNotNull(event.getCourse());
        assertNotNull(event.getDescription());
        assertNotNull(event.getTitle());
        assertNotNull(event.getStartingTime());
        assertNotNull(event.getEndingTime());
        assertTrue(event.getId() > 0);
    }


    @Test
    public void testPersistEvent() throws Exception {
        Course course = new Course();
        course.setId(1);

        Event event = new Event(EventType.ASSIGNMENT, "Oppgave1", "Intro1", course, new Date(), new Date());

        entityManager.getTransaction().begin();
        eventDAO.persist(event);
        entityManager.getTransaction().commit();

        assertTrue(event.getId() > 0);
    }
}