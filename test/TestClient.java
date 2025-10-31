import com.lloseng.ocsf.client.*;
import java.util.Scanner;

public class TestClient extends AbstractClient {
    public TestClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println(" Server response: " + msg);
    }

    @Override
    protected void connectionEstablished() {
        System.out.println(" Connected to server at " + getHost() + ":" + getPort());
    }

    @Override
    protected void connectionException(Exception exception) {
        System.out.println(" Connection error: " + exception.getMessage());
    }

    @Override
    protected void connectionClosed() {
        System.out.println("🔌 Connection closed");
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter server IP address: ");
            String serverIP = scanner.nextLine().trim();
            
            int port = 8300;
            TestClient client = new TestClient(serverIP, port);
            
            System.out.println(" Connecting to " + serverIP + ":" + port + "...");
            client.openConnection();
            
            System.out.println("Type messages to send to server (type 'quit' to exit):");
            
            while (true) {
                String input = scanner.nextLine();
                if ("quit".equalsIgnoreCase(input)) {
                    break;
                }
                client.sendToServer("Client says: " + input);
                System.out.println(" Sent: " + input);
            }
            
            client.closeConnection();
            scanner.close();
            System.out.println(" Client disconnected");
            
        } catch (Exception e) {
            System.err.println(" Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}