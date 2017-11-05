package org.jboss.as.quickstarts.tasksrs.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mauricio "Maltron" Leal <maltron at gmail dot com>
 */
@Path("/whoami")
public class WhoResource {

    private static final Logger LOG = Logger.getLogger(WhoResource.class.getName());
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response whoAmI() {
        String deploymentName = System.getenv("DEPLOYMENT_NAME");
        
        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch(UnknownHostException ex) {
            LOG.log(Level.WARNING, "### UNABLE TO DETERMINE HOSTNAME: {0}", ex.getMessage());
        }
        
        StringBuilder builder = new StringBuilder(hostname);
        if(deploymentName != null) 
            builder.append(" (").append(deploymentName).append(")");
        
        return Response.status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN).entity(builder.toString()).build();
    }

}
