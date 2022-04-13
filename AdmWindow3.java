package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class AdmWindow3 {
    @FXML
    private Button canclebtn;

    @FXML
    private void initialize() {
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
