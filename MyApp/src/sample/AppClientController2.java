package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AppClientController2 {

    public static String last_data;
    public static String next_data;
    public static String master;

    public static Integer mID;
    public static String mname;
    public static String msurname;
    public static String mtelephone;

    public static LocalDate lastt_data;
    public static LocalDate nextt_data;
    public static String Comment;


    @FXML
    private Button BackButton;

    @FXML
    private DatePicker LastDataPicker;

    @FXML
    private DatePicker NextDataPicker;

    @FXML
    private Button MainButton;

    @FXML
    private ChoiceBox<String> MastersButton;

    @FXML
    private TextArea Comment_textArea;

    @FXML
    private Label SignInError;

    @FXML
    private Button SingUpButton;

    public static ObservableList<String> list = FXCollections.observableArrayList();
    public static ArrayList<Integer> index = new ArrayList<>();

    @FXML
    void initialize() {

        DatabaseHandler dbHandlerr = new DatabaseHandler();
        dbHandlerr.getMasters();

        MastersButton.getItems().addAll(list); //Список мастеров
        NextDataPicker.getEditor().setDisable(true);//Блокировка ввода даты руками
        LastDataPicker.getEditor().setDisable(true);//Блокировка ввода даты руками

        MastersButton.getSelectionModel().selectedIndexProperty().addListener(//Возвращение выбранного мастера
                new ChangeListener<Number>() {
                    // if items of the list are changed
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        // text for the label to the selected item
                        master = list.get(new_value.intValue());
                        String[] parts = master.split(" ");
                        mname = parts[0];
                        msurname  = parts[1];
                        mtelephone = "";
                        for (int i = 2; i<parts.length; i++ ) mtelephone = mtelephone + parts[i];
                        mID = index.get(list.indexOf(master));
                    }
                });

        NextDataPicker.setDayCellFactory(picker -> new DateCell() {//Блокировка предыдущих дат
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        LastDataPicker.setDayCellFactory(picker -> new DateCell() {//Блокировка предыдущих дат
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                if (Main.Record == 0) {setDisable(empty || date.compareTo(today) < 0 );}
                else
                setDisable(empty || date.compareTo(today) > 0 );
            }
        });

        EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {//Получение даты регистрации
            public void handle(ActionEvent e)
            {
                // get the date picker value
                nextt_data = NextDataPicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                next_data = nextt_data.format(formatter);

            }
        };
        NextDataPicker.setOnAction(ev);

        EventHandler<ActionEvent> evv = new EventHandler<ActionEvent>() {//Получение даты последнего ТО
            public void handle(ActionEvent e)
            {
                // get the date picker value
                lastt_data = LastDataPicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);
                last_data = lastt_data.format(formatter);

            }
        };
        LastDataPicker.setOnAction(evv);
        if(Main.Record == 0){
                LastDataPicker.setValue(LocalDate.parse(last_data)); //Дата последнего ТО
                SingUpButton.setOnAction(event -> { //Вход условия
                    Comment = Comment_textArea.getText().trim();
                    if(!next_data.equals("")&&!Comment.equals("")&&!master.equals("")) {
                        SetUpNewRecord(Comment);
                    }else SignInError.setVisible(true);
                });

        }else{
             SingUpButton.setOnAction(event -> { //Вход условия
                 Comment = Comment_textArea.getText().trim();
                 if(!next_data.equals("")&&!Comment.equals("")&&!master.equals("")) {
                     setUpNewUser(Comment);
                 }else SignInError.setVisible(true);
                });
        }

        BackButton.setOnAction(event -> { //Назад
            list.clear();
            FXMLLoader loader = new FXMLLoader();
            if(Main.Record == 0){
                DatabaseHandler dbHandler = new DatabaseHandler(); // Проверка входа
                Users user = new Users();
                user.setName(ClientHomePage.name);
                user.setSurname(ClientHomePage.surname);
                user.setTelephone(ClientHomePage.telephone);
                dbHandler.getUser(user);

                BackButton.getScene().getWindow().hide();

                loader.setLocation(getClass().getResource("/sample/client_home_page.fxml"));

            }
            else{

                DatabaseHandler DH = new DatabaseHandler();
                DH.Delete();
                Main.back+=1;
                BackButton.getScene().getWindow().hide();

                loader.setLocation(getClass().getResource("/sample/app_client_reg.fxml"));
            }
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

        MainButton.setOnAction(event -> { //На главную
            list.clear();
            MainButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            if (Main.Record == 1) {
                DatabaseHandler DH = new DatabaseHandler();
                DH.Delete();
                Main.back+=1;
            }
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

    public void setMaster(Integer MID, String Mname, String Msurname, String Mtelephone){
        mID = MID;
        mname = Mname;
        msurname = Msurname;
        mtelephone = Mtelephone;

        index.add(mID);

        list.add(Mname+" "+Msurname+" "+Mtelephone);
    }

    public void setLastDataPicker(String data){last_data = data;}

    public void setUpNewUser(String Comment){

        FXMLLoader loader = new FXMLLoader();
        DatabaseHandler dbHandler = new DatabaseHandler(); // Проверка входа
        Users user = new Users();
        user.setName(AppClientController1.name);
        user.setSurname(AppClientController1.surname);
        user.setTelephone(AppClientController1.telephone);

        dbHandler.singUpCheck();
        DiagnosticCard diagnosticCard = new DiagnosticCard();
        diagnosticCard.setMID(mID);
        dbHandler.singUpDiagnosticCard(diagnosticCard);
        dbHandler.singUpNewUser(next_data, mID, Comment);
        dbHandler.singUpTransportService(last_data);

        dbHandler.getUser(user);

        SingUpButton.getScene().getWindow().hide();
        loader.setLocation(getClass().getResource("/sample/client_home_page.fxml"));
        list.clear();
        index.clear();

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

    public void SetUpNewRecord(String Comment){

        FXMLLoader loader = new FXMLLoader();
        DatabaseHandler dbHandler = new DatabaseHandler();
        Users user = new Users();
        user.setName(ClientHomePage.name);
        user.setSurname(ClientHomePage.surname);
        user.setTelephone(ClientHomePage.telephone);

        dbHandler.singUpCheck();
        DiagnosticCard diagnosticCard = new DiagnosticCard();
        diagnosticCard.setMID(mID);
        dbHandler.singUpDiagnosticCard(diagnosticCard);
        dbHandler.singUpClientRecord(next_data, ClientHomePage.uID, ClientHomePage.tID, ClientHomePage.tsID, mID, Comment);

        dbHandler.getUser(user);

        list.clear();
        index.clear();

        SingUpButton.getScene().getWindow().hide();
        loader.setLocation(getClass().getResource("/sample/client_home_page.fxml"));

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
