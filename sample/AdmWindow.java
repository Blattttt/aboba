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
    @FXML private TableView<BD> medicineTable;
    @FXML private TableColumn<BD, String> Name;
    @FXML private TableColumn<BD, String> LastName;
    @FXML private TableColumn<BD, String> Age;
    @FXML private TableColumn<BD, String> UserRole;
    @FXML private TableColumn<BD, String> EmailAddress;
    @FXML private TableColumn<BD, String> Office;
    private final ObservableList<BD> medicineData = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        UserRole.setCellValueFactory(new PropertyValueFactory<>("UserRole"));
        EmailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        Office.setCellValueFactory(new PropertyValueFactory<>("Office"));

        FXMLLoader loader = new FXMLLoader();

        tablerefresh();




        exitAdm1.setOnAction(event ->
                exitAdm1.getScene().getWindow().hide());

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
        medicineData.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                    "w9", "Qw12345678")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){
                        medicineData.add(new BD(
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Birthdate"),
                                resultSet.getString("RoleID"),
                                resultSet.getString("Email"),
                                resultSet.getString("OfficeID")));
                }
            }
            if (!medicineData.isEmpty()){
                medicineTable.setItems(medicineData);
            }

        }catch (Exception e){
            System.out.println("Ошибка в БД");
        }
    }
}
