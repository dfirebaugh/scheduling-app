package sample;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.ZoneId;
import java.util.Locale;

public class LoginScene extends Scene {
    private String language;
    private String username;
    private String password;

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginBtn;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label zoneIdLabel;
    private Text errorMsgText;

    private String errorMsgString;
    private String usernameLabelString;
    private String passwordLabelString;
    private String loginBtnLabel;
    private String zoneIdString;

    private String errInvalidPassword = "no password was entered";
    private String errInvalidPasswordFR = "aucun mot de passe n'a été saisi";
    private String errInvalidUsername = "no username was entered";
    private String errInvalidUsernameFR = "aucun nom d'utilisateur n'a été saisi";
    private String errInvalidCredentials = "invalid credentials";
    private String errInvalidCredentialsFR = "les informations d'identification invalides";

    public LoginScene(GridPane parent) {
        super(parent);
        parent.setMinWidth(300);
        parent.setMinHeight(275);

        initLabels();
        initNodes(parent);
    }

    private void initNodes(GridPane parent) {
        usernameField = setupUserNameField();
        passwordField = setupPasswordField();
        loginBtn = setupLoginBtn();
        zoneIdLabel = new Label(zoneIdString);
        usernameLabel = new Label(usernameLabelString);
        passwordLabel = new Label(passwordLabelString);
        errorMsgText = new Text(errorMsgString);

        parent.setAlignment(Pos.CENTER);
        parent.add(usernameLabel, 0,0);
        parent.add(usernameField, 0,1);
        parent.add(passwordLabel, 0,2);
        parent.add(passwordField, 0,3);
        parent.add(loginBtn, 0, 4);
        parent.add(errorMsgText, 0, 5);

        parent.setHalignment(zoneIdLabel, HPos.LEFT);
        parent.add(zoneIdLabel, 0,20 );
    }

    private void initLabels() {
        language = Locale.getDefault().getLanguage();

        if (language == "en") {
            setEnglishLabels();
        } else if (language == "fr") {
            setFrenchLabels();
        } else {
            errorMsgString = "unsupported language";
            setEnglishLabels();
        }

        errorMsgString = "";
    }

    private void setFrenchLabels() {
        zoneIdString = "identifiant de zone: " + ZoneId.systemDefault().toString();
        usernameLabelString = "Nom d'utilisateur: ";
        passwordLabelString = "le mot de passe: ";
        loginBtnLabel = "connexion";
    }

    private void setEnglishLabels() {
        zoneIdString = "ZoneId: " + ZoneId.systemDefault().toString();
        usernameLabelString = "username: ";
        passwordLabelString = "password: ";
        loginBtnLabel = "login";
    }

    private TextField setupUserNameField() {
        TextField textField = new TextField();
        textField.setOnKeyReleased(e -> {
            this.username = usernameField.getText();
            clearErrorMsg();
        });


        return textField;
    }
    private PasswordField setupPasswordField() {
        PasswordField textField = new PasswordField();
        textField.setOnKeyReleased(e -> {
            this.password = passwordField.getText();
            clearErrorMsg();
        });


        return textField;
    }
    private Button setupLoginBtn() {
        var loginBtn = new Button();
        loginBtn.setText(loginBtnLabel);
        loginBtn.setOnAction(e -> {
            System.out.println("processing login...");
            System.out.println("username: " + this.username);
            System.out.println("password: " + this.password);

            evaluateCredentials();
        });

        return loginBtn;
    }

    private void clearErrorMsg() {
        errorMsgString = "";
    }

    private void evaluateCredentials() {
        if (language == "fr") {
            if (username == null) {
                errorMsgString = errInvalidUsernameFR;
                return;
            }
            if (password == null) {
                errorMsgString = errInvalidPasswordFR;
                return;
            }
            if (false) {
                errorMsgString = errInvalidCredentialsFR;
                return;
            }
            return;
        }

        if (username == null) {
            errorMsgString = errInvalidUsername;
            return;
        }
        if (password == null) {
            errorMsgString = errInvalidPassword;
            return;
        }
        if (false) {
            errorMsgString = errInvalidCredentials;
            return;
        }
    }
}
