package repository;

import domain.Course;

/**
 * Created by hffb on 15/10/15.
 */
public class CourseDAO extends GenericDAOImpl<Course>{

    public CourseDAO() {
        super(Course.class);
    }

}
