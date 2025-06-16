package strategy;

import pojo.User;

public class AuthenticationContext {
    private AuthenticationStrategy strategy;

    public void setStrategy(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    public User authenticate(String username, String password) {
        if (strategy == null) {
            throw new IllegalStateException("认证策略未设置");
        }
        return strategy.authenticate(username, password);
    }
} 