import java.util.Scanner;

public class Property {
    private String city;
    private String streetName;
    private Integer roomNumber;
    private Integer price;
    private String type;
    private Boolean forRent;
    private Integer houseNumber;
    private Integer floorNumber;
    private User user;

    static Scanner scanner = new Scanner(System.in);

    public Property(String city, String streetName, Integer roomNumber, Integer price, String type, Boolean forRent, Integer houseNumber, Integer floorNumber, User user) {
        this.city = city;
        this.streetName = streetName;
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
        this.forRent = forRent;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.user = user;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setForRent(Boolean forRent) {
        this.forRent = forRent;
    }

    public Boolean getForRent() {
        return this.forRent;
    }

    public User getUser() {
        return this.user;
    }

    //סיבוכיות O(1)
    protected static boolean checkUserProperty(User user, Property property) {
        boolean userProperty = false;
        if (property.getUser().equals(user)) {
            userProperty = true;
        }
        return userProperty;
    }

    //סיבוכיות O(1)
    protected static String typeApart() {
        System.out.println("What type of property You'd like to advertise?\n" +
                "1 - Regular apartment\n" +
                "2 - Penthouse Apartment\n" +
                "3 - Private home");
        return scanner.nextLine();
    }

    // סיבוכיות O(1)
    protected static int checkRoomsNumber() {
        System.out.println("Number of rooms in the property?");
        return scanner.nextInt();
    }

    //סיבוכיות O(1)
    protected static int forRent() {
        System.out.println("Is the property for rent or sale?\n" + "For rent press 1\n" + "For sale press 2");
        return scanner.nextInt();
    }

    //סיבוכיות O(1)
    public String toString() {
        if (!this.forRent) {
            return this.city + " - " + this.streetName + " " + this.houseNumber + "." + "\n"
                    + this.type + " - for sale " + ": " + this.roomNumber + " rooms" + ", floor " + this.floorNumber + ".\n" +
                    "Price: " + this.price + "$.\n" +
                    "Contact info: " + user.toString() +  "\n";
        } else
            return this.city + " - " + this.streetName + " " + this.houseNumber + "." + "\n" +
                    this.type + " - for rent " + ": " + this.roomNumber + " rooms" + ", floor " + this.floorNumber + ".\n" +
                    "Price: " + this.price + "$.\n" +
                    "Contact info: " + user.toString() + "\n";
    }
}