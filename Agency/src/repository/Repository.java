package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.Collection;

public class Repository <T extends Entity> extends AbstractRepository<T> {

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public T getAt(int position) {
        return elements.get(position);
    }

    @Override
    public boolean findById(int id) {
        for (T element:elements)
        {
            if (element.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public T getById(int id) throws RepositoryException {
        for (T element:elements)
        {
            if (element.getId() == id)
            {
                return element;
            }
        }
        throw new RepositoryException("No matching id element found.");
    }

    @Override
    public void removeElement(int id) throws RepositoryException {
        if (findById(id) == false)
        {
            throw new RepositoryException("No matching id element found.");
        }
        elements.remove(getById(id));
    }

    @Override
    public void updateElement(int id, T element) throws RepositoryException, DuplicateObjectException {
        if (findById(id) == false)
        {
            throw new RepositoryException("No matching id element found.");
        }
        removeElement(id);
        addElement(element);
    }

    @Override
    public void addElement(T element) throws DuplicateObjectException {
        if (findById(element.getId()))
        {
            throw new DuplicateObjectException("Same id element already exists.");
        }
        elements.add(element);
    }

    @Override
    public Collection<T> getAll() throws RepositoryException {
        if (this.elements.size() == 0)
        {
            throw new RepositoryException("There are no elements.");
        }
        return new ArrayList(this.elements);
    }
}
