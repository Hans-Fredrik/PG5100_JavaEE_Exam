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
public class UserDaoRemoteImplTestNew {

    private static final String PERSISTENCE_UNIT = "Forelesning1";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDAONewRemoteImpl userDAORemote;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
        userDAORemote = new UserDAONewRemoteImpl();
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
        User userToCreate = new User("12312@g.no", "123", "Student");

        boolean created = userDAORemote.createUser(userToCreate);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.find(User.class, userToCreate.getId()));
        entityTransaction.commit();

        assertTrue(created);
        assertFalse(entityManager.contains(userToCreate));
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
        assertNotNull(userList);
    }

    @Test
    public void testDeleteUser() throws Exception {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        User userToDelete = new User("test@NITH.no", "ingen", "Student");
        entityTransaction.begin();
        entityManager.persist(userToDelete);
        entityTransaction.commit();

        assertTrue(userDAORemote.deleteUser(userToDelete));
    }
}