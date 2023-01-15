import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class RealEstate {
    Scanner scanner = new Scanner(System.in);
    private static final int USERS_ARRAY_SIZE = 1000;
    private static final int PROPERTIES_ARRAY_SIZE = 10;
    private static final int CITIES_ARRAY_SIZE = 10;
    private static final int NUMBER_PUBLICATIONS_STANDART_USER = 2;
    private static final int NUMBER_PUBLICATIONS_AGENT_USER = 5;
    private static final String REGULAR_APARTMENT = "1";
    private static final String PENTHOUSE_APARTMENT = "2";
    private static final String PRIVATE_APARTMENT = "3";
    private static final String NO_FILTER = "-999";
    public String phoneNumber;
    public Boolean agent;
    public Integer publishCount;
    int publishCountBegin;
    public final int FOR_SALE = 1;
    public final int For_RENT = 2;

    User[] users;
    Property[] properties;
    City[] cities;

    String userName;
    String userPassword;
    String userChoiceToAgent;
    Boolean available;

    //סיבוכיות O(1)
    public RealEstate() {
        this.cities = new City[CITIES_ARRAY_SIZE];
        this.users = new User[USERS_ARRAY_SIZE];
        this.properties = new Property[PROPERTIES_ARRAY_SIZE];

        cities[0] = new City("Tel aviv", "Centre", new String[]{"Dizingoff", "Rabin", "Ivn Gvirol"});
        cities[1] = new City("Holon", "Centre", new String[]{"Sokolov", "Shenkar", "Histadrut"});
        cities[2] = new City("Rishon Letzion", "Centre", new String[]{"Herzel", "Rothschild", "Etzel"});
        cities[3] = new City("Ashkelon", "South", new String[]{"Rabin", "Shamir", "Bar Kohva"});
        cities[4] = new City("Ashdod", "South", new String[]{"Yoseftal", "Rogozin", "Bnei Brit"});
        cities[5] = new City("Kiryat Gat", "South", new String[]{"Yahel", "Barzel", "Ben Gurion"});
        cities[6] = new City("Netanya", "Sharon", new String[]{"Holts", "Landao", "Eshkol"});
        cities[7] = new City("Beer Sheva", "Negev", new String[]{"Ramot", "Daled", "Hanasi"});
        cities[8] = new City("Haifa", "North", new String[]{"Check post", "Rabin", "Shahak"});
        cities[9] = new City("Petah Tiqva", "Sharon", new String[]{"Em Hamoshavot", "Bet Rivka", "Sirqin"});
    }

    //סיבוכיות O(1)
    public void createUser() {
        do {
            System.out.println("Enter new user name: ");
            userName = scanner.nextLine();
            if (Objects.equals(userName, "")) {
                userName = scanner.nextLine();
            }
        } while (!checkUserAvailability(userName));

        do {
            System.out.println("Enter password:");
            userPassword = scanner.nextLine();
        } while (!User.checkStrengthPassword(userPassword));

        do {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine();
        } while (!User.checkPhoneNumber(phoneNumber));

        System.out.println("Are you an agent? \n" +
                "1 - Agent \n" +
                "2 - Regular ");
        userChoiceToAgent = scanner.nextLine();
        while (userChoiceToAgent.length() > 1 || !Character.isDigit(userChoiceToAgent.charAt(0)) ||
                Integer.parseInt(userChoiceToAgent) > 2 || Integer.parseInt(userChoiceToAgent) < 1) {
            System.out.println("Try again");
            userChoiceToAgent = scanner.nextLine();
        }
        if (userChoiceToAgent.equals("1")) {
            this.agent = true;
            this.publishCountBegin = NUMBER_PUBLICATIONS_AGENT_USER;
            this.publishCount = publishCountBegin;
        }
        if (userChoiceToAgent.equals("2")) {
            this.agent = false;
            this.publishCountBegin = NUMBER_PUBLICATIONS_STANDART_USER;
            this.publishCount = publishCountBegin;
        }


        addUserToArray(userName, userPassword, phoneNumber, agent, publishCount);

    }

    //סיבוכיות O(n)
    private void addUserToArray(String userName, String userPassword, String phoneNumber, boolean agent, int publishCount) {
        User userToAdd = new User(userName, userPassword, phoneNumber, agent, publishCount);
        User[] newUsersArr = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newUsersArr[i] = this.users[i];
            if (this.users[i] == (null)) {
                this.users[i] = userToAdd;
                break;
            }
        }
        newUsersArr[this.users.length] = userToAdd;
    }

    //סיבוכיות O(n)
    public User login() {
        System.out.println("Enter your user name");
        String typedUserName = scanner.nextLine();
        if (typedUserName.equals("")) {
            typedUserName = scanner.nextLine();
        }
        System.out.println("Enter your password");
        String typedUserPassword = scanner.nextLine();
        User checkUser = new User(typedUserName, userPassword, "00000000000", true, 5);
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] != null) {
                if (this.users[i].getUserName().equals(typedUserName) && this.users[i].getUserPassword().equals(typedUserPassword)) {
                    checkUser = this.users[i];
                    break;
                }
            } else checkUser = null;
        }
        return checkUser;
    }

    //סיבוכיות O(n)
    private boolean checkUserAvailability(String userName) {
        available = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] == null) {
                available = true;
                break;
            }
        }
        return available;
    }

    //סיבוכיות O(n^2)
    public boolean postNewProperty(User user) {
        boolean ableToAdvertise = false;
        String chosenCity;
        String chosenStreet;
        String apartUserChoice;
        int roomsNumber = 0;
        int addressNumber = 0;
        boolean sale = true;
        int price;
        int floor = 0;
        int rentOrSale;
        if (User.checkPublishNum(user)) {
            ableToAdvertise = true;
            for (int i = 0; i < this.cities.length; i++) {
                System.out.println(this.cities[i].getCityName());
            }

            System.out.println("\nChoose city");
            chosenCity = scanner.nextLine();
            for (int i = 0; i < this.cities.length; i++) {
                if (Objects.equals(this.cities[i].getCityName(), chosenCity)) {
                    System.out.println(Arrays.toString(this.cities[i].getStreetNameArr()));
                    System.out.println("\nChoose street");
                    chosenStreet = scanner.nextLine();
                    if (City.chosenStreet(this.cities[i], chosenStreet)) {
                        apartUserChoice = Property.typeApart();
                        if (apartUserChoice.equals("")) {
                            apartUserChoice = scanner.nextLine();
                        }
                        if ((!String.valueOf(apartUserChoice).equals(REGULAR_APARTMENT) && !String.valueOf(apartUserChoice).equals(PENTHOUSE_APARTMENT)
                                && !String.valueOf(apartUserChoice).equals(PRIVATE_APARTMENT))) {
                            System.out.println("Wrong type");
                            ableToAdvertise = false;
                            break;
                        }
                        if (String.valueOf(apartUserChoice).equals("1")) {
                            do {
                                apartUserChoice = "Regular apartment";
                                System.out.println("What floor is the property on?");
                                floor = scanner.nextInt();
                            } while (floor < 0);
                        }
                        if (String.valueOf(apartUserChoice).equals("2")) {
                            apartUserChoice = "Penthouse apartment";
                        }
                        if (String.valueOf(apartUserChoice).equals("3")) {
                            apartUserChoice = "Private house";
                        }
                        do {
                            roomsNumber = Property.checkRoomsNumber();
                        } while (roomsNumber < 0 && !Character.isDigit(roomsNumber));

                        do {
                            System.out.println("What the address number?");
                            addressNumber = scanner.nextInt();
                        } while (addressNumber < 0 && !Character.isDigit(addressNumber));

                        do {
                            rentOrSale = Property.forRent();
                            if (rentOrSale == For_RENT) {
                                sale = false;
                            }
                            if (rentOrSale == FOR_SALE) {
                                sale = true;
                            }

                            do {
                                System.out.println("Enter a price");
                                price = scanner.nextInt();
                            } while (price >= 0 && Character.isDigit(price));
                        } while (rentOrSale != FOR_SALE && rentOrSale != For_RENT);

                        for (int j = 0; j < this.properties.length; j++) {
                            if (this.properties[j] == null) {
                                this.properties[j] = new Property(chosenCity, chosenStreet, roomsNumber, price, apartUserChoice, sale, addressNumber, floor, user);
                                user.setPublishCount(user.getPublishCount() - 1);
                                System.out.println(user.getUserName() + " You left " + user.getPublishCount() + " Advertisement ads");
                                ableToAdvertise = true;
                                break;
                            }

                        }

                    } else {
                        System.out.println("This street not exists");
                        ableToAdvertise = false;
                        break;
                    }
                    break;
                } else {
                    if (!Objects.equals(this.cities[i].getCityName(), chosenCity)) {
                        if (i == this.cities.length - 1) {
                            System.out.println("This city not found");
                            ableToAdvertise = false;
                        }
                    }
                }
            }
        } else {
            System.out.println("This user has no publications left");
        }

        return ableToAdvertise;
    }

    // סיבוכיות O(n)
    public void removeProperty(User user) {
        String deleteUserChoice;
        int usedAds = 1;
        if (!User.checkUserAds(user)) {
            System.out.println("user has not yet published properties");
        } else {
            if (User.checkUserAds(user)) {
                for (int i = 0; i < this.properties.length; i++) {
                    if (this.properties[i] != null) {
                        if (this.properties[i].getUser().equals(user)) {
                            System.out.println(usedAds + "\n" +
                                    this.properties[i].toString());
                            usedAds++;
                        }
                    }
                }
                System.out.println("Which of these Properties would you like to delete? ");
                usedAds = 1;
                deleteUserChoice = scanner.nextLine();
                for (int i = 0; i < this.properties.length; i++) {
                    if (deleteUserChoice.equals(String.valueOf(usedAds))) {
                        this.properties[i] = null;
                        System.out.println("Property deleted");
                    }
                    usedAds++;
                }
            }
        }
    }

    //סיבוכיות O(n)
    protected void printAllProperties() {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i] != null) {
                System.out.println(this.properties[i].toString());
            }
        }
    }

    //סיבוכיות O(n)
    protected void printUserProperties(User user) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i] != null && Property.checkUserProperty(user, this.properties[i])) {
                System.out.println(this.properties[i].toString());
            }
        }
    }

    //סיבוכיות O(n^2)
    protected Property[] search() {
        String forRent;
        String type;
        int roomNumber;
        int minPrice;
        int maxPrice;
        int filterArrSize = 0;
        Property[] tempArr = new Property[this.properties.length];
        Property filterProperty = new Property("null", "null", 0, 0, "", false, 0, 0, null);
        System.out.println("Here are some questions about the property\n" +
                "At any point you can press -999 if the question is irrelevant");
        do {
            forRent = String.valueOf(Property.forRent());
            if (Integer.valueOf(forRent).equals(FOR_SALE)) {
                filterProperty.setForRent(true);
            }
            if (Integer.valueOf(forRent).equals(For_RENT)) {
                filterProperty.setForRent(false);

            }
        } while (!Integer.valueOf(forRent).equals(FOR_SALE) && !Integer.valueOf(forRent).equals(For_RENT) && !forRent.equals(NO_FILTER));

        do {
            type = Property.typeApart();
            if (type.equals("")) {
                type = scanner.nextLine();
            }
            if (Objects.equals(type, REGULAR_APARTMENT)) {
                filterProperty.setType("Regular apartment");
            }

            if (Objects.equals(type, PENTHOUSE_APARTMENT)) {
                filterProperty.setType("Penthouse apartment");
            }

            if (Objects.equals(type, PRIVATE_APARTMENT)) {
                filterProperty.setType("Private house");
            }

        } while (!Objects.equals(type, REGULAR_APARTMENT) && !Objects.equals(type, PENTHOUSE_APARTMENT)
                && !Objects.equals(type, PRIVATE_APARTMENT) && !Objects.equals(type, NO_FILTER));

        do {
            roomNumber = Property.checkRoomsNumber();
            filterProperty.setRoomNumber(roomNumber);
        } while (roomNumber < 0 && !String.valueOf(roomNumber).equals(NO_FILTER));

        do {
            System.out.println("What is the minimum price ?");
            minPrice = scanner.nextInt();
            System.out.println("What is the maximum price ?");
            maxPrice = scanner.nextInt();
        } while (maxPrice < minPrice);

        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i] != null) {
                if (this.properties[i].getForRent().equals(filterProperty.getForRent()) || forRent.equals(NO_FILTER)) {

                    if (this.properties[i].getType().equals(filterProperty.getType()) || filterProperty.getType().equals("")) {

                        if (this.properties[i].getRoomNumber().equals(filterProperty.getRoomNumber())
                                || String.valueOf(filterProperty.getRoomNumber()).equals(NO_FILTER)) {

                            if ((String.valueOf(minPrice).equals(NO_FILTER) && (String.valueOf(maxPrice).equals(NO_FILTER))) ||
                                    (this.properties[i].getPrice() >= minPrice && this.properties[i].getPrice() <= maxPrice) ||
                                    String.valueOf(minPrice).equals(NO_FILTER) && !String.valueOf(maxPrice).equals(NO_FILTER) ||
                                    !String.valueOf(minPrice).equals(NO_FILTER) && String.valueOf(maxPrice).equals(NO_FILTER)) {
                                if (this.properties[i].getPrice() <= maxPrice || this.properties[i].getPrice() >= minPrice) {
                                    filterArrSize++;
                                }

                                for (int j = 0; j < tempArr.length; j++) {
                                    if (tempArr[j] == null) {
                                        tempArr[j] = this.properties[i];
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

            Property[] filterPropertiesArr = new Property[filterArrSize];
            for (int i = 0; i < filterPropertiesArr.length; i++) {
                if (filterPropertiesArr[i] == null) {
                    filterPropertiesArr[i] = tempArr[i];
                }
            }
            return filterPropertiesArr;

    }
}