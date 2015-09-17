package repository;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAORemoteImplTest {

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

    }

    @Test
    public void testGetUser() throws Exception {
        User user = entityManager.find(User.class, 2);
        System.out.println(user.toString());

        Query findByEmail = entityManager.createNamedQuery("findByEmail", User.class).setParameter("mail", "hans@g.no");
        User user2 = (User) findByEmail.getResultList().get(0);
        System.out.println("Here: " + user2);

        User user3 = userDAORemote.getUser(1);
        assertNotNull(user3);
    }


    @Test
    public void testCreateUser() throws Exception {

    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = userDAORemote.getAllUsers();
        assertNotNull(userList);
    }

    @Test
    public void testDeleteUser() throws Exception {

    }
}