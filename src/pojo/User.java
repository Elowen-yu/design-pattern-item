package pojo;

public abstract class User {
    private String userName;
    private String password;
    private UserType userType;

    public enum UserType {
        ADMIN,
        RESIDENT
    }

    public User() {
    }

    public User(String userName, String password, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // 抽象方法，用于获取用户特定的信息
    public abstract String getDisplayInfo();
}
