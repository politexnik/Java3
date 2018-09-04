import java.util.ArrayList;

public class Forester extends Thread {
    private Forest forest;

    public void goCountWood(Forest forest) {
        ArrayList<Integer> woodArray = forest.getWoodArray();
        int[] frequenceWood = forest.getFrequenceWood();
        for (int i = 0; i < woodArray.size(); i++) {
            int tree = woodArray.get(i);
            frequenceWood[tree]++;
        }
        System.out.println("Количество определенных деревьев в лесу");
        for (int i = 1; i < frequenceWood.length; i++) {
            if (frequenceWood[i] != 0) {
                System.out.print("#" + i + "-");
                System.out.print(frequenceWood[i] + " ");
            }
        }

    }
}
