package org.jboss.as.quickstarts.tasksrs.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.as.quickstarts.tasksrs.model.User;
import org.jboss.as.quickstarts.tasksrs.model.UserDao;

/**
 * A JAX-RS resource for exposing REST endpoints for User manipulation
 */

@Path("/users")
public class UserResource {
    @Inject
    private UserDao userDao;

}
