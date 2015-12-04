package controller;

import domain.Course;
import domain.Event;;
import repository.CourseDAO;
import repository.EventDAO;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Created by hffb on 03/12/15.
 */
@Model
public class EventController {

    private final EventDAO eventDAO;
    private final CourseDAO courseDAO;
    private Event event;
    private int selectedEventId;
    private int selectedCourseId;


    @Inject
    public EventController(EventDAO eventDAO, CourseDAO courseDAO){
        this.eventDAO = eventDAO;
        this.courseDAO = courseDAO;
    }

    @PostConstruct
    public void init(){
        event = new Event();
        event.setStartingTime(new Date());
        event.setEndingTime(new Date());
    }

    public void initEvent(){
        this.event = eventDAO.findById(selectedEventId);
    }

    public Event getEvent() {
        return event;
    }

    public void persist(){
        Course course = courseDAO.findById(selectedCourseId);
        event.setCourse(course);
        eventDAO.persist(event);
        event.setTitle("");
        event.setDescription("");
    }

    public void delete(Event event){
        eventDAO.remove(event);
    }

    /*
    public String update(int id){
        Event eventToUpdate = eventDAO.findById(id);
        if(eventToUpdate == null) return "events";

        return "events";
    }*/

    public List<Event> getAllSortedByStartingTime(){
        List<Event> eventList = eventDAO.getAll();
        return eventList.stream().sorted(Comparator.comparing(Event::getStartingTime)).collect(Collectors.toList());
    }

    public int getSelectedEventId() {
        return selectedEventId;
    }

    public void setSelectedEventId(int selectedEventId) {
        this.selectedEventId = selectedEventId;
    }

    public int getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(int selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }
}
