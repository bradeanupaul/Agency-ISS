package domain;

public class UserConverter implements EntityConverter<User> {

    @Override
    public String toString(User object) {
        return object.getId() + ", " + object.getUsername() + ", " + object.getPassword() + " " + object.getAdmin();
    }

    @Override
    public User fromString(String line) {
        String[] tokens = line.split(", ");
        return new User(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Boolean.getBoolean(tokens[3]));
    }
}
