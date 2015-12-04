package controller;

import domain.Course;
import domain.Event;
import domain.Location;
import domain.User;
import repository.CourseDAO;
import repository.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 23/10/15.
 */
@Model
public class CourseController {

    private final CourseDAO courseDAO;
    private final EventDAO eventDAO;
    private Course course;

    @Inject
    public CourseController(CourseDAO courseDAO, EventDAO eventDAO){
        this.courseDAO = courseDAO;
        this.eventDAO = eventDAO;
    }

    @PostConstruct
    public void init(){
        course = new Course();
    }


    public Course getCourse(){
        return course;
    }

    public void persist(){
        courseDAO.persist(course);
        course.setName("");
    }

    public void delete(Course course){
        List<Event> events = eventDAO.getAll();

        events.forEach(e -> {
            if(e.getCourse().equals(course)){
                eventDAO.remove(e);
            }
        });

        courseDAO.remove(course);
    }

    public List<Course> getAll(){
        return courseDAO.getAll();
    }

    public List<Course> getAllCoursesNotRegisteredIn(User user){
        List<Course> filteredList = new ArrayList<>();

        courseDAO.getAll().forEach(course ->{
            if(!course.getUsers().contains(user)){
                filteredList.add(course);
            }
        });

        return filteredList;
    }

    public void addCourseToUser(Course course,User user){
        if(course.getUsers() == null){
            course.setUsers(new ArrayList<>());
        }

        course.getUsers().add(user);
        courseDAO.update(course);
    }

    public void removeCourseFromUser(Course course, User user){
        course.getUsers().remove(user);
        courseDAO.update(course);
    }

    public void addLocation(Course course, Location location){
        course.setLocation(location);
        courseDAO.update(course);
    }

    public void removeLocation(Course course){
        course.setLocation(null);
        courseDAO.update(course);
    }

}
