package repository;

import domain.Entity;
import domain.EntityConverter;

import java.io.*;

public class TextFileRepository<T extends Entity> extends Repository<T> {
    private String fileName;
    private EntityConverter<T> converter;

    public TextFileRepository(String fileName, EntityConverter<T> converter)
    {
        this.converter = converter;
        this.fileName = fileName;
        try
        {
            loadFile();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void addElement(T object) throws DuplicateObjectException
    {
        super.addElement(object);
        try
        {
            saveFile();
        }
        catch (IOException exception) {
            throw new DuplicateObjectException("Error saving object.");
        }
    }

    @Override
    public void removeElement(int id) throws RepositoryException
    {
        super.removeElement(id);
        try
        {
            saveFile();
        }
        catch (IOException exception)
        {
            throw new RepositoryException("Error saving object.");
        }
    }

    @Override
    public void updateElement(int id, T object) throws RepositoryException
    {
        super.updateElement(id, object);
        try
        {
            saveFile();
        }
        catch (IOException exception)
        {
            throw new RepositoryException("Error saving object.");
        }
    }

    private void saveFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName))
        {
            for (T object:elements)
            {
                fileWriter.write(converter.toString(object));
                fileWriter.write("\r\n");
            }
        }
    }

    private void loadFile() throws IOException {
        elements.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)))
        {
            String line = bufferedReader.readLine();
            while (line != null && !line.isEmpty())
            {
                elements.add(converter.fromString(line));
                line = bufferedReader.readLine();
            }
        }
    }

}
