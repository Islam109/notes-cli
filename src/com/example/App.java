package com.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Используйте --cmd=[add|list|rm|count] и опции");
            return;
        }

        String cmd = null;
        String text = null;
        int id = -1;

        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                cmd = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7);
            } else if (arg.startsWith("--id=")) {
                try {
                    id = Integer.parseInt(arg.substring(5));
                } catch (NumberFormatException e) {
                    System.err.println("Некорректный ID");
                    return;
                }
            }
        }

        if ("add".equals(cmd)) {
            if (text == null || text.isEmpty()) {
                System.err.println("Текст заметки обязателен");
                return;
            }
            NotesStore.addNote(text);
            System.out.println("Заметка добавлена");

        } else if ("list".equals(cmd)) {
            List<String> notes = NotesStore.listNotes();
            if (notes.isEmpty()) {
                System.out.println("(empty)");
            } else {
                for (String note : notes) {
                    System.out.println(note);
                }
            }

        } else if ("rm".equals(cmd)) {
            if (id <= 0) {
                System.err.println("Укажите корректный --id=N");
                return;
            }
            if (NotesStore.removeNote(id)) {
                System.out.println("Заметка #" + id + " удалена");
            } else {
                System.out.println("Not found #" + id);
            }

        } else if ("count".equals(cmd)) {
            System.out.println(NotesStore.countNotes());

        } else {
            System.err.println("Неизвестная команда: " + cmd);
        }
    }
}

