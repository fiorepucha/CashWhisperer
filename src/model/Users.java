package model;

import java.util.List;
import java.util.ArrayList;

public class Users {
    private String username;
    private String password;

    public static List<Users> users = new ArrayList<>();

    public static List<Users> getUsers() {
        return users;
    }

    public void setUsername(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
