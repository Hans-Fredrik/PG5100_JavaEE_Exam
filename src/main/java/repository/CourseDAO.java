package repository;

import domain.Course;

import javax.persistence.EntityManager;

/**
 * Created by hffb on 15/10/15.
 */
public class CourseDAO extends GenericDAOImpl<Course>{

    public CourseDAO() {
        super(Course.class);
    }

    public CourseDAO(EntityManager entityManager){
        super(entityManager, Course.class);
    }

}


