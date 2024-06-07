package controller;

import model.User;
import util.DatabaseUtil;

public class UserController {
    public boolean login(String username, String password) {
        User user = DatabaseUtil.loadUser(username);
        return user != null && user.getPassword().equals(password);
    }

    public void register(String username, String password, String firstName, String lastName) {
        User user = new User(username, password, firstName, lastName);
        DatabaseUtil.saveUser(user);
    }

    public void editProfile(User user, String firstName, String lastName, String password) {
        user.editProfile(firstName, lastName, password);
        DatabaseUtil.updateUser(user);
    }

    public void upgradeToVIP(User user, String email) {
        user.upgradeToVIP(email);
        DatabaseUtil.updateUser(user);
    }
}
