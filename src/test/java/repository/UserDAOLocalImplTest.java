package repository;

import domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOLocalImplTest {

    private UserDAO userDAOLocal= new UserDAOLocalImpl();


    @Test
    public void testCreateUser() throws Exception {
        User userToCreate = new User("test@nith.no", "testing", "Student");
        assertTrue(userDAOLocal.createUser(userToCreate));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User(1,"hans@NITH.no", "hansformers", "Teacher");
        user.setUserType("TESTER");
        assertTrue(userDAOLocal.updateUser(user));

        assertEquals(userDAOLocal.getUser(1).getUserType(), user.getUserType());
    }

    @Test
    public void testGetUser() throws Exception {
        assertNotNull(userDAOLocal.getUser(1));
    }

    @Test
    public void testGetAllUsers() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }
}