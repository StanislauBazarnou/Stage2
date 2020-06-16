package by.epam.learn.iooptional;

import java.io.*;

//При разработке для вывода результатов создавать новую директорию и файл средствами класса File.
//8. Прочитать текст Java-программы и удалить из него все «лишние» пробелы и табуляции, оставив только необходимые
// для разделения операторов

public class Task8 {
    static File readingFile = new File("src/main/java/by/epam/learn/iooptional/Task2.java");
    static File writingFile = new File("src/data/Task8_Result.txt");
    static String[] array = new String[linesCounter(readingFile)];

    public static void main(String[] args) {
        readFile();
        writeFile();
        System.out.println("Program finished successfully");
    }

    static int linesCounter(File name) {
        int count = 0;
        try (LineNumberReader lineCounter = new LineNumberReader(new FileReader(name))) {
            while (lineCounter.readLine() != null) {
                count = lineCounter.getLineNumber();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(readingFile))) {
            for (int i = 0; i < array.length; i++) {
                String line = reader.readLine();
                array[i] = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error has occurred while reading a file");
        }
    }

    static void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writingFile))) {
            for (String line : array) {
                writer.write(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error has occurred while writing in a file");
        }
    }
}
