package org.example.agency;

import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import domain.Product;
import repository.DuplicateObjectException;
import repository.RepositoryException;
import repository.IRepository;
import repository.UserRepository;
import repository.SQLUserRepository;
import service.ProductService;
import service.UserService;
import ui.UI;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Collection;

public class HelloController {
    ObservableList<String> list = FXCollections.observableList(new ArrayList<>());

    @FXML
    public ListView productsListMessage, usersListMessage, stockListMessage;

    @FXML
    public Button productsOverviewP, productsOverviewU;

    @FXML
    public Button usersOverviewP, usersOverviewU;

    @FXML
    public Button showProducts, showUsers, showStock, addProduct, addUser, removeProduct, removeUser, updateProduct, updateUser, orderProduct;

    @FXML
    public TextField productId, userId, stockId;

    @FXML
    public TextField productName, userUsername, userPassword, userAdministrator;

    @FXML
    public TextField productPrice, productQuantity, stockQuantity;

    @FXML
    public TextField loginUsername, loginPassword;

    @FXML
    public TextArea productsStatusInformation, usersStatusInformation, stockStatusInformation;

    @FXML
    public Tab productsTab, usersTab, loginTab, basicTab;

    @FXML
    public TabPane mainTabPane;

    ProductService productServiceFX = new ProductService();
    UserService userServiceFX = new UserService();

    @FXML
    public void getAllProducts(ActionEvent actionEvent) throws RepositoryException {
        productsListMessage.getItems().clear();
        ProductService productService = new ProductService();
        Collection<Product> productList = productService.getAllProducts();
        for (Product product:productList) {
            list.add(product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getQuantity());
        }
        productsListMessage.setItems(list);
        productsStatusInformation.setText("Product list refreshed successfully.");
    }

    @FXML
    public void getAllStock(ActionEvent actionEvent) throws RepositoryException {
        stockListMessage.getItems().clear();
        ProductService productService = new ProductService();
        Collection<Product> productList = productService.getAllProducts();
        for (Product product:productList) {
            list.add(product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getQuantity());
        }
        stockListMessage.setItems(list);
        stockStatusInformation.setText("Product list refreshed successfully.");
    }

    @FXML
    public void getAllUsers(ActionEvent actionEvent) throws RepositoryException {
        usersListMessage.getItems().clear();
        UserService userService = new UserService();
        Collection<User> userList = userService.getAllUsers();
        for (User user:userList) {
            list.add(user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getAdmin());
        }
        usersListMessage.setItems(list);
        usersStatusInformation.setText("User list refreshed successfully.");
    }

    @FXML
    public void addProduct(ActionEvent actionEvent) throws RepositoryException {
        ProductService productService = new ProductService();
        int id, price, quantity;
        String name;
        try
        {
            id = Integer.parseInt(productId.getText());
            name = productName.getText();
            price = Integer.parseInt(productPrice.getText());
            quantity = Integer.parseInt(productQuantity.getText());
            productServiceFX.addProduct(id, name, price, quantity);
            usersStatusInformation.setText("Product added successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void addUser(ActionEvent actionEvent) throws RepositoryException {
        UserService userService = new UserService();
        int id;
        String username, password;
        boolean admin = false;
        try
        {
            id = Integer.parseInt(userId.getText());
            username = userUsername.getText();
            password = userPassword.getText();
            if (userAdministrator.getText().equals("true"))
            {
                admin = true;
            }
            userServiceFX.addUser(id, username, password, admin);
            usersStatusInformation.setText("User added successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void removeProduct(ActionEvent actionEvent) throws RepositoryException {
        ProductService productService = new ProductService();
        int id;
        try
        {
            id = Integer.parseInt(productId.getText());
            productServiceFX.removeProduct(id);
            usersStatusInformation.setText("Product removed successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void removeUser(ActionEvent actionEvent) throws RepositoryException {
        UserService userService = new UserService();
        int id;
        try
        {
            id = Integer.parseInt(userId.getText());
            userServiceFX.removeUser(id);
            usersStatusInformation.setText("User removed successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void updateProduct(ActionEvent actionEvent) throws RepositoryException {
        ProductService productService = new ProductService();
        int id, price, quantity;
        String name;
        try
        {
            id = Integer.parseInt(productId.getText());
            name = productName.getText();
            price = Integer.parseInt(productPrice.getText());
            quantity = Integer.parseInt(productQuantity.getText());
            productServiceFX.updateProduct(id, id, name, price, quantity);
            usersStatusInformation.setText("Product updated successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void updateUser(ActionEvent actionEvent) throws RepositoryException {
        UserService userService = new UserService();
        int id;
        String username, password;
        boolean admin = false;
        try
        {
            id = Integer.parseInt(userId.getText());
            username = userUsername.getText();
            password = userPassword.getText();
            if (userAdministrator.getText().equals("true"))
            {
                admin = true;
            }
            userServiceFX.updateUser(id, id, username, password, admin);
            usersStatusInformation.setText("User updated successfully.");
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void orderProduct(ActionEvent actionEvent) throws RepositoryException {
        ProductService productService = new ProductService();
        int givenId, givenQuantity, quantity;
        try
        {
            givenId = Integer.parseInt(stockId.getText());
            givenQuantity = Integer.parseInt(stockQuantity.getText());
            quantity = productServiceFX.getById(givenId).getQuantity();
            System.out.println("Product stock: "+quantity+" ");
            System.out.println("Order quantity: "+givenQuantity+" ");
            if (quantity >= givenQuantity)
            {
                System.out.println("Trying to order product...");
                productServiceFX.orderProduct(givenId, givenQuantity);
                stockStatusInformation.setText("Product ordered successfully.");
                System.out.println("Product successfully ordered.");
            }
            else
            {
                System.out.println("Insufficient product stock.");
                stockStatusInformation.setText("Insufficient stock quantity.");
            }
        }
        catch (NumberFormatException numberFormatException) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            hello.show();
        }
    }

    @FXML
    public void showProductsOverview(ActionEvent actionEvent) {
        mainTabPane.getSelectionModel().select(productsTab);
    }

    @FXML
    public void showUsersOverview(ActionEvent actionEvent) {
        mainTabPane.getSelectionModel().select(usersTab);
    }

    @FXML
    public void loginAccount(ActionEvent actionEvent) throws RepositoryException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        UserService userService = new UserService();
        Collection<User> userList = userService.getAllUsers();
        for (User user:userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                if (user.getAdmin())
                {
                    mainTabPane.getSelectionModel().select(usersTab);
                }
                else
                {
                    mainTabPane.getSelectionModel().select(basicTab);
                }
            }
        }
        loginUsername.clear();
        loginPassword.clear();
    }
}