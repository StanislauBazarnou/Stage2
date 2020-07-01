package by.epam.learn.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Car extends Thread {
    private int carNumber;
    private static final Semaphore PARKING = new Semaphore(3, true);
    private static final boolean[] PARKING_PLACES = new boolean[3];

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public static Semaphore getPARKING() {
        return PARKING;
    }

    public static boolean[] getParkingPlaces() {
        return PARKING_PLACES;
    }

    @Override
    public synchronized void run() {
        System.out.printf("Car №%d came %n", getCarNumber());
        try {
            int parkingSpot = 0;

            if (getPARKING().tryAcquire(3000, TimeUnit.MILLISECONDS)) {
                synchronized (getParkingPlaces()) {
                    for (int i = 0; i < 3; i++) {
                        if (!getParkingPlaces()[i]) {
                            getParkingPlaces()[i] = true;
                            parkingSpot = i;
                            System.out.printf("Car №%d is parked on %d spot %n", getCarNumber(), parkingSpot);
                            break;
                        }
                    }
                }
            } else {
                System.out.printf("Car №%d cannot wait more and go to another parking %n", getCarNumber());
                getPARKING().release();
            }

            Thread.sleep(5000);

            synchronized (getParkingPlaces()) {
                getParkingPlaces()[parkingSpot] = false;
            }

            getPARKING().release();
            System.out.printf("Car №%d is out %n", getCarNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}