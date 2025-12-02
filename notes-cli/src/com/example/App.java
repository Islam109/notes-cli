package com.example;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: --cmd=<command> [--text=<text>] [--id=<id>]");
            return;
        }

        String cmd = "";
        String text = "";
        int id = -1;

        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                cmd = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7);
            } else if (arg.startsWith("--id=")) {
                id = Integer.parseInt(arg.substring(5));
            }
        }

        NotesStore store = new NotesStore("data/notes.csv");

        switch (cmd) {
            case "add":
                store.addNote(text);
                break;
            case "list":
                store.listNotes();
                break;
            case "rm":
                store.removeNote(id);
                break;
            case "count":
                store.countNotes();
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}