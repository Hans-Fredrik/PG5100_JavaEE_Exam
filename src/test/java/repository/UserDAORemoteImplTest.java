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
public class UserDAORemoteImplTest {

    private UserDAORemoteImpl userDAORemote;

    @Before
    public void setUp() throws Exception {
        WeldContainer container = new Weld().initialize();
        Instance<UserDAORemoteImpl> service = container.instance().select(UserDAORemoteImpl.class);
        userDAORemote = service.get();
    }

    @After
    public void tearDown() throws Exception {
        userDAORemote.close();
    }

    @Test
    public void testGetUser() throws Exception {
        User userFromDAO = userDAORemote.findById(1);
        assertNotNull(userFromDAO);
        assertNotNull(userFromDAO.getId());
        assertNotNull(userFromDAO.getEmail());
        assertNotNull(userFromDAO.getPassword());
        assertNotNull(userFromDAO.getUserType());
        assertTrue(userFromDAO.getId() > 0);
    }


    @Test
    public void testCreateUser() throws Exception {
        User created = userDAORemote.persist(new User("Test@Test.no", "Test12345", "Student"));
        assertNotNull(created);
        assertTrue(created.getId() > 0);
    }

    @Test
    public void testUpdateUser() throws Exception {
        final int ID = 1;
        User user = userDAORemote.findById(ID);
        String OLD_PASSWORD = user.getPassword();

        user.setPassword("TestNy123");
        userDAORemote.update(user);
        assertNotEquals(OLD_PASSWORD, userDAORemote.findById(ID).getPassword());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = userDAORemote.getAllUsers();
        userList.forEach(user -> System.out.println(user));
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final int ID = 1;
        User userToDelete = userDAORemote.findById(ID);
        assertTrue(userDAORemote.remove(userToDelete));
        assertNull(userDAORemote.findById(ID));
    }
}