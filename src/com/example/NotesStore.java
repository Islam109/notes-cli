package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {
    private static final String DATA_FILE = "data/notes.csv";

    public static void addNote(String text) {
        List<String> lines = readAllLines();
        int nextId = lines.size() + 1;
        String newLine = nextId + ";" + text;
        lines.add(newLine);
        writeAllLines(lines);
    }

    public static List<String> listNotes() {
        return readAllLines();
    }

    public static int countNotes() {
        return readAllLines().size();
    }

    public static boolean removeNote(int id) {
        List<String> lines = readAllLines();
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(id + ";")) {
                lines.remove(i);
                // Перегенерируем ID, чтобы они остались 1,2,3...
                for (int j = 0; j < lines.size(); j++) {
                    String oldLine = lines.get(j);
                    String newText = oldLine.substring(oldLine.indexOf(';') + 1);
                    lines.set(j, (j + 1) + ";" + newText);
                }
                writeAllLines(lines);
                return true;
            }
        }
        return false;
    }

    private static List<String> readAllLines() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Не удалось создать файл: " + DATA_FILE, e);
            }
        }
        try {
            return Files.readAllLines(Paths.get(DATA_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + DATA_FILE, e);
        }
    }

    private static void writeAllLines(List<String> lines) {
        try {
            Files.write(Paths.get(DATA_FILE), lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл: " + DATA_FILE, e);
        }
    }
}