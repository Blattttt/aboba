package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Thread.sleep;


public class UserWindow extends Controller{
    @FXML
    private Button exitbtn;
    @FXML
    private Label welcomefield;
    @FXML
    private Label welcomefield2;



    int sleep;
    int second;
    boolean active;


    @FXML
    private void initialize() {
        String userName = Get.getUserName();
        active = true;

        exitbtn.setOnAction(event -> {
            exitbtn.getScene().getWindow().hide();
            active = false;
            thread.stop();});

        welcomefield.setText("Hi " + userName + ", Welcome to AMONIC Airlines.");

        active = true;
        thread.start();
    }
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (active) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                second++;
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            welcomefield2.setText("Time spend on system: " + second);
                        }
                    });
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

});

}
