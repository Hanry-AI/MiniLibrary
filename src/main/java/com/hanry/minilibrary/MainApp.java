package com.hanry.minilibrary;

import java.io.IOException;
import java.util.Objects;

import com.hanry.minilibrary.db.DBUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize database
            DBUtil.initializeDatabase();
            
            Parent root = FXMLLoader.load(
                Objects.requireNonNull(
                    MainApp.class.getResource("/com/hanry/minilibrary/ui/MainView.fxml")));
            primaryStage.setTitle("Diary Plus");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
} 