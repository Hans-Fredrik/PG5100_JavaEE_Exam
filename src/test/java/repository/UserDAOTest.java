package repository;

import domain.Course;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.enterprise.inject.Instance;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class UserDAOTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("egentrening");
        entityManager = entityManagerFactory.createEntityManager();
        userDAO = new UserDAO(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        userDAO.close();
    }

    @Test
    public void testGetUser() throws Exception {
        User userFromDAO = userDAO.findById(1);
        assertNotNull(userFromDAO);
        assertNotNull(userFromDAO.getId());
        assertNotNull(userFromDAO.getEmail());
        assertNotNull(userFromDAO.getPassword());
        assertNotNull(userFromDAO.getUserType());
        assertTrue(userFromDAO.getId() > 0);
    }

    @Test
    public void testUpdateUserAndAddCourses() throws Exception {
        User user = userDAO.findById(1);
        user.getCourses().add(new Course("PG5100", null, null));

        entityManager.getTransaction().begin();
        userDAO.update(user);
        entityManager.getTransaction().commit();

        User userBack = userDAO.findById(1);
        assertTrue(userBack.getCourses().size() > 0);
    }

    @Test
    public void testCreateUser() throws Exception {
        entityManager.getTransaction().begin();
        User created = userDAO.persist(new User("Test@Test.no", "Test12345", "Student"));
        entityManager.getTransaction().commit();
        assertNotNull(created);
        assertTrue(created.getId() > 0);
    }

    @Test
    public void testUpdateUser() throws Exception {
        final int ID = 1;
        User user = userDAO.findById(ID);
        String OLD_PASSWORD = user.getPassword();

        user.setPassword("TestNy123");
        userDAO.update(user);
        assertNotEquals(OLD_PASSWORD, userDAO.findById(ID).getPassword());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = userDAO.getAll();
        userList.forEach(user -> System.out.println(user));
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final int ID = 1;
        User userToDelete = userDAO.findById(ID);
        entityManager.getTransaction().begin();
        assertTrue(userDAO.remove(userToDelete));
        entityManager.getTransaction().commit();
        assertNull(userDAO.findById(ID));
    }

}