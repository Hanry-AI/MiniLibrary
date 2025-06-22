package com.hanry.minilibrary.ui;

import java.time.LocalDateTime;

import com.hanry.minilibrary.model.DiaryEntry;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TableView<DiaryEntry> diaryTable;

    @FXML
    private TableColumn<DiaryEntry, Integer> colId;

    @FXML
    private TableColumn<DiaryEntry, String> colTitle;

    @FXML
    private TableColumn<DiaryEntry, LocalDateTime> colDate;

    @FXML
    private TableColumn<DiaryEntry, String> colMood;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnImport;

    @FXML
    public void initialize() {
        // Set up cell value factories
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        colMood.setCellValueFactory(new PropertyValueFactory<>("mood"));
    }
} 