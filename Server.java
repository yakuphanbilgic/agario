import java.awt.geom.Ellipse2D;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable {
    private DisplayGame panel;
    private boolean mapSent = false;

    public Server(DisplayGame panel) {
        this.panel = panel;
    }

    private void hostGame() throws IOException {
    	System.out.println("RTE");
        ServerSocket listener = new ServerSocket(8080);
        Socket socket = listener.accept();

        if (!mapSent) {
            sendFoods(socket);
            sendPoisons(socket);
            mapSent = true;
        }

        while (true) {
            get(socket);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post(socket);
        }
    }

    private void sendFoods(Socket socket) {
        try {
            OutputStream oStream = socket.getOutputStream();
            ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
            ooStream.writeObject(panel.food.getFoods());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void post(Socket socket) throws IOException {
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
        ooStream.writeObject(panel.player1.Player);
    }


    private void sendPoisons(Socket socket) {
        try {
            OutputStream oStream = socket.getOutputStream();
            ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
            ooStream.writeObject(panel.poison.getPoisons());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void get(Socket socket) {
        try {
            InputStream iStream = socket.getInputStream();
            ObjectInputStream oiStream = new ObjectInputStream(iStream);
            Ellipse2D.Double opponent = (Ellipse2D.Double) oiStream.readObject();
            panel.player2.Player= opponent;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        try {
            hostGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
