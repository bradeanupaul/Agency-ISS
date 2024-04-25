package repository;

import domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    public int getSize();
    public T getAt(int position);

    public void addElement(T o) throws DuplicateObjectException;
    public void removeElement(int id) throws RepositoryException;
    public void updateElement(int id, T elem) throws RepositoryException, DuplicateObjectException;

    public boolean findById(int id);
    public T getById(int id) throws  RepositoryException;

    public Collection<T> getAll() throws  RepositoryException;
}
