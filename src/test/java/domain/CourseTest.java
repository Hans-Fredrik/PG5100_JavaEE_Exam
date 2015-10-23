package domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class CourseTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testValidInput() throws Exception {
        Course course = new Course("PG5100 Java", null, null);
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidInputName() throws Exception {
        Course course = new Course(null, null, null);
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertEquals(1, violations.size());
    }
}