package observer;

import pojo.PropertyFee;
import java.util.ArrayList;
import java.util.List;

/**
 * 物业费用通知中心
 * 实现了观察者模式的主题
 */
public class PropertyFeeNotificationCenter implements PropertyFeeSubject {
    private List<PropertyFeeObserver> observers;
    private PropertyFee propertyFee;

    public PropertyFeeNotificationCenter() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(PropertyFeeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(PropertyFeeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PropertyFeeObserver observer : observers) {
            observer.update(propertyFee);
        }
    }

    /**
     * 发布新的物业费用通知
     * @param propertyFee 新的物业费用信息
     */
    public void publishNewFee(PropertyFee propertyFee) {
        this.propertyFee = propertyFee;
        notifyObservers();
    }
} 