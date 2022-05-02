package UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {

        try {
            System.out.println("Message: " + args[0]);

            DatagramSocket aSocket = new DatagramSocket();
            byte[] m = args[0].getBytes(StandardCharsets.UTF_8);
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println(" Reply: " + new String(reply.getData()));
            aSocket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
