import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forest {
    private ArrayList<Integer> woodArray = new ArrayList<Integer>(512);
    private int[] frequenceWood = new int[21];

    public Forest(File forestInput) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(forestInput), 2048);
            String dataString;
            while ((dataString = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\d+(\\s|\n|$)");
                Matcher matcher = pattern.matcher(dataString);
                while (matcher.find()) {
                    int value = Integer.parseInt(matcher.group().trim());
                    woodArray.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Integer> getWoodArray() {
        return woodArray;
    }

    public int[] getFrequenceWood() {
        return frequenceWood;
    }
}
