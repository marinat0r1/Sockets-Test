package TCP;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("The Server is up");
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("New Connection");
                Connection connection = new Connection(clientSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            out = new DataOutputStream ( clientSocket.getOutputStream() );
            in = new DataInputStream ( clientSocket.getInputStream() );
            this.start();
        } catch( IOException e) {System. out. println(" Connection:"+ e.getMessage());}
    }

    public void run(){
        try {
            String data = in.readUTF ();
            out.writeUTF(data);

            System.out.println("Sent data: " + data);
        } catch( EOFException e) {System.out.println(" EOF:"+ e.getMessage());
        } catch( IOException e) {System.out.println(" IO:"+ e.getMessage());}
    }
}
