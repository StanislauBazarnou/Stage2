package by.epam.learn.InputAndOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    static Path path = Paths.get("src/data/TaskIO.txt");

    static void recursivePrint(File[] dir, int level) throws IOException {
        for (File file : dir) {
            for (int i = 0; i < level; i++) {
                Files.write(path, "\t".getBytes(), StandardOpenOption.APPEND);
            }

            if (file.isFile()) {
                Files.write(path, (file.getName() + "\n").getBytes(), StandardOpenOption.APPEND);
            } else if (file.isDirectory()) {
                Files.write(path, ("[" + file.getName() + "]" + "\n").getBytes(), StandardOpenOption.APPEND);
                recursivePrint(file.listFiles(), level + 1);
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("by/epam/learn/InputAndOutput/");
        File[] dir = directory.listFiles();
        if (directory.exists() && directory.isDirectory()) {
            try {
                recursivePrint(dir, 0);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Problem occurred during call recursivePrint() method");
            }
        }
        System.out.println("Program finished successfully");
    }
}
