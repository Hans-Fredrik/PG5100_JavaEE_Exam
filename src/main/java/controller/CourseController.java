package controller;

import domain.Course;
import repository.CourseDAO;

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
    private Course course;

    @Inject
    public CourseController(CourseDAO courseDAO){
        this.courseDAO = courseDAO;
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
        courseDAO.remove(course);
    }

    public List<Course> getAll(){
        System.out.println("GetALL");
        return courseDAO.getAll();
    }

}
