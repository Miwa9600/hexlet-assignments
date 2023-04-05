package exercise.connections;

import exercise.TcpConnection;

public interface Connection {
    // BEGIN
        void connect(TcpConnection connection);
        void disconnect(TcpConnection connection);
        void write(TcpConnection connection, String data);
    // END
}
