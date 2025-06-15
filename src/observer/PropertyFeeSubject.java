package observer;

/**
 * 物业费用主题接口
 * 定义了主题需要实现的方法
 */
public interface PropertyFeeSubject {
    /**
     * 注册观察者
     * @param observer 要注册的观察者
     */
    void registerObserver(PropertyFeeObserver observer);

    /**
     * 移除观察者
     * @param observer 要移除的观察者
     */
    void removeObserver(PropertyFeeObserver observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
} 