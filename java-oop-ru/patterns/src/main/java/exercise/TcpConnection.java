package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;


public class TcpConnection {
    private Connection state;

    public TcpConnection(String ipAddress, int port) {
        this.state = new Connected();
    }

    public void setState(Connection state) {
        this.state = state;
    }

    public String getCurrentState() {
        if (state instanceof Disconnected) {
            return "disconnected";
        } else {
            return "connected";
        }
    }

    public void connect() {
        state.connect(this);
    }

    public void disconnect() {
        state.disconnect(this);
    }

    public void write(String data) {
        state.write(this, data);
    }
}
