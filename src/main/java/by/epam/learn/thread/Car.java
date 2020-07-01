package by.epam.learn.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Car extends Thread {
    private int carNumber;
    private static final int SPOT_NUMBERS = 3;
    private static final Semaphore PARKING = new Semaphore(getSpotNumbers(), true);
    private static final boolean[] PARKING_PLACES = new boolean[getSpotNumbers()];

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public static int getSpotNumbers() {
        return SPOT_NUMBERS;
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
                    for (int i = 0; i < getSpotNumbers(); i++) {
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

            Thread.sleep(4000);

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