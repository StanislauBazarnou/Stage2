package by.epam.learn.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sandbox3 {
    static File taskio = new File("/home/stanislau/AutomatedTesting/AutomatedTestingTraining/Stage2/Java_Input_" +
            "and_Output/data/TaskIO.txt");
    static StringBuilder builder = new StringBuilder();

    static void recursivePrint(File[] dir, int level) {
        for (File file : dir) {
            for (int i = 0; i < level; i++) {
                builder.append("\t");
            }
            if (file.isFile()) {
                builder.append(file.getName()).append("\n");
            } else if (file.isDirectory()) {
                builder.append("[").append(file.getName()).append("]").append("\n");
                recursivePrint(file.listFiles(), level + 1);
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("/home/stanislau/AutomatedTesting/AutomatedTestingTraining/Stage2/Java_Input_and_Output");
        File[] dir = directory.listFiles();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskio))) {
            if (directory.exists() && directory.isDirectory()) {
                recursivePrint(dir, 0);
                writer.write(builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
