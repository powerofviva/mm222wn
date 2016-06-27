package sse;


import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@Path("server-sent-events")
public class ServerSentEventsResource {

    //private static EventOutput eventOutput = new EventOutput();
    private String txt = "  The history of Iran, commonly also known as Persia in the Western world, is intertwined with the history of a larger region, also to an extent known as Greater Iran, comprising the area from Anatolia, the Bosphorus, and Egypt in the west to the borders of Ancient India and Syr Darya in the east, and from the Caucasus and the Eurasian Steppe in the north to the Persian Gulf and the Gulf of Oman in the south. Iran is home to one of the world's oldest continuous major civilizations, with historical and urban settlements dating back to 4000 BC.[1] The southwestern and western part of the Iranian plateau participated in the traditional Ancient Near East with Elam, from the Early Bronze Age, and later with various other peoples, such as the Kassites, Manneans, and Gutians. Hegel names the Persians as the first Historical People.[2] The Medes unified Iran as a nation and empire in 625 BC.[3] The Achaemenid Empire (550�330 BC), founded by Cyrus the Great, was the first of the Persian empires to rule from the Balkans to North Africa and also Central Asia, spanning three continents, from their seat of power in Persis (Persepolis). It was the largest empire yet seen and the first world empire.[4] They were succeeded by the Seleucid Empire, the Parthians and the Sasanians who governed Iran for almost 1,000 years, and would put Iran once again as the leading powers in the world, only this time amongst their arch rival, the Roman Empire and the successive Byzantine Empire.";

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getMessageQueue() throws InterruptedException, IOException {

        final EventOutput seq = new EventOutput();
        FakeRobot fakeRobot = new FakeRobot(1);
        String fakeMessage = fakeRobot.NextMessage();

        new Thread() {
            public void run() {
                try {
                    while (true) {   //-------------------------------------- vase test infinite message
//                		seq.write(new OutboundEvent.Builder().name("in-progress").data(String.class, "starting SSE " +  " ...").build());
//                        Thread.sleep(1000);
                        seq.write(new OutboundEvent.Builder().data(String.class, fakeMessage).build());
                        Thread.sleep(1000);
                    }
//                   seq.close();
                } catch (final InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }.start(); //---------------------------------------------end of infinite message

        //-------------------------------------------------------line 74 and 75 is for  1 message
//        seq.write(new OutboundEvent.Builder().data(String.class,  Json2Str).build());  // --> ino badan pak kon
//        seq.close();
        //----------------------end of 1 message

//        try {
//            eventOutput.write(new OutboundEvent.Builder().name("custom-message").data(String.class, seq).build());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return seq;


    }

}