package pojo;

public class UserFactory {
    public static User createUser(User.UserType userType) {
        switch (userType) {
            case ADMIN:
                return new AdminUser();
            case RESIDENT:
                return new ResidentUser();
            default:
                throw new IllegalArgumentException("未知的用户类型: " + userType);
        }
    }

    public static User createUser(User.UserType userType, String userName, String password) {
        User user = createUser(userType);
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }

    public static User createAdminUser(String userName, String password, String gender, String age, String phone) {
        return new AdminUser(userName, password, gender, age, phone);
    }

    public static User createResidentUser(String userName, String password, String address, String vehicle) {
        return new ResidentUser(userName, password, address, vehicle);
    }
} 