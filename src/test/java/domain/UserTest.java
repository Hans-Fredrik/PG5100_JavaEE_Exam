package domain;

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
        User user = new User(null, null, UserType.TEACHER);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());
    }

    @Test
    public void testInvalidEmail() throws Exception {
        User user = new User("test@test", "goodPws23", UserType.STUDENT);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testInvalidPassword() throws Exception {
        User user = new User("test@test.no", "badpw", UserType.STUDENT);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testInvalidValues()throws Exception{
        User user = new User("test", "test", UserType.STUDENT);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());
    }

    @Test
    public void testValidInput() throws Exception {
        User validUser = new User("tester@test.no", "Test123HP", UserType.STUDENT);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void testEqualsMethod() throws Exception {
        User user1 = new User();
        user1.setId(1);
        assertFalse(user1.equals(new Object()));

        User user2 = new User();
        user2.setId(2);
        assertFalse(user2.equals(user1));

        user2.setId(1);
        assertTrue(user1.equals(user2));
    }
}
