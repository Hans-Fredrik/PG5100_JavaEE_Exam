package service;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.UserDAO;
import repository.UserDAORemoteImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        userService.userDAO  = Mockito.mock(UserDAO.class);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllUsers() throws Exception {


    }

    @Test
    public void testCreateUser() throws Exception {

    }

    @Test
    public void testDeleteUserByID() throws Exception {
        assertFalse(userService.deleteUserByID(-1));

        when(userService.userDAO.getUser(999999)).thenReturn(null);

        assertFalse(userService.deleteUserByID(99999));

        when(userService.userDAO.getUser(1)).thenReturn(new User(1, "test", "test", "test"));

        assertNotNull(userService.deleteUserByID(1));

        verify(userService.userDAO, atLeastOnce()).deleteUser(any());

    }




    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testGetUserByID() throws Exception {

    }
}