package wsocket;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.json.simple.JSONObject;


//@ClientEndpoint( encoders = { MessageHandler.MessageEncoder.class }, decoders = { MessageHandler.MessageDecoder.class } )
@ClientEndpoint(encoders = {MessageHandler.MessageEncoder.class}, decoders = {MessageHandler.MessageDecoder.class})

public class EventHandler {
    private static final Logger log = Logger.getLogger(EventHandler.class.getName());

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {
        JSONObject jobj = new JSONObject();
        jobj.put("hello: ", "You are connected successfully! !");
        String str = jobj.toJSONString();
        session.getBasicRemote().sendObject(new MessageHandler("Hello Client", str));
//		session.getBasicRemote().sendObject( new MessageHandler( "Hello Client", "You are connected successfully! !" ) );
    }

    @OnMessage
    public void onMessage(final MessageHandler messageHandler) {
        System.out.println("  " + messageHandler.getUsername() + "		" + messageHandler.getMessage());
    }
}
