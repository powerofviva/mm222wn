package wsocket;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


// ======================================this is SERVER for thesis==========================================================/


public class Robot {
	public static void main( final String[] args ) throws Exception {
	//	final String client = UUID.randomUUID().toString().substring( 0, 8 );
		String client = "WS_client";

		final WebSocketContainer container = ContainerProvider.getWebSocketContainer();				
		final String uri = "ws://localhost:9090/broadcast";
		try( Session session = container.connectToServer( EventHandler.class, URI.create( uri ) ) ) {
				while (true) {
					FakeRobot fakeRobot = new FakeRobot(1);
					String fakeMessage = fakeRobot.NextMessage();
					session.getBasicRemote().sendObject( new MessageHandler( client, fakeMessage ) );
					Thread.sleep(1000 );
				}
		}
//		(( LifeCycle )container ).stop();

		// JSR-356 has no concept of Container lifecycle.
		// (This is an oversight on the spec's part)
		// This stops the lifecycle of the Client WebSocketContainer
	
//		if( container instanceof LifeCycle ) {
//		    ( ( LifeCycle )container ).stop();
//		}
	}
}
