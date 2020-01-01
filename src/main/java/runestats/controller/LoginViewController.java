package runestats.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import runestats.model.GameMode;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML private TextField tfUsername;

    @FXML private Button bLogin;

    @FXML private ComboBox<GameMode> cbGamemode; // disabledProp

    @FXML private CheckBox cbAddNewAccount; // selectedProp

    public LoginViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize");
        cbGamemode.getItems().addAll(Arrays.asList(GameMode.values()));

        cbGamemode.setDisable(true);
        cbGamemode.disableProperty().bind(cbAddNewAccount.selectedProperty().not());

        bLogin.textProperty().bind(Bindings.createStringBinding(
                () -> cbAddNewAccount.isSelected() ? "Add account" : "Login", cbAddNewAccount.selectedProperty()));
    }
}
