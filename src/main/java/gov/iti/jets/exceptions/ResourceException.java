package gov.iti.jets.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ResourceException extends WebApplicationException {

    public ResourceException(String message, Response.Status status) {
        super(Response.status(status).entity(message).build());
    }

    public ResourceException(String message, Response.Status status, Throwable cause) {
        super(cause, Response.status(status).entity(message).build());
    }
}
