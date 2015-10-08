package repository;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class UserDAORemoteImplTest {

    private UserDAORemoteImpl userDAORemote;

    @Before
    public void setUp() throws Exception {
        userDAORemote = new UserDAORemoteImpl("Forelesning1Test");
    }

    @After
    public void tearDown() throws Exception {
        userDAORemote.close();
    }

    @Test
    public void testGetUser() throws Exception {
        User userFromDAO = userDAORemote.getUser(1);
        assertNotNull(userFromDAO);
        assertNotNull(userFromDAO.getId());
        assertNotNull(userFromDAO.getEmail());
        assertNotNull(userFromDAO.getPassword());
        assertNotNull(userFromDAO.getUserType());
    }


    @Test
    public void testCreateUser() throws Exception {
        User userToCreate = new User("12312@g.no", "123HF23jf", "Student");
        User created = userDAORemote.createUser(userToCreate);
        assertNotNull(created);
    }

    @Test
    public void testUpdateUser() throws Exception {
        final int ID = 2;
        final String NEW_PASSWORD = "Tester123T";

        User user = userDAORemote.getUser(ID);
        final String OLD_PASSWORD = user.getPassword();

        user.setPassword(NEW_PASSWORD);
        userDAORemote.updateUser(user);

        assertNotEquals(OLD_PASSWORD, userDAORemote.getUser(ID).getPassword());

        user.setPassword(OLD_PASSWORD);
        userDAORemote.updateUser(user);

        assertEquals(OLD_PASSWORD, userDAORemote.getUser(ID).getPassword());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = userDAORemote.getAllUsers();
        userList.forEach(user -> System.out.println(user));
        assertNotNull(userList);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final int ID = 1;
        final String EMAIL = "test@t.no";
        final String PASSWORD = "Tester123";
        final String TYPE = "Tester";

        User userToDelete = new User(ID, EMAIL, PASSWORD, TYPE);


        // TODO: Denne testen må fikses, og create må returnerer objectet som er laget!!
    }
}