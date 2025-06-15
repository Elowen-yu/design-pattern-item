package pojo;

public class AdminUser extends User {
    private String gender;
    private String age;
    private String phone;

    public AdminUser() {
        super();
        setUserType(UserType.ADMIN);
    }

    public AdminUser(String userName, String password, String gender, String age, String phone) {
        super(userName, password, UserType.ADMIN);
        this.gender = gender;
        this.age = age;
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getDisplayInfo() {
        return String.format("管理员: %s, 性别: %s, 年龄: %s, 电话: %s", 
            getUserName(), gender, age, phone);
    }
} 