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
import java.util.List;

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

    @Test
    public void testGetAllEvents() throws Exception {
        List<Event> eventList = eventDAO.getAll();
        assertNotNull(eventList);
        assertTrue(eventList.size() > 1);
    }

    @Test
    public void testUpdateEventAllFields() throws Exception {
        Event event = eventDAO.findById(1);

        Event oldValues = new Event();
        oldValues.setTitle(event.getTitle());
        oldValues.setDescription(event.getDescription());
        oldValues.setEventType(event.getEventType());
        oldValues.setStartingTime(event.getStartingTime());
        oldValues.setEndingTime(event.getEndingTime());
        oldValues.setCourse(event.getCourse());

        event.setTitle("New Title");
        event.setDescription("New Desc");
        event.setEventType(EventType.ASSIGNMENT);
        event.setStartingTime(new Date());
        event.setEndingTime(new Date());
        Course course = new Course();
        course.setId(2);
        event.setCourse(course);

        eventDAO.update(event);

        Event newValues = eventDAO.findById(1);
        assertNotEquals(newValues.getTitle(), oldValues.getTitle());
        assertNotEquals(newValues.getDescription(), oldValues.getDescription());
        assertNotEquals(newValues.getEventType(), oldValues.getEventType());
        assertNotEquals(newValues.getStartingTime(), oldValues.getStartingTime());
        assertNotEquals(newValues.getEndingTime(), oldValues.getEndingTime());
        assertNotEquals(newValues.getCourse(), oldValues.getCourse());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        Event event = eventDAO.findById(1);
        assertNotNull(event);

        entityManager.getTransaction().begin();
        eventDAO.remove(event);
        entityManager.getTransaction().commit();

        assertNull(eventDAO.findById(1));
    }
}