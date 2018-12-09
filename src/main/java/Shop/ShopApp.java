package Shop;

import java.net.URI;
import java.sql.*;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ShopApp {
    private final static int port = 9998;
    private final static String host="http://localhost/";
    private Connection c;
    public static void main(String[] args) {
       /* URI baseUri = UriBuilder.fromUri(host).port(port).build();
        ResourceConfig config = new ResourceConfig(StudentsKeeper.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);*/


        ShopAppController sac = new ShopAppController();
        sac.mainLoop();

    }


}