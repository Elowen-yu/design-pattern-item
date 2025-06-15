package pojo;

import observer.PropertyFeeObserver;
import observer.PropertyFeeSubject;

/**
 * 居民用户类
 */
public class ResidentUser extends User implements PropertyFeeObserver {
    private String address;
    private String vehicle;
    private PropertyFeeSubject notificationCenter;

    public ResidentUser() {
        super();
        setUserType(UserType.RESIDENT);
    }

    public ResidentUser(String userName, String password, String address, String vehicle) {
        super(userName, password);
        setUserType(UserType.RESIDENT);
        this.address = address;
        this.vehicle = vehicle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * 设置通知中心
     * @param notificationCenter 物业费用通知中心
     */
    public void setNotificationCenter(PropertyFeeSubject notificationCenter) {
        if (this.notificationCenter != null) {
            this.notificationCenter.removeObserver(this);
        }
        this.notificationCenter = notificationCenter;
        this.notificationCenter.registerObserver(this);
    }

    @Override
    public void update(PropertyFee propertyFee) {
        // 当收到物业费用通知时，更新用户界面或发送通知
        System.out.println("收到新的物业费用通知：");
        System.out.println("标题：" + propertyFee.getTitle());
        System.out.println("内容：" + propertyFee.getContent());
        System.out.println("金额：" + propertyFee.getAmount());
        System.out.println("截止日期：" + propertyFee.getDueDate());
        // 这里可以添加更多的通知处理逻辑，比如更新UI或发送邮件等
    }

    @Override
    public String getDisplayInfo() {
        return String.format("住户: %s, 住址: %s, 车牌: %s", 
            getUserName(), address, vehicle);
    }
} 