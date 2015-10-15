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


public class LocationTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testInvalidInput(){
        Location location = new Location(null,null);
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertEquals(2, violations.size());
    }

    @Test
    public  void testValidInput(){
        Location location = new Location("35", "NITH");
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertEquals(0, violations.size());
    }
}