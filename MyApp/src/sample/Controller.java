package sample;
//fx:controller="sample.Controller"

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller{

    ToggleGroup group = new ToggleGroup();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton ClientButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    private RadioButton MasterButton;

    @FXML
    private TextField Name_field;

    @FXML
    private Label SignInError;

    @FXML
    private TextField Surname_field;

    @FXML
    private TextField Telephone_field;

    @FXML
    private Button authSignInButton;

    @FXML
    void initialize() {

        ClientButton.setToggleGroup(group);
        MasterButton.setToggleGroup(group);

        authSignInButton.setOnAction(event -> { //Вход условия
            RadioButton selection  = (RadioButton) group.getSelectedToggle();

            String nameText = Name_field.getText().trim();
            String surnameText = Surname_field.getText().trim();
            String telephoneText = Telephone_field.getText().trim();


            if(!nameText.equals("")&&!surnameText.equals("")&&!telephoneText.equals("")&&selection!=null)
                if(selection.getText().equals("Клиент"))
                authSignInUser(nameText, surnameText, telephoneText);
                else
                    SignInError.setVisible(true);
            else
                SignInError.setVisible(true);

        });
        LoginSignUpButton.setOnAction(event -> { //Регистрация

            LoginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/app_client_reg.fxml"));
            try{
                loader.load();
            }catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void authSignInUser(String nameText, String surnameText, String telephoneText) { //Выполнение входа

        DatabaseHandler dbHandler = new DatabaseHandler(); // Проверка входа
        Users user = new Users();
        user.setName(nameText);
        user.setSurname(surnameText);
        user.setTelephone(telephoneText);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        if (result != null) {
            while (true) {
                try {
                    if (!result.next()) {
                        break;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                counter++;
            }
            if (counter >= 1) {
                authSignInButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/sample/client_home_page.fxml"));
                try {
                    loader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();


            } else SignInError.setVisible(true);
        } else {
            SignInError.setVisible(true);
        }
    }
}

