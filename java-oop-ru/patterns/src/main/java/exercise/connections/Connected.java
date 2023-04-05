package exercise.connections;

import com.sun.tools.jconsole.JConsoleContext;
import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    @Override
    public void connect(TcpConnection connection) {
        System.out.println("Error! Connection already established.");
    }

    @Override
    public void disconnect(TcpConnection connection) {
        connection.setState(new Disconnected());
    }

    @Override
    public void write(TcpConnection connection, String data) {
        System.out.println("Writing data: " + data);
    }
}
// END
