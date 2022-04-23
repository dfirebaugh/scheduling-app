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

    public static final String AddCustomerOperation = "Add Customer";
    public static final String ModifyCustomerOperation = "Modify Customer";

    @FXML
    private Label titleLabel;
    @FXML
    private TextField idField;
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

    public CustomerScene(GridPane gridPane, SceneController sceneController, CustomerService customerService,
            DivisionService divisionService, CountryService countryService) {
        super(fxmlFilePath, gridPane, sceneController, customerService, divisionService, countryService);

        populateCountryComboBox();
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
        divisionComboBox.getItems()
                .addAll(divisionService.get(selectedCountry.getID()).stream().collect(Collectors.toList()));
    }

    public void handleClose() {
        this.sceneManger.switchToHome();
    }

    public void handleSave() {
        this.sceneManger.switchToHome();
        if (customer != null)
            customer.print();

        Logger.info("customer " + operationType + " operation");

        if (operationType == AddCustomerOperation)
            handleAdd();

        if (operationType == ModifyCustomerOperation)
            handleModify();
    }

    public void handleCountryUpdate() {
        populateDivisionComboBox();
    }

    private void handleAdd() {
        Division selected = divisionComboBox.getValue();
        Customer toAdd = new Customer(nameField.getText(), addressField.getText(), postalCodeField.getText(),
                phoneField.getText(), selected.getId());
        toAdd.print();
        this.customerService.add(toAdd);
    }

    private void handleModify() {
        Customer toModify = new Customer(
            customer.getId(), 
            nameField.getText(), 
            addressField.getText(),
            postalCodeField.getText(),
            phoneField.getText(),
            divisionComboBox.getSelectionModel().getSelectedItem().getId()
            );
        toModify.print();
        this.customerService.update(toModify);
    }

    public void setCurrentCustomer(Customer customer, String operationType) {
        Logger.info(operationType);

        this.operationType = operationType;

        titleLabel.setText(operationType);
        this.customer = customer;
        if (operationType == AddCustomerOperation)
            clear();
        if (operationType == ModifyCustomerOperation)
            populateExistingCustomer(customer);
    }

    private void populateExistingCustomer(Customer customer) {
        idField.setText(customer.getId().toString());
        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        postalCodeField.setText(customer.getPostalCode());
        phoneField.setText(customer.getPhone());
        countryComboBox.setValue(countryService.get(divisionService.getOne(customer.getDivisionID()).getCountryID()));
        divisionComboBox.setValue(divisionService.getOne(customer.getDivisionID()));
    }

    public void clear() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        postalCodeField.setText("");
        phoneField.setText("");
        populateCountryComboBox();
        divisionComboBox.setValue(null);
    }
}
