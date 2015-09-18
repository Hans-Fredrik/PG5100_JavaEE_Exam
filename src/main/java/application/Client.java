package application;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import service.UserService;
import javax.enterprise.inject.Instance;
import java.util.Scanner;

/**
 * Created by hffb on 17/09/15.
 */
public class Client {

    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<UserService> service = container.instance().select(UserService.class);

        service.get().getAllUsers().forEach(user -> System.out.println(user));

        Scanner in = new Scanner(System.in);

        System.out.println("\nWelcome:\n" +
                            "print  -> print all users \n" +
                            "add    -> add new user \n" +
                            "remove -> start delete process \n" +
                            "update -> start update process \n" +
                            "quit   -> exit application");

        String input = "";
        do{
            input = in.nextLine();

            switch (input.toLowerCase()){
                case "print":

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
