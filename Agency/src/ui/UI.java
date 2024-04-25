package ui;

import domain.Product;
import repository.DuplicateObjectException;
import repository.RepositoryException;
import service.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private ProductService productService = new ProductService();

    public void addProduct()
    {
        try {
            int id, price, quantity;
            String name;
            System.out.println("Product id: ");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            System.out.println("Product name: ");
            name = cin.next();
            System.out.println("Product price: ");
            price = cin.nextInt();
            System.out.println("Product quantity: ");
            quantity = cin.nextInt();
            productService.addProduct(id, name, price, quantity);
            System.out.println("The product with id "+id+" has been succesfully added.");
        }
        catch (RepositoryException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void removeProduct()
    {
        try {
            int id;
            System.out.println("Product id: ");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            productService.removeProduct(id);
            System.out.println("The product with id "+id+" has been successfully removed.");
        }
        catch (RepositoryException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void updateProduct()
    {
        try
        {
            int initialId, id, price, quantity;
            String name;
            System.out.println("Product id: ");
            Scanner cin = new Scanner(System.in);
            initialId = cin.nextInt();
            System.out.println("New product id: ");
            id = cin.nextInt();
            System.out.println("New product name: ");
            name = cin.next();
            System.out.println("New product price: ");
            price = cin.nextInt();
            System.out.println("New product quantity: ");
            quantity = cin.nextInt();
            productService.updateProduct(initialId, id, name, price, quantity);
            System.out.println("The product with id "+id+" has been successfully updated.");
        }
        catch (RepositoryException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void showProducts()
    {
        try
        {
            System.out.println("These are the products registered in the system:");
            for (Product product: productService.getAllProducts())
            {
                System.out.println(product);
            }
        }
        catch (RepositoryException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void administratorOptions()
    {
        System.out.println("Interface for the ADMINISTRATOR:");
        System.out.println("Managing the products.");
        System.out.println(" ");
        System.out.println("1. Add a new product.");
        System.out.println("2. Remove an existing product.");
        System.out.println("3. Update an existing product.");
        System.out.println("4. Show the products registered in the system.");
        System.out.println(" ");
        System.out.println("0. Close the program.");
        System.out.println(" ");
        System.out.println("Enter your desired option: ");
    }

    public void applicationMenu()
    {
        boolean isRunning = true;
        while (isRunning)
        {
            administratorOptions();
            int option;
            Scanner cin = new Scanner(System.in);
            option = cin.nextInt();
            switch (option)
            {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    showProducts();
                    break;
                case 0:
                    isRunning = false;
                    break;
            }
        }
    }

}
