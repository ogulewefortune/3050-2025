import ocsf.server.*;

public class TestServer extends AbstractServer {
    public TestServer(int port) {
        super(port);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("Message received from client: " + msg);
        System.out.println("Client IP: " + client.getInetAddress());
        
        // Echo back to client
        try {
            String response = "Server at " + java.time.LocalTime.now() + " received: " + msg;
            client.sendToClient(response);
            System.out.println("Sent response to client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void serverStarted() {
        System.out.println("🚀 Server started successfully on port " + getPort());
        System.out.println("📡 Waiting for client connections...");
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        System.out.println(" New client connected: " + client.getInetAddress());
    }

    public static void main(String[] args) {
        try {
            int port = 8300;
            TestServer server = new TestServer(port);
            server.listen();
        } catch (Exception e) {
            System.err.println(" Server failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }
}