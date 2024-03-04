package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppClientController1 {
    public  static String name;
    public  static String surname;
    public  static String telephone;
    public  static String address;

    public  static String number;
    public  static String model;
    public  static String color;
    public  static String year;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Address_field;

    @FXML
    private TextField Color_field;

    @FXML
    private TextField Model_field;

    @FXML
    private TextField Name_field;

    @FXML
    private Button NextButton;

    @FXML
    private TextField Reg_number_field;

    @FXML
    private Label SignInError;

    @FXML
    private TextField Surname_field;

    @FXML
    private TextField Telephone_field;

    @FXML
    private TextField Year_field;

    @FXML
    private Button MainButton;

    @FXML
    void initialize() {

        NextButton.setOnAction(event ->{
            name = Name_field.getText();
            surname = Surname_field.getText();
            telephone = Telephone_field.getText();
            address = Address_field.getText();

            number = Reg_number_field.getText();
            model = Model_field.getText();
            color = Color_field.getText();
            year = Year_field.getText();

            if(!name.equals("")&&!surname.equals("")&&!telephone.equals("")&&!address.equals("")
                    &&!number.equals("") &&!model.equals("")&&!color.equals("") &&!year.equals("")) {
                Integer uID = 0;
                Integer tID = 0;
                Integer tsID = 0;
                singUpNewUser(uID, name, surname,telephone,address,tID,number,model,color,year,tsID);
            } else{
            SignInError.setVisible(true);
                }
        });
        MainButton.setOnAction(event -> { //На главную

            MainButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

    }

    private void singUpNewUser(Integer UID, String name, String surname,String telephone,String address,
                               Integer TID,String number,String model,String color,String year, Integer TSID) {//Регистрация пользователя
        Main.Record = 1;
        DatabaseHandler dbHandler = new DatabaseHandler();

        Users user = new Users(UID, name, surname, telephone, address);
        Transport transport = new Transport(TID, number,model,color,year,TSID);
        dbHandler.singUpUser(user, transport);

        NextButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/sample/app_client_reg2.fxml"));
        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

}
