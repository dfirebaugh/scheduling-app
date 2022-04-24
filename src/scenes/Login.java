package scenes;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.Logger;
import services.UserService;

import java.time.ZoneId;
import java.util.Locale;

import datastore.UserStore;

public class Login extends Scene {
    private SceneController sceneManger;
    private UserService userService;
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

    private static final String errInvalidPassword = "no password was entered";
    private static final String errInvalidPasswordFR = "aucun mot de passe n'a été saisi";
    private static final String errInvalidUsername = "no username was entered";
    private static final String errInvalidUsernameFR = "aucun nom d'utilisateur n'a été saisi";
    private static final String errInvalidCredentials = "invalid credentials";
    private static final String errInvalidCredentialsFR = "les informations d'identification invalides";

    public Login(GridPane parent, SceneController sm, UserService us) {
        super(parent);
        userService = us;
        sceneManger = sm;
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
        errorMsgText.setFill(Color.RED);

        parent.setAlignment(Pos.CENTER);
        parent.add(usernameLabel, 0, 0);
        parent.add(usernameField, 0, 1);
        parent.add(passwordLabel, 0, 2);
        parent.add(passwordField, 0, 3);
        parent.add(loginBtn, 0, 4);
        parent.add(errorMsgText, 0, 5);

        parent.setHalignment(zoneIdLabel, HPos.LEFT);
        parent.add(zoneIdLabel, 0, 20);
        setupOnEnterHandler();
    }

    private void setupOnEnterHandler() {
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                evaluateCredentials();
            }
        });
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                evaluateCredentials();
            }
        });
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
            Logger.info("processing login...");

            evaluateCredentials();
        });

        return loginBtn;
    }

    private void clearErrorMsg() {
        errorMsgString = "";
    }

    private void setErrorMsg(String msg) {
        errorMsgString = msg;
        errorMsgText.setText(msg);
    }

    private void evaluateCredentials() {
        if (language == "fr") {
            if (username == null) {
                setErrorMsg(errInvalidUsernameFR);
                return;
            }
            if (password == null) {
                setErrorMsg(errInvalidPasswordFR);
                return;
            }
            if (this.userService.login(username, password)) {
                setErrorMsg("success");
                sceneManger.switchToHome();
                return;
            } else {
                setErrorMsg(errInvalidCredentialsFR);
            }
            return;
        }

        if (username == null) {
            setErrorMsg(errInvalidUsername);
            return;
        }
        if (password == null) {
            setErrorMsg(errInvalidPassword);
            return;
        }
        if (this.userService.login(username, password)) {
            setErrorMsg("success");
            sceneManger.switchToHome();
            return;
        } else {
            setErrorMsg(errInvalidCredentials);
        }
    }
}
