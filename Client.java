import java.awt.geom.Ellipse2D;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
    private String address;
    private DisplayGame panel;

    public Client(String address, DisplayGame panel) {
        this.address = address;
        this.panel = panel;
    }

    private void connectToPeer() {
        Socket socket = null;

        try {
            socket = new Socket(this.address, 8080);
            receiveFoods(socket);
            receivePoisons(socket);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                post(socket);
                TimeUnit.MILLISECONDS.sleep(1);
                get(socket);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void get(Socket socket) {
        try {
            InputStream iStream = socket.getInputStream();
            ObjectInputStream oiStream = new ObjectInputStream(iStream);
            Ellipse2D.Double player2 = (Ellipse2D.Double) oiStream.readObject();
            panel.player2.Player=player2;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void post(Socket socket) throws IOException {
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
        ooStream.writeObject(panel.player1.Player);
    }


    private void receivePoisons(Socket socket) throws IOException, ClassNotFoundException {
        InputStream iStream = socket.getInputStream();
        ObjectInputStream oiStream = new ObjectInputStream(iStream);
        Ellipse2D.Double[] poisons = (Ellipse2D.Double[]) oiStream.readObject();
        panel.poison.setPoisons(poisons);
    }

    private void receiveFoods(Socket socket) throws IOException, ClassNotFoundException {
        InputStream iStream = socket.getInputStream();
        ObjectInputStream oiStream = new ObjectInputStream(iStream);
        Foods foods = (Foods) oiStream.readObject();
        panel.food=foods;
    }


    @Override
    public void run() {
        connectToPeer();
        }
}
