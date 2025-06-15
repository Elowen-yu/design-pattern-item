package observer;

import pojo.PropertyFee;

/**
 * 物业费用观察者接口
 * 定义了观察者需要实现的方法
 */
public interface PropertyFeeObserver {
    /**
     * 当物业费用更新时，通知观察者
     * @param propertyFee 更新的物业费用信息
     */
    void update(PropertyFee propertyFee);
} 