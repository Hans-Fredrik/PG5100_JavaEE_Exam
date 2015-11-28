package controller;

import domain.Course;
import domain.Location;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import repository.CourseDAO;
import repository.LocationDAO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LocationControllerTest {

    private LocationController locationController;
    private LocationDAO locationDAO;
    private CourseDAO courseDAO;

    @Before
    public void setUp() throws Exception {
        locationDAO = mock(LocationDAO.class);
        courseDAO = mock(CourseDAO.class);
        locationController = new LocationController(locationDAO, courseDAO);
        locationController.init();
    }

    @Test
    public void testGetLocation() throws Exception {
        assertNotNull(locationController.getLocation());
    }

    @Test
    public void testPersist() throws Exception {
        locationController.persist();
        verify(locationDAO, times(1)).persist(new Location());
    }


    @Test
    public void testDelete() throws Exception {
        List<Course> courseList = new ArrayList<>();
        Location location1 = new Location("TestRoom", "TestBuildning");
        location1.setId(1);

        Course course = new Course();
        course.setLocation(location1);
        courseList.add(course);

        when(courseDAO.getAll()).thenReturn(courseList);
        locationController.delete(location1);
        verify(courseDAO, times(1)).getAll();
        verify(locationDAO, times(1)).remove(location1);
    }


    @Test
    public void testUpdate() throws Exception {
        Location location = new Location();
        locationController.update(location);
        verify(locationDAO, times(1)).update(location);
    }


    @Test
    public void testGetAll() throws Exception {
        locationController.getAll();
        verify(locationDAO, times(1)).getAll();
    }


    @Test
    public void testGetAllLocationsIfCourseDontHaveLocation() throws Exception {
        Course course = new Course();
        course.setLocation(new Location());

        assertNotNull(locationController.getAllLocationsIfCourseDontHaveLocation(course));
        assertEquals(0,locationController.getAllLocationsIfCourseDontHaveLocation(course).size());

        course.setLocation(null);

        when(locationController.getAllLocationsIfCourseDontHaveLocation(course)).thenReturn(new ArrayList<Location>());
        assertNotNull(locationController.getAllLocationsIfCourseDontHaveLocation(course));
        verify(locationDAO, times(1)).getAll();
    }
}