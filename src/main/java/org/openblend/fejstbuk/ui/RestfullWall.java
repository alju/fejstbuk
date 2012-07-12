package org.openblend.fejstbuk.ui;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.openblend.fejstbuk.domain.Owned;
import org.openblend.fejstbuk.domain.Post;
import org.openblend.fejstbuk.domain.Status;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@Path("/wall")
@Local
public interface RestfullWall {
    @GET
    @Path("/activity/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Status> activity(@PathParam("userID") long userID) throws IOException;
}
