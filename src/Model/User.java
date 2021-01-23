package Model;

/** This is the User class.*/
public class User {

    private int userId;
    private String userName;
    private String userPassword;

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /** @return Returns the user Id.*/
    public int getUserId() {
        return userId;
    }

    /** @return Returns the user name.*/
    public String getUserName() {
        return userName;
    }

    /** @return Returns the user password.*/
    public String getUserPassword() {
        return userPassword;
    }

    /** @return Returns user id and user name in String format.*/
    @Override
    public String toString() {
        return "[" + userId + "] " + userName;
    }

}
