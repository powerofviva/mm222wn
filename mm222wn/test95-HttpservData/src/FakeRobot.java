import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by mahdi on 5/11/2016.
 */
public class FakeRobot {

    int textSize;
    int delay;

    FakeRobot( int messageSize ,int delayTime){
        textSize = messageSize;
        delay = delayTime;
    }

    FakeRobot(int messageSize){
        textSize = messageSize;
    }
    String message =  " ... " ;
    String NextMessage() {
        String fakeMessage = message;

        for (int i = 0; i <textSize -1 ; i++) {
            fakeMessage = fakeMessage + message;
        }

        int digit = 1;
        JSONObject json = new JSONObject();
        json.put("validTime", "2016-02-24T11:00:00Z");
        json.put("text: ", fakeMessage);
        JSONArray jsonArray = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("mcc", digit);
        obj.put("temprature", digit + 1);
        obj.put("Humidity", digit + 10);
        jsonArray.add(obj);
        json.put("JSONdata --> ", jsonArray);
        String Json2Str = json.toJSONString();

        return  Json2Str;
    }
}
