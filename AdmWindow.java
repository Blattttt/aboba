package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdmWindow {
    @FXML private Button exitAdm1;
    @FXML private Button addUserAdm1;
    @FXML private Button changerolebtn;
    @FXML private TableView<BD> abonentsTable;
    @FXML private TableView<BDaction> actionstable;
    @FXML private TableColumn<BD, String> Name;
    @FXML private TableColumn<BDaction, String> Action;
    @FXML private TableColumn<BD, String> LastName;
    @FXML private TableColumn<BD, String> Age;
    @FXML private TableColumn<BD, String> UserRole;
    @FXML private TableColumn<BD, String> EmailAddress;
    @FXML private TableColumn<BD, String> Office;
    private final ObservableList<BD> abonentsData = FXCollections.observableArrayList();
    private final ObservableList<BDaction> actionsData = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        UserRole.setCellValueFactory(new PropertyValueFactory<>("UserRole"));
        EmailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));

        Action.setCellValueFactory(new PropertyValueFactory<>("Action"));

        FXMLLoader loader = new FXMLLoader();

        tablerefresh();

        exitAdm1.setOnAction(event -> {
                loader.setLocation(getClass().getResource("firstWindow.fxml"));
                exitAdm1.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.show();});

        addUserAdm1.setOnAction(event -> {
            loader.setLocation(getClass().getResource("adminWindow2.fxml"));
            addUserAdm1.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.show();
        });

        changerolebtn.setOnAction(event -> {
            loader.setLocation(getClass().getResource("adminWindow3.fxml"));
            changerolebtn.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.show();
        });

    }
    private void tablerefresh(){
        abonentsData.clear();
        actionsData.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                    "w9", "Qw12345678")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM abonents");
                Statement statement2 = conn.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM schedule");
                while(resultSet.next()){
                    abonentsData.add(new BD(
                                resultSet.getString("ФИО"),
                                resultSet.getString("телефон"),
                                resultSet.getString("Лицевой счет"),
                                resultSet.getString("Услуги"),
                                resultSet.getString("Номер договора")));
                }
                while(resultSet2.next()){
                    uo uo = new uo(resultSet2.getString(2));
                    actionsData.add(new BDaction(uo.getCheto()));
                }

            }
            if (!abonentsData.isEmpty()){
                abonentsTable.setItems(abonentsData);
            }

            if (!actionsData.isEmpty()){
                actionstable.setItems(actionsData);
            }

        }catch (Exception e){
            System.out.println("Ошибка в БД");
        }
    }
}
