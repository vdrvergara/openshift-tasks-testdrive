package org.jboss.as.quickstarts.tasksrs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mauricio "Maltron" Leal <maltron at gmail dot com> */
@Path("/ping")
public class PingResource {
    
    /**
     * Simple method to respond that this application is ready to go */
    @GET
    public Response ping() {
        return Response.status(Response.Status.OK).build();
    }
}
