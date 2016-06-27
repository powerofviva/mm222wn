package tcp;

/**
 * Created by mahdi on 4/24/2016.
 */



import java.io.*;
import java.net.*;
import java.util.*;

/**  When started allows one client to connect. It listens on port 8189.
 *  It returns whatever a connected client sends.
 *  It shuts down when the client sends a Bye line.
 *
 */


// ======================================this is SERVER for thesis==========================================================/
public class SimpleServer{

    public static void main(String[] args){
        SimpleServer simpleserver = new SimpleServer();
        simpleserver.init();
    }

    public void init(){
        ServerSocket serversocket = null;
        Socket socket = null;
        try{
            serversocket = new ServerSocket(8189);
            socket = serversocket.accept();
            InputStreamReader inputstreamreader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            String readLine = "";
            boolean finished = false;
            while (((readLine = bufferedreader.readLine()) != null) && (!finished)){
                System.out.println("Received from Client: " + readLine);
                if (readLine.compareToIgnoreCase("Bye") == 0) finished = true;
            }
            System.out.println("Closing connection.");
        }catch(UnknownHostException unhe){
            System.out.println("UnknownHostException: " + unhe.getMessage());
        }catch(InterruptedIOException intioe){
            System.out.println("Timeout while attempting to establish socket connection.");
        }catch(IOException ioe){
            System.out.println("IOException: " + ioe.getMessage());
        }finally{
            try{
                socket.close();
                serversocket.close();
            }catch(IOException ioe){
                System.out.println("IOException: " + ioe.getMessage());
            }
        }
    }
}
