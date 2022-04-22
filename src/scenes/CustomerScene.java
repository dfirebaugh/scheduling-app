package scenes;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Customer;
import models.Division;
import models.Country;
import services.CustomerService;
import services.DivisionService;
import services.CountryService;
import services.Logger;

public class CustomerScene extends AbstractScene {
    private static final String fxmlFilePath = "Customer.fxml";
    private Customer customer;

    private final String addCustomerOperation = "Add Customer";
    private final String modifyCustomerOperation = "Modify Customer";

    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private ComboBox<Country> countryComboBox;

    public CustomerScene(GridPane gridPane, SceneController sceneController, CustomerService customerService, DivisionService divisionService, CountryService countryService) {
        super("Customer.fxml", gridPane, sceneController, customerService, divisionService, countryService);

        populateCountryComboBox();
        populateDivisionComboBox();
    }

    private void populateCountryComboBox() {
        countryComboBox.getItems().clear();
        countryComboBox.getItems().addAll(countryService.get().stream().collect(Collectors.toList()));
    }
    private void populateDivisionComboBox() {
        Country selectedCountry = countryComboBox.getValue();
        if (selectedCountry == null) {
            return;
        }
        divisionComboBox.getItems().clear();
        divisionComboBox.getItems().addAll(divisionService.get(selectedCountry.getID()).stream().collect(Collectors.toList()));
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

    public void handleCountryUpdate() {
        populateDivisionComboBox();
    }

    private void handleAdd() {
        Division selected = divisionComboBox.getValue();
        Logger.info(selected.toString());
        Logger.info("id: " + selected.getId());

        Customer toAdd = new Customer(nameField.getText(), addressField.getText(), postalCodeField.getText(),
                phoneField.getText(), selected.getId());
        toAdd.print();
        this.customerService.add(toAdd);
    }

    private void handleModify() {
        Customer toModify = new Customer(customer.getId(), nameField.getText(), addressField.getText(),
                postalCodeField.getText(), phoneField.getText());
        toModify.print();
        this.customerService.update(toModify);
    }

    public void setCurrentCustomer(Customer customer) {
        this.customer = customer;
    }

    public void clear() {
        setCurrentCustomer(new Customer());
        titleLabel.setText("");
        nameField.setText("");
        addressField.setText("");
        postalCodeField.setText("");
        phoneField.setText("");
        phoneField.setText("");
        populateDivisionComboBox();
    }
}
