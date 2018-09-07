import java.io.File;

public class Main {
    public static void main(String[] args) {
        File forestInput = new File("forestInput.data");
        Forest forest = new Forest(forestInput);
        Forester Jack = new Forester();
        Jack.goCountWood(forest);
    }
}
