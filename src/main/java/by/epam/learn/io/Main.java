package by.epam.learn.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static void recursivePrint(Path path ,File[] dir, int level) throws IOException {
        for (File file : dir) {
            for (int i = 0; i < level; i++) {
                Files.write(path, "\t".getBytes(), StandardOpenOption.APPEND);
            }
            if (file.isFile()) {
                Files.write(path, (file.getName() + "\n").getBytes(), StandardOpenOption.APPEND);
            } else if (file.isDirectory()) {
                Files.write(path, ("[" + file.getName() + "]" + "\n").getBytes(), StandardOpenOption.APPEND);
                recursivePrint(path ,Objects.requireNonNull(file.listFiles()), level + 1);
            }
        }
    }

    static void readFile(Path pathAmonFile) {
        int directoryCounter = 0;
        int fileCounter = 0;
        int lengthOfLines = 0;
        double averageFilesInFolder = 0;
        double averageLengthOfName = 0;

        try(Scanner scanner = new Scanner(pathAmonFile)) {
            String line;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.contains("|-----")) {
                    directoryCounter++;
                } else if (!line.contains("|-----") & line.contains("       ")) {
                    fileCounter++;
                    lengthOfLines += line.trim().length();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (directoryCounter != 0) {
            averageFilesInFolder = (double) fileCounter / directoryCounter;
        }
        if (fileCounter != 0) {
            averageLengthOfName = (double) lengthOfLines / fileCounter;
        }
        System.out.println("Number of folders : " + directoryCounter);
        System.out.println("Number of files : " + fileCounter);
        System.out.println("Average number of files in folder : " + averageFilesInFolder);
        System.out.printf("Average length of the file name : %.2f", averageLengthOfName);
    }

    public static void main(String[] args) {
        Path path = Paths.get(args[0]);
        Path pathToWritingFile = Paths.get("src/data/TaskIO.txt");
        File file = new File(String.valueOf(path));
        File[] dir = file.listFiles();

        if (file.exists() && file.isDirectory()) {
            try {
                assert dir != null;
                recursivePrint(pathToWritingFile ,dir, 0);
                System.out.println("Program finished successfully");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Problem occurred during call recursivePrint() method");
            }
        } else if(file.exists() && file.isFile()) {
            readFile(path);
        }
    }
}
