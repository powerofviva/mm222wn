package wsserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import config.ServerConfiguration;
//======================================this is SERVER for thesis==========================================================/
public class ServerMain  {
    public static void main( String[] args ) throws Exception {
        Server server = new Server(9090);

        // Create the 'root' Spring application context
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addEventListener(new ContextLoaderListener());
        context.setInitParameter("contextClass",AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter("contextConfigLocation", ServerConfiguration.class.getName());
        final ServletHolder defaultHolder = new ServletHolder( "default", DefaultServlet.class );
        defaultHolder.setInitParameter( "resourceBase", System.getProperty("user.dir") );
        context.addServlet( defaultHolder, "/" );

        server.setHandler(context);
        WebSocketServerContainerInitializer.configureContext(context);

        server.start();
        server.join();	
    }
}
