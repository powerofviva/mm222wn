package services;

import java.io.StringReader;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import redis.clients.jedis.Jedis;


public class MessageHandler {
	
	
		public static int counter = 0;
		 
		
	
	public static class MessageEncoder implements Encoder.Text<MessageHandler> {
		@Override
		public void init( final EndpointConfig config ) {
		}
		
		@Override
		public String encode( final MessageHandler messageHandler) throws EncodeException {
					
			return Json.createObjectBuilder()	
					.add( "username", "mahdi84" )
					.add( "messageHandler", messageHandler.getMessage() )
					.build()
					.toString();
		}
		
		@Override
		public void destroy() {
		}
	}
	
//-------------------------------------------------------------------------------------------------------//	
	public static class MessageDecoder implements Decoder.Text<MessageHandler> {
		private JsonReaderFactory factory = Json.createReaderFactory( Collections.< String, Object >emptyMap() );
		
		@Override
		public void init( final EndpointConfig config ) {
		}
		
		@Override
		public MessageHandler decode(final String str ) throws DecodeException {
			Jedis jedis = new Jedis("localhost");
			final MessageHandler messageHandler = new MessageHandler();
			
			try( final JsonReader reader = factory.createReader( new StringReader( str ) ) ) {
				final JsonObject json = reader.readObject();
				messageHandler.setUsername( json.getString( "username" ) );
				messageHandler.setMessage( json.getString( "messageHandler" ) );
				
				//add to the database
			//	Jedis jedis = new Jedis("localhost");
				
				jedis.set("wkey" + (counter++), messageHandler.getMessage());
//				System.out.println("key"+counter +"                 "+ jedis.get("wkey" +counter ) + jedis.dbSize());
				System.out.println(counter+ " db save ----------> " + messageHandler.getMessage() + "     dbsize----> " + jedis.dbSize());
				
			}
			
			return messageHandler;
		}
		
		@Override
		public boolean willDecode( final String str ) {
			return true;
		}
		
		@Override
		public void destroy() {
		}
	}
	
	//-------------------------------------------------------------------------------------------------------//		
	
	private String username;
	private String message;
	
	public MessageHandler() {
	}
	
	public MessageHandler(final String username, final String message ) {
		this.username = username ;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUsername() {
		return username;
	}

	public void setMessage( final String message ) {
		this.message = message;
	}
	
	public void setUsername( final String username ) {
		this.username = username;
	}

	
	
}