package wsocket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
    String message =  "  The history of Iran, commonly also known as Persia in the Western world, is intertwined with the history of a larger region, also to an extent known as Greater Iran, comprising the area from Anatolia, the Bosphorus, and Egypt in the west to the borders of Ancient India and Syr Darya in the east, and from the Caucasus and the Eurasian Steppe in the north to the Persian Gulf and the Gulf of Oman in the south. Iran is home to one of the world's oldest continuous major civilizations, with historical and urban settlements dating back to 4000 BC.[1] The southwestern and western part of the Iranian plateau participated in the traditional Ancient Near East with Elam, from the Early Bronze Age, and later with various other peoples, such as the Kassites, Manneans, and Gutians. Hegel names the Persians as the first Historical People.[2] The Medes unified Iran as a nation and empire in 625 BC.[3] The Achaemenid Empire (550�330 BC), founded by Cyrus the Great, was the first of the Persian empires to rule from the Balkans to North Africa and also Central Asia, spanning three continents, from their seat of power in Persis (Persepolis). It was the largest empire yet seen and the first world empire.[4] They were succeeded by the Seleucid Empire, the Parthians and the Sasanians who governed Iran for almost 1,000 years, and would put Iran once again as the leading powers in the world, only this time amongst their arch rival, the Roman Empire and the successive Byzantine Empire.";
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
