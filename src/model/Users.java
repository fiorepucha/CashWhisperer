package model;

import java.util.List;

public class Users {
    private String username;
    private String password;

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
}
