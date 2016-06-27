package tcp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.*;


// ======================================this is SERVER for thesis==========================================================/

/**
 * Connects to a SimpleServer which is listening on port 8189
 */
public class SimpleClient {
    String serverurl = "127.0.0.1";
    int serverport = 8189;

    public static void main(String[] args) {
        SimpleClient simpleclient = new SimpleClient();
        simpleclient.init();
    }


    public void init() {
        Socket socket = null;
        int digit = 200;
        try {

            FakeRobot fakeRobot = new FakeRobot(1);
            String fakeMessage = fakeRobot.NextMessage();
        //System.out.println("Connecting to " + serverurl + " on port " + serverport);
            socket = new Socket(serverurl, serverport);
            System.out.println("Connected.");
            PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);                                             //establish an printwriter using the output streamof the socket object and set auto flush on
        while (true) {
            printwriter.println(fakeMessage);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.print("an error ...");
            }
        }
        } catch (UnknownHostException unhe) {
            System.out.println("UnknownHostException: " + unhe.getMessage());
        } catch (InterruptedIOException intioe) {
            System.out.println("Timeout while attempting to establish socket connection.");
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ioe) {
                System.out.println("IOException: " + ioe.getMessage());
            }
        }
    }
}
