package repository;

import domain.Product;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class SQLProductRepository extends Repository<Product> {
    private Connection connection = null;
    private final String databaseLocation;

    public SQLProductRepository(String databaseLocation) {
        this.databaseLocation = "jdbc:sqlite:"+databaseLocation;
        openConnection();
        createSchema();
        loadData();
    }

    private void openConnection() {
        try
        {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(databaseLocation);
            if (connection == null || connection.isClosed())
            {
                connection = dataSource.getConnection();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    void createSchema() {
        try
        {
            try (final Statement statement = connection.createStatement())
            {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS products(id int, name varchar(255), price int, quantity int);");
            }
        }
        catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void loadData() {
        try
        {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products"); ResultSet resultSet = statement.executeQuery();)
            {
                while (resultSet.next())
                {
                    Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("quantity"));
                    this.elements.add(product);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void addElement(Product product) throws DuplicateObjectException
    {
        super.addElement(product);
        try
        {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?)")) {
                statement.setInt(1, product.getId());
                statement.setString(2, product.getName());
                statement.setInt(3, product.getPrice());
                statement.setInt(4, product.getQuantity());
                statement.executeUpdate();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void removeElement(int id) throws RepositoryException
    {
        super.removeElement(id);
        try
        {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateElement(int id, Product product) throws RepositoryException
    {
        super.updateElement(id, product);
        removeElement(id);
        addElement(product);
    }
}
