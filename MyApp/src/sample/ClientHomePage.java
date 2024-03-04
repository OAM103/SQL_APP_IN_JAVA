package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientHomePage {

    public static Integer uID;
    public static Integer tID;
    public static Integer tsID;

    public static String name;
    public static String surname;
    public static String telephone;
    public static String address;

    public String data;
    public String master;
    public String mas_tel;

    public String end_data;
    public String price;

    public static String number;
    public static String model;
    public static String color;
    public static String year;

    public String comments;
    public String recommendations;
    public static String next_data;
    public static int count = 0;

    @FXML
    private ChoiceBox<String> SearchBox;

    @FXML
    private TextField Search_field;

    @FXML
    private Label Address_label;

    @FXML
    private Label Color_label;

    @FXML
    private Label Data_TO;

    @FXML
    private TableColumn<RecordTable, String> Data_end_col;

    @FXML
    private TableColumn<RecordTable, String> Data_reg_col;

    @FXML
    private Button MainButton;

    @FXML
    private TableColumn<RecordTable, String> Master_col;

    @FXML
    private Label Model_label;

    @FXML
    private Label Name_label;

    @FXML
    private TableColumn<RecordTable, String> Number_col1;

    @FXML
    private Label Number_label;

    @FXML
    private AnchorPane Pane;

    @FXML
    private TableColumn<RecordTable, String> Price_col;

    @FXML
    private TableView<RecordTable> REG_TAB;

    @FXML
    private Button SingUpButton;

    @FXML
    private Label Surname_label;

    @FXML
    private TableColumn<RecordTable, String> Telephone_col;

    @FXML
    private Label Telephone_label;

    @FXML
    private TableColumn<RecordTable, String> Work_col;

    @FXML
    private Label Year_label;

    @FXML
    private Label Recommendation_label;

    public static ObservableList<RecordTable> list = FXCollections.observableArrayList();

    public void getAllUser(Integer UID, String client_name, String client_surname, String client_telephone, String client_address,
                           Integer TID,String transport_number, String transport_model, String transport_color, String transport_year,
                           Integer TSID,String master_name, String master_surname, String master_tel, String record_data,
                           String check_data, String check_price, String Comments, String recommendation, String nextData){

        uID = UID;
        tID = TID;
        tsID = TSID;

        name = client_name;
        surname = client_surname;
        telephone = client_telephone;
        address = client_address;


        number = transport_number;
        model = transport_model;
        color = transport_color;
        year = transport_year;

        data = record_data;

        end_data = check_data;
        price = check_price;

        comments = Comments;
        recommendations = recommendation;
        next_data = nextData;

        count+=1;

        master = master_name+" "+master_surname;
        mas_tel = master_tel;

        if (!check_data.equals("2000-01-01")) end_data = check_data;
        else end_data = "Не завершено";
        if (Comments != null) comments = Comments;
        else comments = "Информация отсутствует";
        if (check_price != null)price = check_price;
        else price = "Информация отсутствует";

       setList(count,master, mas_tel, data, end_data, comments,price);

    }

    public void setList(int number,String master, String mas_tel, String data, String end_data, String comments,String price){
        list.add(new RecordTable(number,master, mas_tel, data, end_data, comments,price));
    }

    ObservableList<RecordTable> getList(){
        return list;
    }

    @FXML
    void initialize() {
        Main.Record = 0;

        Number_col1.setCellValueFactory(new PropertyValueFactory<>("number"));
        Master_col.setCellValueFactory(new PropertyValueFactory<>("master"));
        Telephone_col.setCellValueFactory(new PropertyValueFactory<>("mas_tel"));
        Data_reg_col.setCellValueFactory(new PropertyValueFactory<>("data"));
        Data_end_col.setCellValueFactory(new PropertyValueFactory<>("end_data"));
        Work_col.setCellValueFactory(new PropertyValueFactory<>("comments"));
        Price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        REG_TAB.setItems(getList());

        SearchBox.getItems().addAll("Номер", "Мастер", "Телефон","Дата записи","Дата завершения","Работа", "Цена");
        FilteredList<RecordTable> filter = new FilteredList<RecordTable>(list, p -> true);

        Search_field.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (SearchBox.getValue())//Switch on choiceBox value
            {
                case "Мастер" -> {
                    filter.setPredicate(p -> p.getMaster().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
                case "Телефон" -> {
                    filter.setPredicate(p -> p.getMas_tel().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
                case "Дата записи" -> {
                    filter.setPredicate(p -> p.getData().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
                case "Дата завершения" -> {
                    filter.setPredicate(p -> p.getEnd_data().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
                case "Работа" -> {
                    filter.setPredicate(p -> p.getComments().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
                case "Цена" -> {
                    filter.setPredicate(p -> p.getPrice().toLowerCase().contains(newValue.toLowerCase().trim()));
                    REG_TAB.setItems(filter);
                }
            }
        });

        SearchBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                Search_field.setText("");
            }
        });


        Name_label.setText(name);
        Surname_label.setText(surname);
        Telephone_label.setText(telephone);
        Address_label.setText(address);

        Number_label.setText(number);
        Model_label.setText(model);
        Color_label.setText(color);
        Year_label.setText(year);

        Data_TO.setText(next_data);

        if (recommendations != null) Recommendation_label.setText(recommendations);
        else Recommendation_label.setText("Информация отсутствует");

        SingUpButton.setOnAction(event -> { //Регестрация
            list.clear();
            count = 0;
            FXMLLoader loader = new FXMLLoader();
            SingUpButton.getScene().getWindow().hide();
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

        });

        MainButton.setOnAction(event -> { //На главную
            list.clear();
            count = 0;

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



}

