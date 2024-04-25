package domain;

public class ProductConverter implements EntityConverter<Product> {

    @Override
    public String toString(Product object) {
        return object.getId() + ", " + object.getName() + ", " + object.getPrice() + ", " + object.getQuantity();
    }

    @Override
    public Product fromString(String line) {
        String[] tokens = line.split(", ");
        return new Product(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }
}
