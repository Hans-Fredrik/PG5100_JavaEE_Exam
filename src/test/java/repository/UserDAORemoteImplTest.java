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

    // TODO: Skriv om denne testen er du snill...

    private static final String PERSISTENCE_UNIT = "Forelesning1";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private UserDAORemoteImpl userDAORemote;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
        userDAORemote = new UserDAORemoteImpl();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testGetUser() throws Exception {
        User userFromEntity = entityManager.find(User.class, 1);
        assertNotNull(userFromEntity);

        User userFromDAO = userDAORemote.getUser(1);
        assertNotNull(userFromDAO);

        assertEquals(userFromEntity.getId(), userFromDAO.getId());
        assertEquals(userFromEntity.getEmail(), userFromDAO.getEmail());
    }


    @Test
    public void testCreateUser() throws Exception {
        User userToCreate = new User("12312@g.no", "123HF23jf", "Student");

        boolean created = userDAORemote.createUser(userToCreate);
        assertTrue(created);
    }

    @Test
    public void testUpdateUser() throws Exception {
        final int ID = 1;
        final String NEW_PASSWORD = "FRA IT TEST";

        User user = entityManager.find(User.class, ID);
        final String OLD_PASSWORD = user.getPassword();

        user.setPassword(NEW_PASSWORD);
        userDAORemote.updateUser(user);

        assertNotEquals(OLD_PASSWORD, entityManager.find(User.class, ID).getPassword());

        user.setPassword(OLD_PASSWORD);
        userDAORemote.updateUser(user);

        assertEquals(OLD_PASSWORD, entityManager.find(User.class, ID).getPassword());


    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = userDAORemote.getAllUsers();
        userList.forEach(user -> System.out.println(user));
        assertNotNull(userList);
    }

    @Test
    public void testDeleteUser() throws Exception {
        User userToDelete = new User("test@NITH.no", "Hei123HF2", "Student");

        userDAORemote.createUser(userToDelete);

        // TODO: Denne testen må fikses, og create må returnerer objectet som er laget!!
    }
}