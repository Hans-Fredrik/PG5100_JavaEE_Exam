package controller;

import domain.User;
import domain.UserType;
import org.junit.Before;
import org.junit.Test;
import repository.UserDAO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserDAO userDAO;
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAO.class);
        userController = new UserController(userDAO);
        userController.init();
    }

    @Test
    public void testGetUser() throws Exception {
        userController.init();
        assertNotNull(userController.getUser());
    }

    @Test
    public void testPersist() throws Exception {
        userController.persist();
        verify(userDAO, times(1)).persist(anyObject());
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        userController.delete(user);
        verify(userDAO, times(1)).remove(user);
    }

    @Test
    public void testUpdateFail() throws Exception {
        final int userID = 1;
        assertEquals("users", userController.update(userID));
        when(userDAO.findById(userID)).thenReturn(null);
        verify(userDAO, times(1)).findById(userID);
        verify(userDAO, never()).update(anyObject());
    }

    @Test
    public void testUpdate() throws Exception {
        final int userID = 2;
        userController.init();
        userController.getUser().setEmail("Test@Test.no");
        userController.getUser().setPassword("test123T");
        userController.getUser().setUserType(UserType.TEACHER);

        when(userDAO.findById(userID)).thenReturn(new User());
        assertEquals("users", userController.update(userID));
        verify(userDAO, times(1)).findById(userID);
        verify(userDAO, times(1)).update(anyObject());
    }

    @Test
    public void testGetAll() throws Exception {
        when(userController.getAll()).thenReturn(new ArrayList<User>());
        assertNotNull(userController.getAll());
        verify(userDAO, times(1)).getAll();
    }

    @Test
    public void testInitUser() throws Exception {
        userController.setSelectedId(1);
        userController.initUser();
        verify(userDAO, times(1)).findById(1);
    }

    @Test
    public void testSetGetSelectedId() throws Exception {
        userController.setSelectedId(1);
        assertEquals(1, userController.getSelectedId());
    }
}