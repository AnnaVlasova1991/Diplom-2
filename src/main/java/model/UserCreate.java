package model;

public class UserCreate {
    private String email;
    private String password;
    private String name;
    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
    private String message;

    public UserCreate(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserCreate(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCreate(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public UserCreate(String email) {
        this.email = email;
    }

    public UserCreate(boolean success, User user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public UserCreate(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserCreate() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
