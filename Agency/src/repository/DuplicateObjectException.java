package repository;

public class DuplicateObjectException extends RepositoryException {
    public DuplicateObjectException(String message) {
        super(message);
    }
}
