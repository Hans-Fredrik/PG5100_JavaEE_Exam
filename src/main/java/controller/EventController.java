package controller;

import domain.Event;
import repository.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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

    public void delete(Event event){
        eventDAO.remove(event);
    }

    
}
