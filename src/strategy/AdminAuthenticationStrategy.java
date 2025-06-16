package strategy;

import DAO.AdminDAO;
import pojo.User;

public class AdminAuthenticationStrategy implements AuthenticationStrategy {
    @Override
    public User authenticate(String username, String password) {
        User user = AdminDAO.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
} 