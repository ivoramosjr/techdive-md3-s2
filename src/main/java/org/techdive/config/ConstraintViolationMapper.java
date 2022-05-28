package org.techdive.config;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            messages.add(violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(messages).build();
    }

}
