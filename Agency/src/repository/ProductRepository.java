package repository;

import domain.Product;

public class ProductRepository extends Repository<Product> {
    public void updateElements(int id, Product newProduct) throws RepositoryException {
        if (findById(id))
        {
            Product initialProduct = getById(id);
            initialProduct.setId(newProduct.getId());
            initialProduct.setName(newProduct.getName());
            initialProduct.setPrice(newProduct.getPrice());
            initialProduct.setQuantity(newProduct.getQuantity());
        }
        throw new RepositoryException("No product with matching id found.");
    }
}
