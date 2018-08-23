package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
        ServerSocket server = null;
        Socket socket = null;
private Vector<ClientHandler> clients;

public Server() {
        clients = new Vector<>();

        try {
                server = new ServerSocket(8189);
                System.out.println("Сервер запущен!");
                while (true) {
                        socket = server.accept();

                        System.out.println("Клиент подключен!");
                        clients.add( new ClientHandler(this, socket));
                }

        } catch (IOException e) {
                e.printStackTrace();
        } finally {
        try {
                socket.close();
                server.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
        }
}

}
