package service;

import domain.*;
import repository.*;

import java.util.Collection;
import java.util.Objects;

public class ProductService {
    private IRepository<Product> productRepository;

    private EntityConverter<Product> productEntityConverter = new ProductConverter();

    Settings settings = Settings.getInstance();

    public ProductService() {
        if (Objects.equals(settings.getRepositoryType(), "memory"))
        {
            this.productRepository = new Repository<Product>();
        }
        if (Objects.equals(settings.getRepositoryType(), "txt"))
        {
            this.productRepository = new TextFileRepository<>(settings.getRepositoryFile(), productEntityConverter);
        }
        if (Objects.equals(settings.getRepositoryType(), "db"))
        {
            this.productRepository = new SQLProductRepository(settings.getRepositoryFile());
        }
    }

    public void addProduct(int id, String name, int price, int quantity) throws RepositoryException {
        Product product = new Product(id, name, price, quantity);
        productRepository.addElement(product);
    }

    public void removeProduct(int id) throws RepositoryException {
        productRepository.removeElement(id);
    }

    public void updateProduct(int initialId, int id, String name, int price, int quantity) throws RepositoryException {
        Product product = new Product(id, name, price, quantity);
        productRepository.updateElement(initialId, product);
    }

    public Collection<Product> getAllProducts() throws RepositoryException {
        return productRepository.getAll();
    }

    public Product getById(int id) throws RepositoryException {
        return productRepository.getById(id);
    }

    public void orderProduct(int givenId, int givenQuantity) throws RepositoryException {
        Product product = getById(givenId);
        int quantity = product.getQuantity();
        String name = product.getName();
        int price = product.getPrice();
        int newQuantity = quantity - givenQuantity;
        Product newProduct = new Product(givenId, name, price, newQuantity);
        productRepository.updateElement(givenId, newProduct);
    }
}
