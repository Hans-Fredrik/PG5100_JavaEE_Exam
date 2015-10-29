package controller;

import javax.enterprise.inject.Model;

/**
 * Created by hffb on 22/10/15.
 */
@Model
public class NavigationController {

    public String proccessUsersPage(){
        return "users";
    }
    public String proccessHomePage(){
        return "index";
    }

    public String proccessCoursePage(){
        return "courses";
    }

    public String proccessLocationPage(){
        return "location";
    }
}
