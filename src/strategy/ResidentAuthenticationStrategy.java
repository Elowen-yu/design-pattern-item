package strategy;

import DAO.ResDAO;
import pojo.User;

public class ResidentAuthenticationStrategy implements AuthenticationStrategy {
    @Override
    public User authenticate(String username, String password) {
        User user = ResDAO.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
} 