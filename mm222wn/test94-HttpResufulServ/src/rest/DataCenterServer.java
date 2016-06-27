package rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

//======================================this is SERVER for thesis==========================================================/
public class DataCenterServer {

    public static void main(String[] args) throws Exception {

        //change port to 8888 for minitor in fiddler
        Server jettyServer = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        jettyServer.setHandler(context);
        ServletHolder servletHolder = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,
                "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                MessageHandler.class.getCanonicalName());
        try {
            jettyServer.start();
//            System.out.println(" open the browser on http://localhost:8080/lnu/mahdi");
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
