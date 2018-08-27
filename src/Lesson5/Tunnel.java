import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore;
    public Tunnel(int length, Semaphore semaphore) {    //изменил конструктор, длина тоннеля теперь задается
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = semaphore;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                //вставка кода ожидания если превышен лимит машин по тоннелю
                System.out.println(c.getName() + " начал этап: " + description);
                //System.out.println("Свободных мест в тоннеле:" + semaphore.availablePermits()); //Добавлено для
                //наглядности, в документации метод указан как "для отладки"
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
                //System.out.println("Свободных мест в тоннеле:" + semaphore.availablePermits());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}