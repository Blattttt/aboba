package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField passwordfield;
    @FXML
    private Button loginbtn;
    @FXML
    private Button exitbtn;

    public static int userID;

    public String username;
    public String user;

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader();


        exitbtn.setOnAction(event ->
                exitbtn.getScene().getWindow().hide());

        loginbtn.setOnAction(event -> {
            try {
                String username = usernamefield.getText();
                String password = passwordfield.getText();
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                        "w9", "Qw12345678")) {
                    System.out.println("Подключение к бд");
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                    while (resultSet.next()) {
                        if (resultSet.getString("Email").equals(username) &&
                                resultSet.getString("Password").equals(password) &&
                                resultSet.getString("RoleID").equals("1")) {
                            System.out.println("Успешный вход admin");
                            userID = resultSet.getInt("id");
                            loginbtn.getScene().getWindow().hide();
                            loader.setLocation(getClass().getResource("adminWindow.fxml"));
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
                            break;
                        } else if (resultSet.getString("Email").equals(username) &&
                                resultSet.getString("Password").equals(password)) {
                            System.out.println("Успешный вход user");
                            user = resultSet.getString("FirstName");
                            Get.setUserName(user);
                            userID = resultSet.getInt("id");
                            loginbtn.getScene().getWindow().hide();
                            loader.setLocation(getClass().getResource("userWindow.fxml"));
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
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Ошибка доступа к БД");
            }
        });
    }
}