import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static long startTime = System.currentTimeMillis();
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cyclicBarrierForRace = new CyclicBarrier(CARS_COUNT, new StartAction());
        Semaphore semaphoreForTunnel = new Semaphore((int)CARS_COUNT/2, true);  //справедливый семафор для тоннеля
        Semaphore semaphoreForWinner = new Semaphore(1, true);  //семафор для победителя
        BlockingQueue<Car> CarResultQueue = new ArrayBlockingQueue<Car>(CARS_COUNT);

        Race race = new Race(cyclicBarrierForRace, semaphoreForWinner, CarResultQueue, new Road(60), new Tunnel(60,semaphoreForTunnel), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        Thread[] treadCars = new Thread[cars.length];
        for (int i = 0; i < cars.length; i++) {
            treadCars[i] = new Thread(cars[i]);
            treadCars[i].start();
        }
        for (Thread tread: treadCars) {
            try {
                tread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("Результаты:");

        for (int i = 1; i <= CARS_COUNT; i++) {
            long result = (CarResultQueue.peek().getResultTime() - startTime);
            System.out.println(i + "-е место: " + CarResultQueue.poll().getName() + ", время " + result + " мс");
        }
    }

    static class StartAction implements Runnable {
        @Override
        public void run() {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

}