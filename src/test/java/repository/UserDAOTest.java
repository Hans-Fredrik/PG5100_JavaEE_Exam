package repository;

import domain.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.enterprise.inject.Instance;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class UserDAOTest {

    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        WeldContainer container = new Weld().initialize();
        Instance<UserDAO> service = container.instance().select(UserDAO.class);
        userDAO = service.get();
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
    public void testCreateUser() throws Exception {
        User created = userDAO.persist(new User("Test@Test.no", "Test12345", "Student"));
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
        assertTrue(userDAO.remove(userToDelete));
        assertNull(userDAO.findById(ID));
    }

    /*

    @Before
    public void begin() {
        userDAO.getEntityManager().getTransaction().begin();
    }

    @After
    public void commit(){
        userDAO.getEntityManager().getTransaction().commit();
    }
    */
}