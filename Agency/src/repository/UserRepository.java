package repository;

import domain.User;

import java.util.Collection;

public class UserRepository extends Repository<User> {
    public void updateElements(int id, User newUser) throws RepositoryException {
        if (findById(id))
        {
            User initialUser = getById(id);
            initialUser.setId(newUser.getId());
            initialUser.setUsername(newUser.getUsername());
            initialUser.setPassword(newUser.getPassword());
            initialUser.setAdmin(newUser.getAdmin());
        }
        throw new RepositoryException("No user with matching id found.");
    }
}
