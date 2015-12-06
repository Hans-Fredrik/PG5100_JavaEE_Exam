package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NavigationControllerTest {

    private NavigationController navigationController;

    @Before
    public void setUp() throws Exception {
        navigationController = new NavigationController();
    }

    @Test
    public void testProcessUsersPage() throws Exception {
        assertEquals("users", navigationController.processUsersPage());
    }

    @Test
    public void testProcessHomePage() throws Exception {
        assertEquals("index", navigationController.processHomePage());
    }

    @Test
    public void testProcessCoursePage() throws Exception {
        assertEquals("courses", navigationController.processCoursePage());
    }

    @Test
    public void testProcessLocationPage() throws Exception {
        assertEquals("location", navigationController.processLocationPage());
    }

    @Test
    public void testProcessUserDetailPage() throws Exception {
        assertEquals("user-details", navigationController.processUserDetailPage());
    }

    @Test
    public void testProcessEvent() throws Exception {
        assertEquals("events", navigationController.processEventPage());
    }

    @Test
    public void testProcessEventDetails() throws Exception {
        assertEquals("event-details", navigationController.processEventDetailPage());
    }
}