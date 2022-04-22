package scenes;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Customer;
import models.Division;
import services.CustomerService;
import services.DivisionService;
import services.Logger;

public class CustomerScene extends AbstractScene {
    private static final String fxmlFilePath = "Customer.fxml";
    private String mode;
    private Customer customer;

    private final String addCustomerOperation = "Add Customer";
    private final String modifyCustomerOperation = "Modify Customer";

    @FXML private Label titleLabel;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<Division> divisionComboBox;

    public CustomerScene(GridPane p, SceneController sm, CustomerService cs, DivisionService ds, String m) {
        super(p, sm, cs, ds, fxmlFilePath);

        populateDivisionComboBox();

        mode = m;
        titleLabel.setText(mode);
    }

    public CustomerScene(GridPane p, SceneController sm, CustomerService cs, DivisionService ds, String m, Customer c) {
        super(p, sm, cs, ds, fxmlFilePath);

        customer = c;

        populateDivisionComboBox();

        mode = m;
        titleLabel.setText(mode);
    }



    private void populateDivisionComboBox() {
        divisionComboBox.getItems().clear();
        divisionComboBox.getItems().addAll(divisionService.get().stream().collect(Collectors.toList()));
    }


    public void handleClose() {
        this.sceneManger.switchToHome();
    }

    public void handleSave() {
        this.sceneManger.switchToHome();
        if (customer != null)
            customer.print();

        Logger.info("customer " + mode + " operation");

        if (mode == addCustomerOperation)
            handleAdd();

        if (mode == modifyCustomerOperation)
            handleModify();
    }

    private void handleAdd() {
        Division selected = divisionComboBox.getValue();
        Logger.info(selected.toString());
        Logger.info("id: " + selected.getId());

        Customer toAdd = new Customer(nameField.getText(),
                addressField.getText(),
                postalCodeField.getText(),
                phoneField.getText(),
                selected.getId());
        toAdd.print();
        this.customerService.add(toAdd);
    }

    private void handleModify() {
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
