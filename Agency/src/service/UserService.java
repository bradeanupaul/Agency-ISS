package service;

import domain.*;
import repository.*;

import java.util.Collection;
import java.util.Objects;

public class UserService {
    private IRepository<User> userRepository;

    private EntityConverter<User> userEntityConverter = new UserConverter();

    Settings settings = Settings.getInstance();

    public UserService() {
        if (Objects.equals(settings.getRepositoryType(), "memory"))
        {
            this.userRepository = new Repository<User>();
        }
        if (Objects.equals(settings.getRepositoryType(), "txt"))
        {
            this.userRepository = new TextFileRepository<>(settings.getRepositoryFile(), userEntityConverter);
        }
        if (Objects.equals(settings.getRepositoryType(), "db"))
        {
            this.userRepository = new SQLUserRepository(settings.getRepositoryFile());
        }
    }

    public void addUser(int id, String username, String password, boolean admin) throws RepositoryException {
        User user = new User(id, username, password, admin);
        userRepository.addElement(user);
    }

    public void removeUser(int id) throws RepositoryException {
        userRepository.removeElement(id);
    }

    public void updateUser(int initialId, int id, String username, String password, boolean admin) throws RepositoryException {
        User user = new User(id, username, password, admin);
        userRepository.updateElement(initialId, user);
    }

    public Collection<User> getAllUsers() throws RepositoryException {
        return userRepository.getAll();
    }

    public User getById(int id) throws RepositoryException {
        return userRepository.getById(id);
    }
}
