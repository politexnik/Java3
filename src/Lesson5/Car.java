import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0; //Счетчик созданных машин
    }
    private Race race;
    private int speed;
    private String name;

    private long resultTime;
    private CyclicBarrier cyclicBarrierForRace;
    private Semaphore semaphoreForWinner;
    private BlockingQueue<Car> carResultQueue;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        cyclicBarrierForRace = race.getCyclicBarrierForRace();
        semaphoreForWinner = race.getSemaphoreForWinner();
        carResultQueue = race.getCarResultQueue();
    }

    @Override
    public void run() {
        try {

            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrierForRace.await();   //ждем остальных
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        System.out.println(this.name + " финишировал!");
        resultTime = System.currentTimeMillis();
        if (semaphoreForWinner.availablePermits() == 1) {
            try {
                semaphoreForWinner.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.name + " выиграл!");
        }
        carResultQueue.add(this);
    }

    public long getResultTime() {
        return resultTime;
    }
}