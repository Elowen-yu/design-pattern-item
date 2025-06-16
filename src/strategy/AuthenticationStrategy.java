package strategy;

import pojo.User;
 
public interface AuthenticationStrategy {
    User authenticate(String username, String password);
} 