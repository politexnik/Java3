import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Port extends SeaObject{

    private String name;

    private HashMap<String, AtomicInteger> storageHashMap;

    private Terminal terminalClothes;
    private Terminal terminalFood;
    private Terminal terminalOil;


    public Port(String name, int food, int oil, int clothes) {
        this.name = name;
        storageHashMap = new HashMap<String, AtomicInteger>(3);
        storageHashMap.put("food", new AtomicInteger(food));
        storageHashMap.put("clothes", new AtomicInteger(clothes));
        storageHashMap.put("oil", new AtomicInteger(oil));
        terminalClothes = new Terminal(Cargo.CLOTHES);
        terminalClothes.setPort(this);
        terminalFood = new Terminal(Cargo.FOOD);
        terminalFood.setPort(this);
        terminalOil = new Terminal(Cargo.OIL);
        terminalOil.setPort(this);
    }

    public Terminal getTerminal(Sheep sheep) {
        if (sheep.getType() == Cargo.CLOTHES) {
            return terminalClothes;
        } else if (sheep.getType() == Cargo.FOOD) {
            return terminalFood;
        }
        return terminalOil;
    }

    public void goIn(Sheep sheep) {
        System.out.println("Корабль" + Thread.currentThread().getName() + " вошел в порт " + name);
        if (( !sheep.getFlagIsLoaded() ) && (sheep.getRunOperation().get(sheep.getType().keyType).get() == 0)) { //если пустой
            // и если есть товарооборот
            getTerminal(sheep).loadTheCargo(sheep);   //получаем объект терминала и посылаем корабль в него загружаться
        } else if (sheep.getFlagIsLoaded()){    //если еще есть товар
            getTerminal(sheep).unloadTheCargo(sheep);   //получаем объект терминала и посылаем корабль в него разгружаться
        }
    }


    public Terminal getTerminalClothes() {
        return terminalClothes;
    }

    public Terminal getTerminalFood() {
        return terminalFood;
    }

    public Terminal getTerminalOil() {
        return terminalOil;
    }

    public HashMap<String, AtomicInteger> getStorageHashMap() {
        return storageHashMap;
    }
}
