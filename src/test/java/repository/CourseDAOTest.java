package repository;

import domain.Course;
import domain.User;
import domain.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
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
        userList.add(new User("test@test.no", "HeiTest92", UserType.STUDENT));
        userList.add(new User("test2@test.no", "HeiTest29", UserType.TEACHER));

        courseDAO.getEntityManager().getTransaction().begin();
        Course course = courseDAO.persist(new Course("PG5100", userList, null));
        courseDAO.getEntityManager().getTransaction().commit();

        assertNotNull(course);
        assertNotNull(course.getName());
        assertTrue(course.getId() > 0);
        assertEquals(course.getUsers().size(), 2);

        course.getUsers().forEach(user -> {
            assertTrue(user.getId() > 0);
        });
    }

    @Test
    public void testAddUserToPersistedCourse() throws Exception {
        courseDAO.getEntityManager().getTransaction().begin();
        Course course = courseDAO.persist(new Course("PG5100", new ArrayList<User>(), null));
        courseDAO.getEntityManager().getTransaction().commit();

        Course course1 = courseDAO.findById(course.getId());
        courseDAO.getEntityManager().getTransaction().begin();
        course1.getUsers().add(new User("hans@hans.no", "hansH923", UserType.TEACHER));
        course1.getUsers().add(new User("hans@hans.no", "hansH923", UserType.TEACHER));
        course1.getUsers().add(new User("hans@hans.no", "hansH923", UserType.TEACHER));
        courseDAO.getEntityManager().getTransaction().commit();

        assertNotNull(course1.getUsers());
        assertTrue(course.getUsers().size() > 0);
        assertTrue(course.getUsers().size() == 3);
        course1.getUsers().forEach(u -> System.out.println(u));
    }

    @Test
    public void testUpdate() throws Exception {
        addCourseData();
        Course course = courseDAO.findById(1);
        course.setName("Unit-testing");

        courseDAO.getEntityManager().getTransaction().begin();
        Course courseAfterUpdate = courseDAO.update(course);
        courseDAO.getEntityManager().getTransaction().commit();

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
        List<Course> courseList = courseDAO.getAll();
        assertNotNull(courseList);
        assertTrue(courseList.size() > 0);
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
        userList.add(new User("test@test.no", "HeiTest92", UserType.TEACHER));
        userList.add(new User("test2@test.no", "HeiTest29", UserType.STUDENT));

        courseDAO.getEntityManager().getTransaction().begin();
        Course course = courseDAO.persist(new Course("PG5100", userList, null));
        courseDAO.getEntityManager().getTransaction().commit();
    }

}