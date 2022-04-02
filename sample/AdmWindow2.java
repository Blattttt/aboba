package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdmWindow2 {

    @FXML
    private Button canclebtn;
    @FXML
    private Button addbtn;
    @FXML private TextField officefield;
    @FXML private TextField emailfield;
    @FXML private TextField fname;
    @FXML private TextField lnamefield;
    @FXML private TextField passwordfield;
    @FXML private TextField birtfield;

    @FXML
    private void initialize() {
        addbtn.setOnAction(event -> {
            if (!(emailfield.getText().isEmpty() || passwordfield.getText().isEmpty()
                    || fname.getText().isEmpty() || lnamefield.getText().isEmpty()|| officefield.getText().isEmpty()||birtfield.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                            "w9", "Qw12345678")){
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into users(RoleID,Email,Password,FirstName,LastName,OfficeID,Birthdate,Active) VALUES(?,?,?,?,?,?,?,?)");
                        statement.setString(1, "2");
                        statement.setString(2, emailfield.getText());
                        statement.setString(3, passwordfield.getText());
                        statement.setString(4, fname.getText());
                        statement.setString(5, lnamefield.getText());
                        statement.setString(6, officefield.getText());
                        statement.setString(7, birtfield.getText());
                        statement.setString(8, "1");
                        statement.executeUpdate();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
