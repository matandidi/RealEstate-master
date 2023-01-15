import java.util.Objects;

public class User {

    private String userName;
    private String userPassword;

    private String phoneNumber;
    private Boolean agent;

    private Integer publishCount;

    private static final Integer MIN_USER_PASSWORD_LENGTH = 5;
    private static final int PHONE_NUMBER_LENGHT = 10;

    public User(String userName, String userPassword, String phoneNumber,
                Boolean agent, Integer publishCount) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.agent = agent;
        this.publishCount = publishCount;
    }


    public String getUserName() {
        return this.userName;
    }


    public String getUserPassword() {

        return this.userPassword;
    }



    public void setPublishCount(Integer publishCount) {

        this.publishCount = publishCount;
    }

    public Integer getPublishCount() {

        return this.publishCount;
    }


    //סיבוכיות O(1)
    protected static boolean checkStrengthPassword(String userPassword) {
        boolean strongPassword = false;
        if (userPassword.length() >= MIN_USER_PASSWORD_LENGTH) {
            for (int i = 0; i < userPassword.length(); i++) {
                if (Character.isDigit(userPassword.charAt(i))) {
                    strongPassword = true;
                    break;
                }
            }
            if (strongPassword) {
                for (int i = 0; i < userPassword.length(); i++) {
                    if (userPassword.charAt(i) == '$' || userPassword.charAt(i) == '%' || userPassword.charAt(i) == '_') {
                        strongPassword = true;
                        break;
                    } else strongPassword = false;
                }
            }

            if (!strongPassword) {
                System.out.println("Wrong password please try again");
            }
        } else {
            System.out.println("Too short password");
        }
        return strongPassword;
    }


    //סיבוכיות O(n)
    protected static boolean checkPhoneNumber(String phoneNumber) {
        boolean goodNumber = false;
        if (phoneNumber.length() != PHONE_NUMBER_LENGHT) {
            System.out.println("Too small or too long Phone number");
            return false;
        } else {
            if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '5') {
                for (int i = 2; i < phoneNumber.length(); i++) {
                    if (Character.isDigit(phoneNumber.charAt(i))) {
                        goodNumber = true;
                    } else return false;
                }
            }
        }
        return goodNumber;
    }


    // סיבוכיות O(1)
    protected static boolean checkPublishNum(User user) {
        boolean publicationsRemaining = false;
        if (user.publishCount > 0) {
            publicationsRemaining = true;
        }
        return publicationsRemaining;
    }

    // סיבוכיות O(1)
    protected static boolean checkUserAds(User user) {
        boolean propertiesFound;
        if ((user.agent && user.publishCount.equals(5)) || (!user.agent && user.publishCount == 2)) {
            propertiesFound = false;
        } else propertiesFound = true;
        return propertiesFound;
    }

    // סיבוכיות O(1)
    public String toString() {
        if (this.agent) {
            return this.getUserName() + " " + this.phoneNumber + " (Real Estate agent).";
        } else
            return this.getUserName() + " " + this.phoneNumber + " (Private person).";
    }
}
