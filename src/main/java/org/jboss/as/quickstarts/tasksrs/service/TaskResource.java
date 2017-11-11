/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.tasksrs.service;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
    
import org.jboss.as.quickstarts.tasksrs.model.Task;
import org.jboss.as.quickstarts.tasksrs.model.TaskDao;
import org.jboss.as.quickstarts.tasksrs.model.User;
import org.jboss.as.quickstarts.tasksrs.model.UserDao;

/**
 * A JAX-RS resource for exposing REST endpoints for Task manipulation
 */
@Path("/")
public class TaskResource {
    @Inject
    private UserDao userDao;

    @Inject
    private TaskDao taskDao;

    



    // Utility Methods

    private List<Task> getTasks(User user, String title) {
        return taskDao.getForTitle(user, title);
    }

    private List<Task> getTasks(User user) {
        return taskDao.getAll(user);
    }

    private Task getTask(User user, Long id) {
        for (Task task : taskDao.getAll(user))
            if (task.getId().equals(id))
                return task;

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    private User getUser(SecurityContext context) {
        Principal principal = null;

        if (context != null)
            principal = context.getUserPrincipal();

        if (principal == null)
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);

        return getUser(principal.getName());
    }

    private User getUser(String username) {

        try {
            User user = userDao.getForUsername(username);

            if (user == null) {
                user = new User(username);

                userDao.createUser(user);
            }

            return user;
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }
}
