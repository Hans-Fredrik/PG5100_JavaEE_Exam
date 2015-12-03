package controller;

import javax.enterprise.inject.Model;

/**
 * Created by hffb on 22/10/15.
 */
@Model
public class NavigationController {

    public String processUsersPage(){
        return "users";
    }
    public String processHomePage(){
        return "index";
    }
    public String processCoursePage(){
        return "courses";
    }
    public String processLocationPage(){
        return "location";
    }
    public String processUserDetailPage(){
        return "user-details";
    }
    public String processEventPage(){
        return "events";
    }
    public String processEventDetailPage(){
        return "event-details";
    }


}
