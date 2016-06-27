package sse;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;
//======================================this is SERVER for thesis==========================================================/
public class App {
	
	public static final String ROOT_PATH = "server-sent-events";

    public static void main(String[] args) {
        try {
            final ResourceConfig resourceConfig = new ResourceConfig(ServerSentEventsResource.class, SseFeature.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:7070/"), resourceConfig, false);
//            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//                @Override
//                public void run() {  server.shutdownNow();     }         }));
            server.start();
            System.out.println("started in http://localhost:7070/server-sent-events/domains/1");
            Thread.currentThread().join();
        } 
        		catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}