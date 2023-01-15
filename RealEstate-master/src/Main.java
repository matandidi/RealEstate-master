import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static final String CREATE_ACCOUNT = "1";
    private static final String CONNECT = "2";
    private static final String EXIT = "3";
    private static final String ADVERTISE_A_NEW_PROPERTY = "1";
    private static final String REMOVE_ADVERTISING_ABOUT_A_PROPERTY = "2";
    private static final String VIEW_ALL_PROPERTIES_IN_THE_SYSTEM = "3";
    private static final String VIEW_ALL_USER_PUBLISHED_PROPERTIES = "4";
    private static final String SEARCH_FOR_A_PROPERTY_BY_PARAMETERS = "5";
    private static final String LOG_OUT_AND_RETORN_TO_THR_MAIN_MENU = "6";

    //סיבוכיות O(1) - בהנחה שהסינון נכנסים הוא עם הרבה ערכים שמסננים נכסים ואין הרבה נכסים אחרת הסיבוכיות היא O(n)
    public static void main(String[] args) {
        RealEstate realEstate = new RealEstate();
        Scanner scanner = new Scanner(System.in);
        Property [] registeredProperties;
        User connectedUser = new User(realEstate.userName, realEstate.userPassword, realEstate.phoneNumber, realEstate.agent, realEstate.publishCount);
        String userChoice;
        do {
            do {
                do {
                    do {
                        System.out.println("       Welcome \n" +
                                "1 - Create account \n" +
                                "2 - Connect to registered account \n" +
                                "3 - Exit");
                        userChoice = scanner.nextLine();
                        if (userChoice .equals("")) {
                            userChoice = scanner.nextLine();
                        }
                    } while (!String.valueOf(userChoice).equals(CREATE_ACCOUNT) && !String.valueOf(userChoice).equals(CONNECT) && !String.valueOf(userChoice).equals(EXIT));
                    if (String.valueOf(userChoice).equals(CREATE_ACCOUNT)) {
                        realEstate.createUser();
                    }
                }while (String.valueOf(userChoice).equals(CREATE_ACCOUNT));

                if (String.valueOf(userChoice).equals(CONNECT)) {
                    connectedUser = realEstate.login();
                    if (connectedUser != null) {
                        do {
                            System.out.println("Hello " + connectedUser.getUserName() + "\n" +
                                    "What would You like to do ? \n" +
                                    "1 - Advertise a new property \n" +
                                    "2 - Remove advertising about a property \n" +
                                    "3 - View all properties in the system\n" +
                                    "4 - View all user-published properties\n" +
                                    "5 - Search for a property by parameters\n" +
                                    "6 - Log out and return to the main menu");
                            userChoice = String.valueOf(scanner.nextInt());

                            if (userChoice.equals(ADVERTISE_A_NEW_PROPERTY)) {
                                if (realEstate.postNewProperty(connectedUser)) {
                                    System.out.println("successfully advertised Property\n");
                                }
                            }

                            if (userChoice.equals(REMOVE_ADVERTISING_ABOUT_A_PROPERTY)) {
                                realEstate.removeProperty(connectedUser);
                            }

                            if (userChoice.equals(VIEW_ALL_PROPERTIES_IN_THE_SYSTEM)) {
                                realEstate.printAllProperties();
                            }

                            if (userChoice.equals(VIEW_ALL_USER_PUBLISHED_PROPERTIES)) {
                                realEstate.printUserProperties(connectedUser);
                            }

                            if (userChoice.equals(SEARCH_FOR_A_PROPERTY_BY_PARAMETERS)) {
                                registeredProperties = realEstate.search();
                                for (int i = 0; i < registeredProperties.length; i++) {
                                    System.out.println(registeredProperties[i].toString());
                                }
                            }

                            if (userChoice.equals(LOG_OUT_AND_RETORN_TO_THR_MAIN_MENU)) {
                                break;
                            }
                        }while (!userChoice.equals(ADVERTISE_A_NEW_PROPERTY) && !userChoice.equals(REMOVE_ADVERTISING_ABOUT_A_PROPERTY)
                                && !userChoice.equals(VIEW_ALL_USER_PUBLISHED_PROPERTIES) && !userChoice.equals(SEARCH_FOR_A_PROPERTY_BY_PARAMETERS));

                    } else {
                        System.out.println("User no exist ");
                    }
                }
                if (Objects.equals(userChoice, EXIT)){
                    break;
                }
            } while (connectedUser == null);
        } while (!Objects.equals(userChoice, EXIT));
}
}