package by.epam.learn.iooptional;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// При разработке для вывода результатов создавать новую директорию и файл средствами класса File
//1. Создать и заполнить файл случайными целыми числами. Отсортировать содержимое файла по возрастанию

public class Task1 {
    static File writingFile = new File("src/data/Task1_Result.txt");
    static int[] array = new int[20];

    public static void main(String[] args) {
        createFile();
        fillingArrayWithNumbersFromFile();
        sortAndWriteNumberIntoFile();
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
                System.out.println("Error has occurred while creating a file");
            }
        } else if (writingFile.exists()) {
            fillingFileWithRandomNumbers();
        }
    }

    static void fillingFileWithRandomNumbers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writingFile))) {
            for (int i = 0; i < 20; i++) {
                int randomNumber = ThreadLocalRandom.current().nextInt(-50, 50);
                writer.write(randomNumber + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error has occurred in fillingWithRandomNumbers method");
        }
    }

    static void fillingArrayWithNumbersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(writingFile))) {
            String[] arrayString = reader.readLine().split(" ");
            for (int i = 0; i < 20; i++) {
                array[i] = Integer.parseInt(arrayString[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sortAndWriteNumberIntoFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writingFile, true))) {
            Arrays.sort(array);
            bufferedWriter.write("\n");
            for (int value : array) {
                bufferedWriter.write(value + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
