package application;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import repository.UserDAO;
import service.UserService;
import javax.enterprise.inject.Instance;
import java.util.Scanner;

/**
 * Created by hffb on 17/09/15.
 */
public class Client {

    private UserService userService;


    public Client(UserService userService) {
        this.userService = userService;

        try(Scanner in = new Scanner(System.in)){

            System.out.println(welcomeMessage());
            String input = "";

            do{
                input = in.nextLine();
                switch (input.toLowerCase()){
                    case "print":
                        printUsers();
                        break;
                    case "add":

                        break;
                    case "remove":

                        break;
                    case "update":

                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Commando not recognized");
                }

            }while(!input.equals("quit"));
        }
    }

    public String welcomeMessage(){
        return "\nWelcome:\n" +
                "print  -> print all users \n" +
                "add    -> add new user \n" +
                "remove -> start delete process \n" +
                "update -> start update process \n" +
                "quit   -> exit application";
    }

    public void printUsers(){
        userService.getAllUsers().forEach(user -> System.out.println(user));
    }

    public void addUser(Scanner input){
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Password: ");
        String password = input.nextLine();
        System.out.println("User Type: ");
        String userType = input.nextLine();
    }

    public void removeUser(){

    }

    public void updateUser(){

    }


    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }



    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<UserService> service = container.instance().select(UserService.class);

        service.get().getAllUsers().forEach(user -> System.out.println(user));

        new Client(service.get());
    }
}
