package domain;

public class User extends Entity {
    private String username;
    private String password;

    private boolean admin;

    public User(int id, String username, String password, boolean admin) {
        super(id);
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getAdmin() { return this.admin; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) { this.admin = admin; }
}
