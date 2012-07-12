package org.openblend.fejstbuk.ui.demo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 12.7.12
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
@Path("/time")
@Stateless
public class RESTTime {

    @GET
    @Path("/current")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrentTime() {
        return new Date().toString();
    }
}
