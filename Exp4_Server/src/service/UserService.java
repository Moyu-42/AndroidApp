package service;

import bean.*;
import dao.UserOpt;

import java.util.List;

public class UserService {
    private UserOpt u;
    public UserService(Database db) { u = new UserOpt(db); }
    public int addUser(User user) {
        return u.insert(user);
    }
    public int delUser(User user) {
        return u.delete(user);
    }
    public List<User> getQuery() {
        return u.query();
    }
    public boolean search(User user) { return u.search(user); }
    public int login_varify(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        List<User> userList = u.query();

        Boolean username_exist = false;
        Boolean password_match = false;

        for (User u: userList) {
            if (username.equals(u.getUsername())) {
                username_exist = true;
                if (password.equals(u.getPassword())) {
                    password_match = true;
                }
                break;
            }
        }
        if (!username_exist) {
            return 1;
        }else if (!password_match) {
            return 2;
        }else {
            return 0;
        }
    }
}
