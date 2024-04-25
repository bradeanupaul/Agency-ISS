package repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Settings instance;
    private String repositoryType;
    private final String repositoryFile;

    private Settings(String repositoryType, String repositoryFile)
    {
        this.repositoryFile = repositoryFile;
        this.repositoryType = repositoryType;
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    public String getRepositoryFile() {
        return repositoryFile;
    }

    private static Properties loadSettings()
    {
        try(FileReader fileReader = new FileReader("/Users/paulb/Desktop/Agency/src/settings.properties"))
        {
            Properties settings = new Properties();
            settings.load(fileReader);
            return settings;
        }
        catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public static synchronized  Settings getInstance() {
        Properties properties = loadSettings();
        instance = new Settings(properties.getProperty("repository_type"), properties.getProperty("repository_file"));
        return instance;
    }
}
