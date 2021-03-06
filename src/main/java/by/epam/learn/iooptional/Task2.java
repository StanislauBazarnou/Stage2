package by.epam.learn.iooptional;

import java.io.*;
import java.util.Scanner;

//2. Прочитать текст Java-программы и все слова public в объявлении атрибутов и методов класса заменить на слово private

public class Task2 {
    static String pathToWritingFile = "src/main/resources/Task2_Result.txt";
    static File writingFile = new File(pathToWritingFile);
    static String pathToReadingFile = "src/main/java/by/epam/learn/iooptional/Task2.java";
    static File readingFile = new File(pathToReadingFile);

    public static void main(String[] args) {
        createFile();
        replaceAndWriteWordsInFile();
        System.out.println("Program finished successfully");
    }

    static void createFile() {
        if (!writingFile.exists()) {
            try {
                if (writingFile.createNewFile()) {
                    System.out.println("File created");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error during file creating");
            }
        }
    }

    static void replaceAndWriteWordsInFile() {
        String wordToBeReplace = "public";
        String wordInstead = "private";
        try (
                Scanner scanner = new Scanner(readingFile);
                BufferedWriter writer = new BufferedWriter(new FileWriter(writingFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String modified = line.replaceAll(wordToBeReplace, wordInstead);
                writer.write(modified + "\n");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
