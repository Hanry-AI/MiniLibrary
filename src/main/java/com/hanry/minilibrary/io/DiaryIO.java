package com.hanry.minilibrary.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hanry.minilibrary.model.DiaryEntry;

public class DiaryIO {
    
    public static void saveToBinary(List<DiaryEntry> entries, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<DiaryEntry> loadFromBinary(String filename) {
        List<DiaryEntry> entries = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            entries = (List<DiaryEntry>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }
} 