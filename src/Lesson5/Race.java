import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Race {
    private ArrayList<Stage> stages;
    private CyclicBarrier cyclicBarrierForRace;
    private Semaphore semaphoreForWinner;
    private BlockingQueue<Car> CarResultQueue;

    public Race(CyclicBarrier cyclicBarrierForRace, Semaphore semaphoreForWinner, BlockingQueue<Car> CarResultQueue,Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.cyclicBarrierForRace = cyclicBarrierForRace;
        this.semaphoreForWinner = semaphoreForWinner;
        this.CarResultQueue = CarResultQueue;
    }

    public ArrayList<Stage> getStages() { return stages; }

    public CyclicBarrier getCyclicBarrierForRace() {
        return cyclicBarrierForRace;
    }

    public Semaphore getSemaphoreForWinner() {
        return semaphoreForWinner;
    }
    public BlockingQueue<Car> getCarResultQueue() {
        return CarResultQueue;
    }


}