package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {
    private final String filePath;

    public NotesStore(String filePath) {
        this.filePath = filePath;
    }

    public void addNote(String text) throws IOException {
        int id = getLastId() + 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(id + ";" + text);
            writer.newLine();
        }
    }

    public void listNotes() throws IOException {
        List<String> notes = readNotes();
        if (notes.isEmpty()) {
            System.out.println("(empty)");
        } else {
            notes.forEach(System.out::println);
        }
    }

    public void removeNote(int id) throws IOException {
        List<String> notes = readNotes();
        boolean found = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String note : notes) {
                String[] parts = note.split(";", 2);
                int noteId = Integer.parseInt(parts[0]);
                if (noteId != id) {
                    writer.write(note);
                    writer.newLine();
                } else {
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Not found #" + id);
        }
    }

    public void countNotes() throws IOException {
        List<String> notes = readNotes();
        System.out.println(notes.size());
    }

    private int getLastId() throws IOException {
        List<String> notes = readNotes();
        if (notes.isEmpty()) {
            return 0;
        }
        String lastNote = notes.get(notes.size() - 1);
        String[] parts = lastNote.split(";", 2);
        return Integer.parseInt(parts[0]);
    }

    private List<String> readNotes() throws IOException {
        List<String> notes = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        }
        return notes;
    }
}