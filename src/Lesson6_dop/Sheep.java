import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Sheep implements Runnable {
    private Cargo type;
    private int speed;
    private int capacity;
    private boolean flagIsLoaded = false;

    private HashMap<String, AtomicInteger> runOperation;

    private SeaObject[] seaObjects;

    public Sheep(Cargo type){
        this.type = type;
        speed = 10 + (int)(5* Math.random());  //скорость корабля в м/с
        capacity = 450 + (int) (100* Math.random());    //вместимость товаров, ед
    }

    public void run() {
        int k = 0;
        while (runOperation.get(type.keyType).get() == 0) {
            k++;
            System.out.println("Цикл корабля " + Thread.currentThread().getName() + " #" + k);
            for (int i = 0; i < seaObjects.length; i++) {
                if ((i == 0)&&(k > 1)) {continue;}
                seaObjects[i].goIn(this);
            }
            for (int i = seaObjects.length - 2; i >= 0; i--) {
                seaObjects[i].goIn(this);
            }
        }
        // в конце загруженный корабль должен доставить груз
        if (flagIsLoaded) {
            for (int i = 1; i < seaObjects.length; i++) {
                seaObjects[i].goIn(this);
            }
        }
        System.out.println("Корабль " + Thread.currentThread().getName()+ " закончил плавать");
    }

    public Cargo getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setSeaObjects(SeaObject[] seaObjects) {
        this.seaObjects = seaObjects;
    }

    public void setRunOperation(HashMap<String, AtomicInteger> runOperation) {
        this.runOperation = runOperation;
    }

    public HashMap<String, AtomicInteger> getRunOperation() {
        return runOperation;
    }

    public void setFlagIsLoaded(boolean flagIsLoaded) {
        this.flagIsLoaded = flagIsLoaded;
    }
    public boolean getFlagIsLoaded() {
        return flagIsLoaded;
    }
}
