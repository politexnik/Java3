package Client;

import java.io.*;
import java.net.Socket;

public class Controller {

    Socket socket;

    ObjectInputStream in;
    ObjectOutputStream out;


    final String IP_ADRESS = "localhost";
    final int PORT = 8189;


    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            System.out.println("Подключение потоков контроллера");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("входящий");
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("исходящий");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Создаем Jack");
                        Student Jack = new Student("Jack");
                        System.out.println("Пишем в поток Jack");
                        out.writeObject(Jack);

                        System.out.println("Записали в поток Jack");
                        while (true) {

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
