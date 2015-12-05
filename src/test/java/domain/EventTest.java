package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class EventTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private Event createValidEventObject(){
        Course course = new Course("PG5100", new ArrayList<User>(), new Location());
        return new Event(EventType.ASSIGNMENT, "PG5100 Forelesning", "valid desc", course, new Date(), new Date());
    }


    @Test
    public void testValidInput() throws Exception {
        Event event = createValidEventObject();
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(0, violations.size());
    }


    @Test
    public void testInvalidNullInput() throws Exception {
        Event event = new Event(null, null, null, null, null, null);

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(5, violations.size());
    }

    @Test
    public void testCourseNull() throws Exception {
        Event event = createValidEventObject();
        event.setCourse(null);
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(1, violations.size());
    }

    @Test
    public void testMinTitleLength() throws Exception {
        Event event = createValidEventObject();
        event.setTitle("123");

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(1, violations.size());
    }


    @Test
    public void testMaxTitleLength() throws Exception {
        Event event = createValidEventObject();
        event.setTitle("this text is over 25 characters long");

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(1, violations.size());
    }


    @Test
    public void testDecscriptionMaxLength() throws Exception {
        Event event = createValidEventObject();
        StringBuilder description = new StringBuilder();

        for(int i = 0; i <= 101; i++){
            description.append(i);
        }

        event.setDescription(description.toString());

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(1, violations.size());
    }


    @Test
    public void testDescriptionNullOk() throws Exception {
        Event event = createValidEventObject();
        event.setDescription(null);

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(0, violations.size());
    }

    @Test
    public void testCourseValidating() throws Exception {
        Event event = createValidEventObject();
        Course course = new Course();
        event.setCourse(course);

        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        assertEquals(1, violations.size());
    }


    @Test
    public void testEquals() throws Exception {
        Event event = new Event();
        event.setId(1);
        assertFalse(event.equals(new Object()));

        Event event1 = new Event();
        event1.setId(2);
        assertFalse(event.equals(event1));

        event1.setId(1);
        assertTrue(event.equals(event1));
    }
}