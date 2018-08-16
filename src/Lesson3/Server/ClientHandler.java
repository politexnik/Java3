package Server;

import Client.Student;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Server server;
    private Socket socket;

    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключен!");
            ClientHandler clientHandler = this; //военная хитрость, чтобы применить этот экземпляр внутри безымянного класса
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Считываем студента из потока");
                        Student Jack = (Student) in.readObject();
                        System.out.println(Jack.getName());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}