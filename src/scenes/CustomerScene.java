package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Customer;
import services.CustomerService;
import services.Logger;

public class CustomerScene extends AbstractScene {
    private static final String fxmlFilePath = "Customer.fxml";
    private String mode;
    private Customer customer;

    @FXML private Label titleLabel;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;

    public CustomerScene(GridPane p, SceneController sm, CustomerService cs, String m) {
        super(p, sm, cs, fxmlFilePath);

        mode = m;
        titleLabel.setText(mode);
    }
    public CustomerScene(GridPane p, SceneController sm, CustomerService cs, String m, Customer c) {
        super(p, sm, cs, fxmlFilePath);

        customer = c;

        mode = m;
        titleLabel.setText(mode);
    }
    public void handleClose() {
        this.sceneManger.switchToHome();
    }
    public void handleSave() {
        this.sceneManger.switchToHome();
        if (customer != null)
            customer.print();

        if (mode == "add") {
            Customer toAdd = new Customer(nameField.getText(),
            addressField.getText(),
            postalCodeField.getText(),
            phoneField.getText());
            Logger.info("add operation on: ");
            toAdd.print();
            // this.customerService.add(toAdd);
        }
        if (mode == "modify") {
            Customer toModify = new Customer(
                customer.getId(),
                nameField.getText(),
                addressField.getText(),
                postalCodeField.getText(),
                phoneField.getText());
            toModify.print();
            this.customerService.update(toModify);
        }
    }
}
