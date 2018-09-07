public class Sea extends SeaObject{
    private int length;
    private String name;
    public Sea(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void goIn(Sheep sheep) {
        try {
            System.out.println("Корабль" + Thread.currentThread().getName() + " плывет по морю " + name);
            Thread.sleep((int)(length/ sheep.getSpeed())*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
