package com.hanry.minilibrary.ui;

import java.nio.file.Path;
import java.time.LocalDateTime;

import com.hanry.minilibrary.io.DiaryIO;
import com.hanry.minilibrary.model.DiaryEntry;
import com.hanry.minilibrary.xml.DiaryXML;
import com.hanry.minilibrary.db.DiaryDAO;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    void onSave() {
        DiaryIO.saveToBinary(diaryTable.getItems(), "entries.bin");
    }

    @FXML
    void onLoad() {
        diaryTable.setItems(FXCollections.observableArrayList(DiaryIO.loadFromBinary("entries.bin")));
    }

    @FXML
    public void initialize() {
        // Set up cell value factories
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        colMood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        // Add sample entries for quick test
        diaryTable.setItems(FXCollections.observableArrayList(
            new DiaryEntry(1, "Test Entry 1", java.time.LocalDateTime.now(), "Happy", "Sample content 1"),
            new DiaryEntry(2, "Test Entry 2", java.time.LocalDateTime.now(), "Sad", "Sample content 2")
        ));
    }

    @FXML 
    private void onExportXml() {
        try {
            DiaryXML.exportXml(diaryTable.getItems(), Path.of("entries.xml"));
            alert("Export XML thành công!");
        } catch (Exception ex) { 
            alertErr(ex); 
        }
    }

    @FXML 
    private void onImportXml() {
        try {
            diaryTable.setItems(FXCollections.observableArrayList(
                    DiaryXML.importXml(Path.of("entries.xml"))));
            alert("Import XML thành công!");
        } catch (Exception ex) { 
            alertErr(ex); 
        }
    }

    @FXML private void onSaveDb() {
        DiaryDAO.saveAll(diaryTable.getItems());
        alert("Đã lưu vào database!");
    }

    @FXML private void onLoadDb() {
        diaryTable.setItems(FXCollections.observableArrayList(DiaryDAO.loadAll()));
        alert("Đã tải từ database!");
    }

    /* helpers */
    private void alert(String msg){ 
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait(); 
    }
    private void alertErr(Exception ex){ 
        new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait(); 
    }
} 