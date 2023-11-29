package demoqa.models;

import lombok.Data;

@Data
public class LoginRequestModel {
    public LoginRequestModel(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    private String userName, password;
}