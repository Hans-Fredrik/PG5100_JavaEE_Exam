package repository;

import domain.Course;
import domain.Location;
import domain.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.enterprise.inject.Instance;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseDAOTest {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CourseDAO courseDAO;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("egentrening");
        entityManager = entityManagerFactory.createEntityManager();
        courseDAO = new CourseDAO(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        courseDAO.close();
    }

    @Test
    public void testPersist() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User("test@test.no", "HeiTest92", "Tester"));
        userList.add(new User("test2@test.no", "HeiTest29", "Tester"));
        Course course = courseDAO.persist(new Course("PG5100", userList, null));

        assertNotNull(course);
        assertNotNull(course.getName());
        assertTrue(course.getId() > 0);
        assertEquals(course.getUsers().size(), 2);

        course.getUsers().forEach(user -> {
            assertTrue(user.getId() > 0);
        });
    }

    @Test
    public void testUpdate() throws Exception {
        addCourseData();
        Course course = courseDAO.findById(1);
        course.setName("Unit-testing");

        Course courseAfterUpdate = courseDAO.update(course);
        assertEquals(courseAfterUpdate.getName(), course.getName());
    }

    @Test
    public void testFindById() throws Exception {
        addCourseData();
        Course course = courseDAO.findById(1);
        assertNotNull(course);
    }

    @Test
    public void testGetAll() throws Exception {
        addCourseData();
        List<Course> courseList = courseDAO.getAll();
        assertNotNull(courseList);
        assertTrue(courseList.size() > 0);

        Course course = courseDAO.findById(1);
        course.getUsers().forEach(u -> System.out.println(u));
    }

    @Test
    public void testRemove() throws Exception {
        addCourseData();
        Course course = courseDAO.findById(1);
        assertTrue(courseDAO.remove(course));
        assertNull(courseDAO.findById(1));
    }


    private void addCourseData(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("test@test.no", "HeiTest92", "Tester"));
        userList.add(new User("test2@test.no", "HeiTest29", "Tester"));
        Course course = courseDAO.persist(new Course("PG5100", userList, null));
    }

    @Before
    public void begin() {
        System.out.println("Begin...");
        courseDAO.getEntityManager().getTransaction().begin();
    }

    @After
    public void commit(){
        System.out.println("Commit...");
        courseDAO.getEntityManager().getTransaction().commit();
    }

}