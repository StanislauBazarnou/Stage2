package by.epam.learn.thread;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Car(i).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}