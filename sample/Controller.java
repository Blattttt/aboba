package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;


public class Controller {
    @FXML
    private TextField numberfield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private TextField codefield;
    @FXML
    private Label codepic;
    @FXML
    private Button enterbtn;
    @FXML
    private Button canclebtn;


    public String user;

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader();

        String simCode = "qwertyuiopasdfghjkzxcvbnmQWERTYUOASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        char sim;
        String code = "";
        int index;
        for (int i = 0; i < 8; i++){
            index = random.nextInt(simCode.length());
            sim = simCode.charAt(index);
            code += sim;}
        codepic.setText(code);

        passwordfield.textProperty().addListener((observableValue, s, t1)->
            codefield.setEditable(true));

        enterbtn.setOnAction(event -> {
            try {
                String number = numberfield.getText();
                String password = passwordfield.getText();
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/a9",
                        "w9", "Qw12345678")) {
                    System.out.println("Подключение к бд");
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                    while (resultSet.next()) {
                        if (resultSet.getString("Email").equals(number) &&
                                resultSet.getString("Password").equals(password) &&
                                resultSet.getString("RoleID").equals("1") &&
                                (codefield.getText().equals(codepic.getText()))){
                            System.out.println("Успешный вход admin");
                            enterbtn.getScene().getWindow().hide();
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
                        } else if (resultSet.getString("Email").equals(number) &&
                                resultSet.getString("Password").equals(password) &&
                                (codefield.getText().equals(codepic.getText()))){
                            System.out.println("Успешный вход user");
                            user = resultSet.getString("FirstName");
                            Get.setUserName(user);
                            enterbtn.getScene().getWindow().hide();
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

        canclebtn.setOnAction(event -> {
            passwordfield.clear();
            numberfield.clear();
            codefield.clear();
        });


    }
}
