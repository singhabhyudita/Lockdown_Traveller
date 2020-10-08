package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label noAccountLabel, signinLabel;
    @FXML
    private AnchorPane signinPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton, signupButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
    }

    public void switchToSignup(ActionEvent actionEvent) {
        FXMLLoader signupLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Stage stage = (Stage) signupButton.getScene().getWindow();
        Scene signupScene = null;
        try {
            signupScene = new Scene(signupLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(signupScene);
    }
}