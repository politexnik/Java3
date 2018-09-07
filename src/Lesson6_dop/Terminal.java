import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Terminal {
    Semaphore semaphore = new Semaphore(1, true);
    Cargo cargoType;
    int loadSpeed;


    private Port port;
    public Terminal(Cargo type) {
        this.cargoType = type;
        loadSpeed = 50 + (int)(100* Math.random());  //скорость загрузки конктерного терминала, ед товара в сек
    }

    public void loadTheCargo(Sheep sheep) {
        try {
            System.out.println("Корабль" + Thread.currentThread().getName() + " подошел к причалу для загрузки " + cargoType.keyType);
            semaphore.acquire();
            AtomicInteger storageLeft;
            storageLeft = port.getStorageHashMap().get(cargoType.keyType);
            if (storageLeft.get() > 0) {
                System.out.println("Корабль" + Thread.currentThread().getName() + " начал загрузку, товара в порту " + cargoType.keyType + " " + storageLeft.get());
                Thread.sleep(sheep.getCapacity() / loadSpeed * 1000);
                storageLeft.getAndAdd(-sheep.getCapacity());
                sheep.setFlagIsLoaded(true);
                if (storageLeft.get() <= 0) {
                    storageLeft.set(0);
                    sheep.getRunOperation().get(cargoType.keyType).incrementAndGet();
                    System.out.println("Товара " + cargoType.keyType + " не осталось");
                }
                System.out.println("Корабль" + Thread.currentThread().getName() + " загрузился на причале, осталось товара "
                        + cargoType.keyType + " " + storageLeft.get());
            } else {
                System.out.println("Корабль" + Thread.currentThread().getName() + " не загрузился на причале, на складе пусто");
            }

            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void unloadTheCargo(Sheep sheep) {
        try {
            System.out.println("Корабль" + Thread.currentThread().getName() + " подошел к причалу для разгрузки");
            semaphore.acquire();
            AtomicInteger storageLeft = port.getStorageHashMap().get(cargoType.keyType);
                Thread.sleep(sheep.getCapacity() / loadSpeed * 1000);
                storageLeft.addAndGet(sheep.getCapacity());
                sheep.setFlagIsLoaded(false);
                System.out.println("Корабль" + Thread.currentThread().getName() + " разгрузился на причале");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setPort(Port port) {
        this.port = port;
    }
}
