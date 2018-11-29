import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class Admin {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9998/students");

        webTarget.request().post(Entity.entity("Srankio", MediaType.TEXT_PLAIN));
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        @SuppressWarnings("unchecked")
        List<String> response = invocationBuilder.get(List.class);

        response.forEach(System.out::println);
    }
}