package controller;

import domain.Event;
import repository.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hffb on 03/12/15.
 */
@Model
public class EventController {

    private final EventDAO eventDAO;
    private Event event;

    @Inject
    public EventController(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }

    @PostConstruct
    public void init(){
        event = new Event();
    }

    public Event getEvent(){
        return event;
    }

    public void persist(){
        eventDAO.persist(event);
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
}
