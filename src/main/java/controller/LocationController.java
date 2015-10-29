package controller;

import domain.Course;
import domain.Location;
import repository.CourseDAO;
import repository.LocationDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hffb on 23/10/15.
 */
@Model
public class LocationController{

    private final LocationDAO locationDAO;
    private final CourseDAO courseDAO;
    private Location location;

    @Inject
    public LocationController(LocationDAO locationDAO, CourseDAO courseDAO){
        this.locationDAO = locationDAO;
        this.courseDAO = courseDAO;
    }


    @PostConstruct
    public void init() {
        location = new Location();
    }

    public Location getLocation() {
        return location;
    }

    public void persist() {
        locationDAO.persist(location);
    }

    public void delete(Location location) {
        courseDAO.getAll().forEach(course ->{
            if(course.getLocation() != null && course.getLocation().equals(location)){
                course.setLocation(null);
                courseDAO.update(course);
            }
        });

        locationDAO.remove(location);
    }

    public void update(Location location) {
        locationDAO.update(location);
    }

    public List<Location> getAll() {
        return locationDAO.getAll();
    }

    public List<Location> getAllLocationsIfCourseDontHaveLocation(Course course){

        if(course.getLocation() == null){
            return getAll();
        }


        return  new ArrayList<>();
    }
}
