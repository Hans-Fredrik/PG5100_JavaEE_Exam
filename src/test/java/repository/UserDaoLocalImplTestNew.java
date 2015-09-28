package repository;

import domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoLocalImplTestNew {

    private UserDao userDAOLocal= new UserDAOLocalImpl();


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

        assertFalse(userDAOLocal.updateUser(new User(1000, "ingen", "ingen", "ingen")));
    }

    @Test
    public void testGetUser() throws Exception {
        assertNotNull(userDAOLocal.getUser(1));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        assertNotNull(userDAOLocal.getAllUsers());

    }

    @Test
    public void testDeleteUser() throws Exception {
        User toDelete = new User(99, "test@email.no", "Ingen", "Tester");
        userDAOLocal.createUser(toDelete);
        assertTrue(userDAOLocal.deleteUser(toDelete));
    }
}