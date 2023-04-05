package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    @Override
    public void connect(TcpConnection connection) {

    }

    @Override
    public void disconnect(TcpConnection connection) {
        System.out.println("Error! Connection already disconnected.");
    }

    @Override
    public void write(TcpConnection connection, String data) {
        System.out.println("Error! Connection not established.");
    }
}
// END
