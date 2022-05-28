package org.techdive.controller;

import org.techdive.dto.CursoDTO;
import org.techdive.mapper.CursoMapper;
import org.techdive.model.Curso;
import org.techdive.service.CursosService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursosController {

    @Inject
    private CursosService service;

    @POST
    public Response inserir(@Valid CursoDTO cursoDto) {
        Curso curso = CursoMapper.INSTANCE.toModel(cursoDto);
        service.inserir(curso);
        return Response.created(URI.create(curso.getCodigo())).entity(cursoDto).build();
    }

    @PUT
    @Path("/{codigo}")
    public Response alterar(@PathParam("codigo") String codigo, @Valid CursoDTO cursoDto) {
        if (!codigo.equals(cursoDto.getCodigo()))
            return Response.status(Response.Status.BAD_REQUEST).entity("Código inconsistente!").build();
        Curso curso = CursoMapper.INSTANCE.toModel(cursoDto);
        service.alterar(curso);
        return Response.ok(curso).build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response remover(@PathParam("codigo") String codigo) {
        service.excluir(codigo);
        return Response.noContent().build();
    }

    @GET
    public Response obter(@QueryParam("sort") String sortBy, @QueryParam("limit") Integer limite) {
        List<Curso> cursos = service.obter(sortBy, limite);
        List<CursoDTO> resp = cursos.stream().map(CursoMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return Response.ok(resp).build();
    }

    @GET
    @Path("/{codigo}")
    public Response obter(@PathParam("codigo") String codigo) {
        Curso curso = service.obter(codigo);
        CursoDTO cursoDto = CursoMapper.INSTANCE.toDTO(curso);
        return Response.ok(cursoDto).build();
    }

}
