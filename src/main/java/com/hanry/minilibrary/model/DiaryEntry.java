package com.hanry.minilibrary.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DiaryEntry {
    private final IntegerProperty id;
    private final StringProperty title;
    private final ObjectProperty<LocalDateTime> timestamp;
    private final StringProperty mood;
    private final StringProperty content;

    public DiaryEntry(int id, String title, LocalDateTime timestamp, String mood, String content) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.timestamp = new SimpleObjectProperty<>(timestamp);
        this.mood = new SimpleStringProperty(mood);
        this.content = new SimpleStringProperty(content);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public LocalDateTime getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<LocalDateTime> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp.set(timestamp);
    }

    public String getMood() {
        return mood.get();
    }

    public StringProperty moodProperty() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood.set(mood);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }
} 