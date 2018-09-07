import java.util.concurrent.Semaphore;

public class SeaChannel extends SeaObject{
    int length;
    Semaphore seaChannelSemaphore = new Semaphore(5);
    public SeaChannel(int length) {
        this.length = length;
    }

    @Override
    public void goIn(Sheep sheep) {

        try {
            System.out.println("Корабль" + Thread.currentThread().getName() + " подошел ко входу в пролив, ожидает");
            seaChannelSemaphore.acquire();
            System.out.println("Корабль" + Thread.currentThread().getName() + " вошел в пролив");
            Thread.sleep((int)(length/ sheep.getSpeed())*1000);
            seaChannelSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
