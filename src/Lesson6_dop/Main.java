import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        SeaObject[] seaObjects = {
                new Port("A", 5900,8500,2700),
                new Sea("A",100),
                new SeaChannel(50),
                new Sea("B",50),
                new Port("B",0,0,0)     // ответный порт пустой - туда перевозим
        };

        HashMap<String, AtomicInteger> runOperation = new HashMap<String, AtomicInteger>(3);
        runOperation.put("food", new AtomicInteger(0));
        runOperation.put("oil", new AtomicInteger(0));
        runOperation.put("clothes", new AtomicInteger(0));
        Sheep sheeps[] = new Sheep[15];

        for (int i = 0; i < sheeps.length; i++) {
            switch (i%3) {
                case 0: {
                    sheeps[i] = new Sheep(Cargo.CLOTHES);
                    break;
                }
                case 1: {
                    sheeps[i] = new Sheep(Cargo.FOOD);
                    break;
                }
                case 2: {
                    sheeps[i] = new Sheep(Cargo.OIL);
                    break;
                }
            }
        }

//        sheeps[0] = new Sheep(Cargo.CLOTHES);
//        sheeps[1] = new Sheep(Cargo.CLOTHES);
//        sheeps[1] = new Sheep(Cargo.CLOTHES);
//        sheeps[1] = new Sheep(Cargo.CLOTHES);
//        sheeps[1] = new Sheep(Cargo.CLOTHES);
//        sheeps[2] = new Sheep(Cargo.FOOD);
//        sheeps[2] = new Sheep(Cargo.FOOD);
//        sheeps[2] = new Sheep(Cargo.FOOD);
//        sheeps[2] = new Sheep(Cargo.FOOD);
//        sheeps[2] = new Sheep(Cargo.FOOD);
//        sheeps[3] = new Sheep(Cargo.OIL);
//        sheeps[4] = new Sheep(Cargo.FOOD);

        for (int i = 0; i < sheeps.length; i++) {
            sheeps[i].setSeaObjects(seaObjects);
            sheeps[i].setRunOperation(runOperation);
            Thread thread = new Thread(sheeps[i]);
            thread.start();
        }
    }

}
