

import java.util.Timer;
import java.util.TimerTask;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



// ======================================this is SERVER for thesis==========================================================/

public class Robot extends TimerTask{

    public static void main(String[] args) {

	TimerTask mytask = new Robot();
	Timer timer = new Timer();
	timer.schedule(mytask, 1000, 1000);


    }

        //------------------------------------------------------------ http://localhost:8080/lnu/mahdi

@Override
public void run() {

    FakeRobot fakeRobot = new FakeRobot(1 );
    String fakeMessage = fakeRobot.NextMessage();


    try {

        Client client = Client.create();     //change port to 8888 to monitor in fiddler
        WebResource webResource = client.resource("http://localhost:8080/lnu/post");

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, fakeMessage);
//        System.out.println(fakeMessage);//
//        System.out.println("                                                                                              ");

        if (response.getStatus() != 201) {       //---------------------------------  to check the server's response
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
//        String output = response.getEntity(String.class);  //------------------ to print the server's response
//            System.out.println( "-----> "+ output);

      }
    catch (Exception e) {
        e.printStackTrace();
      }
//    }  -----------------------------------------------------> delete this if u want to use time task

    }
}