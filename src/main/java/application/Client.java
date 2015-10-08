package application;

import domain.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import repository.UserDAORemoteImpl;
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
                        addUser(in);
                        break;
                    case "remove":
                        removeUser(in);
                        break;
                    case "update":
                        updateUser(in);
                        break;
                    case "quit":
                        break;
                    default:
                        System.out.println("Commando not recognized");
                }

            }while(!input.equals("quit"));
        }

        if(userService.userDAO instanceof UserDAORemoteImpl){
            ((UserDAORemoteImpl) userService.userDAO).entityManager.close();
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

        User user = new User(email, password,userType);
        System.out.println("Do you want to create y/n: " + user);
        String in = input.nextLine();
        if(in.equalsIgnoreCase("y")){
            System.out.println("User created: " + userService.createUser(user));
        }else{
            System.out.println("Did not create user..");
        }
    }

    public void updateUser(Scanner input){
        System.out.println("Enter ID: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.println("Enter email, password and user type");

        String email = input.nextLine();
        String password = input.nextLine();
        String studentType = input.nextLine();

        User userToUpdate = new User(id, email, password, studentType);

        userService.updateUser(userToUpdate);
        System.out.println("User updated..");
    }

    public void removeUser(Scanner input){
        System.out.println("ID of remove object: ");
        int id = input.nextInt();
        System.out.println("Removed: " + userService.deleteUserByID(id));
    }


    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }


    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<UserService> service = container.instance().select(UserService.class);

        UserService userService1 = service.get();

        new Client(userService1);

        System.exit(0);
    }
}
