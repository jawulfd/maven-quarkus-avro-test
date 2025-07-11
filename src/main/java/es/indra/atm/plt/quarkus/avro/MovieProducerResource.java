package es.indra.atm.plt.quarkus.avro;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Path("/movies")
public class MovieProducerResource {
  private static final Logger LOGGER = Logger.getLogger(MovieProducerResource.class);

  @Channel("movies-to-kafka")
  Emitter<Movie> emitter;

  @POST
  @Path("/add")
  public Response enqueueMovie(Movie movie) {
    LOGGER.infof("Sending movie %s to Kafka", movie.getTitle());
    emitter.send(movie);
    return Response.accepted().build();
  }
}
