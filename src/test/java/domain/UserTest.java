package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testInvalidNullInput() throws Exception {
        User user = new User(null, null, "Student");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.forEach(v -> System.out.println(v));
        assertEquals(2, violations.size());
    }



    @Test
    public void testInvalidValue()throws Exception{
        User user = new User("test", "test", "Student");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.forEach(v -> System.out.println(v));
        assertEquals(2, violations.size());
    }


    @Test
    public void testValidInput() throws Exception {
        User validUser = new User("tester@test.no", "Test123HP", "Student");
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }
}