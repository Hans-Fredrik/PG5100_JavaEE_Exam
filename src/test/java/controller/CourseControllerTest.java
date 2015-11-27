package controller;

import domain.Course;
import domain.Location;
import domain.User;
import domain.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import repository.CourseDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseControllerTest {

    CourseController courseController;
    CourseDAO courseDAO;

    @Before
    public void setUp() throws Exception {
        courseDAO = mock(CourseDAO.class);
        courseController = new CourseController(courseDAO);

        courseController.init();
        courseController.getCourse().setId(1);
        courseController.getCourse().setName("Test course");
    }

    @Test
    public void testGetCourse() throws Exception {
        assertNotNull(courseController.getCourse());
    }


    @Test
    public void testPersist() throws Exception {
        courseController.persist();
        verify(courseDAO, times(1)).persist(anyObject());
    }

    @Test
    public void testDelete() throws Exception {
        Course course = new Course("Test course", new ArrayList<>(), new Location());
        courseController.delete(course);
        verify(courseDAO, times(1)).remove(course);
    }


    @Test
    public void testGetAll() throws Exception {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course());
        courseList.add(new Course());

        when(courseDAO.getAll()).thenReturn(courseList);
        assertEquals(2, courseController.getAll().size());
    }

    @Test
    public void testGetAllCoursesNotRegisteredIn() throws Exception {
        User user = new User("test@test.no", "pass123P", UserType.STUDENT);

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("course1", new ArrayList<>(), new Location()));
        courseList.add(new Course("course2", new ArrayList<>(), new Location()));
        courseList.add(new Course("course3", new ArrayList<>(), new Location()));

        courseList.get(0).getUsers().add(user);

        when(courseDAO.getAll()).thenReturn(courseList);
        assertEquals(2, courseController.getAllCoursesNotRegisteredIn(user).size());
        assertEquals("course2", courseController.getAllCoursesNotRegisteredIn(user).get(0).getName());
        assertEquals("course3", courseController.getAllCoursesNotRegisteredIn(user).get(1).getName());
    }


    @Test
    public void testAddCourseToUser() throws Exception {

    }

    @Test
    public void testRemoveCourseFromUser() throws Exception {

    }

    @Test
    public void testAddLocation() throws Exception {

    }

    @Test
    public void testRemoveLocation() throws Exception {

    }
}