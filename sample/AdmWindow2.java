package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class AdmWindow2 {
    @FXML
    private Button canclebtn;
    @FXML
    private Button addbtn;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lifeCol.setCellValueFactory(new PropertyValueFactory<>("life"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tablerefresh();
        addbtn.setOnAction(event -> {
            if (!(medName.getText().isEmpty() || medLife.getText().isEmpty()
                    || medCount.getText().isEmpty() || medDescription.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                            "w9", "Qw12345678")){
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT medicines(medicineName, medicineLife, medicineCount, medicineDescription, userId) VALUES(?, ?, ?, ?, ?)");
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM medicines");
                        while(resultSet.next()){
                            if (resultSet.getString("medicineName").equals(medName.getText()) &&
                                    resultSet.getInt("userId") == Controller.userID) {
                                statement = conn.prepareStatement
                                        ("UPDATE medicines SET medicineName = ?, medicineLife = ?, " +
                                                "medicineCount = ?, medicineDescription = ? WHERE userId = ?");
                                break;
                            }
                        }
                        statement.setString(1, medName.getText());
                        statement.setString(2, medLife.getText());
                        statement.setString(3, medCount.getText());
                        statement.setString(4, medDescription.getText());
                        statement.setInt(5, Controller.userID);
                        statement.executeUpdate();
                    }
                }
                catch (Exception e) {
                    System.out.println("Ошибка в БД");
                }
            }
            tablerefresh();
        });
        FXMLLoader loader = new FXMLLoader();

        canclebtn.setOnAction(event -> {
            loader.setLocation(getClass().getResource("adminWindow.fxml"));
            canclebtn.getScene().getWindow().hide();
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
}
