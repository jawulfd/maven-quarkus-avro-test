package es.indra.atm.plt.quarkus.avro;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestStreamElementType;

@ApplicationScoped
@Path("/movies")
public class MovieConsumerResource {

  @Channel("movies-from-kafka")
  Multi<Movie> movies;

  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @RestStreamElementType(MediaType.APPLICATION_JSON)
  public Multi<Movie> stream() {
    return movies;
  }
}
