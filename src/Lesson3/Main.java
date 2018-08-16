import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
//            readFileAndPrint();
//            copyFilesToOne();
            readByPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1. Считать файл 50байт в массив и вывести в консоль
    public static void readFileAndPrint() throws IOException {
        File f = new File("sourse_file.txt");
        FileInputStream in = new FileInputStream(f);
        byte[] byteArr = new byte[50];
        in.read(byteArr);
        System.out.println(Arrays.toString(byteArr));
        in.close();
    }

    //2. Считать 5 файлов и собрать в 1
    public static void copyFilesToOne () throws IOException {
        File[] fileArr = {new File("sourse_file.txt"),
                new File("sourse_file1.txt"),
                new File("sourse_file2.txt"),
                new File("sourse_file3.txt"),
                new File("sourse_file4.txt"),};
        for (File f : fileArr) {
            if (!f.exists()) f.createNewFile();
        }
        File outFile = new File("out.txt");
        if (!outFile.exists()) outFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outFile);

        ArrayList<InputStream> al = new ArrayList<InputStream>();
        for (File f : fileArr) {
            al.add(new FileInputStream(f));
        }
        Enumeration<InputStream> e = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(e);
        int sym;
        while (( sym = sis.read() ) !=-1) {
            fos.write(sym);
            if (sis.available() == 0) {
                fos.write('\n');        //Конец части сшиваемого файла отделяем строкой
            }
        }

        fos.close();
        sis.close();
    }
    //3. Консольное приложение чтение файла постранично
    public static void readByPages() throws IOException {
        File f = new File("sourse_file.txt");
        BufferedReader br = new BufferedReader( new FileReader(f), 1800);
        Scanner sc = new Scanner(System.in);
Mark:   while (true) {
            System.out.println("Чтение постранично из файла, прочитать страницу? y/n");
            String userCommand = sc.nextLine();
            if (userCommand.equals("n")) {
                break;
            } else if (userCommand.equals("y")) {
                int symbol;
                for (int i = 0; i < 1800; i++) {
                    if ((symbol = br.read()) != -1) {
                        System.out.print((char)br.read());
                    } else {
                        System.out.println("\nДостигнут конец файла");
                        break Mark;
                    }
                }

                System.out.println();
            }
        }
        br.close();
    }
}



