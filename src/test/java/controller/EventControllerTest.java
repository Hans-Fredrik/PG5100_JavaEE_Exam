package controller;

import domain.Course;
import domain.Event;
import domain.EventType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.EventDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventControllerTest {

    private EventDAO eventDAO;
    private EventController eventController;

    @Before
    public void setUp() throws Exception {
        eventDAO = mock(EventDAO.class);
        eventController = new EventController(eventDAO);
    }

    @Test
    public void testGetEvent() throws Exception {
        eventController.init();
        assertNotNull(eventController.getEvent());
    }

    @Test
    public void testPersist() throws Exception {
        eventController.persist();
        verify(eventDAO, times(1)).persist(anyObject());
    }

    @Test
    public void testDelete() throws Exception {
        Event event = new Event();
        eventController.delete(event);
        verify(eventDAO, times(1)).remove(event);
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO: Remove or implement after everything is done..
    }

    @Test
    public void testGetAllSortedByStartingTime() throws Exception {
        when(eventDAO.getAll()).thenReturn(createEventListData());

        List<Event> eventList = eventController.getAllSortedByStartingTime();
        System.out.println(eventList.toString());

        assertEquals("event5", eventList.get(0).getTitle());
        assertEquals("event1", eventList.get(eventList.size()-1).getTitle());

    }

    private List<Event> createEventListData() throws ParseException {
        List<Event> eventList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        eventList.add(new Event(EventType.ASSIGNMENT, "event1", "event1", new Course(), simpleDateFormat.parse("2015-12-03 20:00:00"),new Date()));
        eventList.add(new Event(EventType.ASSIGNMENT, "event2", "event2", new Course(), simpleDateFormat.parse("2015-12-03 15:00:00"),new Date()));
        eventList.add(new Event(EventType.ASSIGNMENT, "event3", "event3", new Course(), simpleDateFormat.parse("2015-12-03 08:00:00"),new Date()));
        eventList.add(new Event(EventType.ASSIGNMENT, "event4", "event4", new Course(), simpleDateFormat.parse("2015-12-03 09:00:00"),new Date()));
        eventList.add(new Event(EventType.ASSIGNMENT, "event5", "event5", new Course(), simpleDateFormat.parse("2015-12-03 07:00:00"),new Date()));

        return  eventList;
    }

}

