package org.techdive.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RegistroNaoEncontradoException extends WebApplicationException {

    public RegistroNaoEncontradoException(String tipo, String identificador) {
        super(String.format("%s: Registro não encontrado com identificador: %s", tipo, identificador), Response.Status.NOT_FOUND);
    }

}
