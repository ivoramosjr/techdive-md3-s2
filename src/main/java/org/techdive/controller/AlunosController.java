package org.techdive.controller;

import org.techdive.dto.AlunoDTO;
import org.techdive.mapper.AlunoMapper;
import org.techdive.model.Aluno;
import org.techdive.service.AlunosService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunosController {

    @Inject
    private AlunosService service;


    @POST
    public Response inserir(@Valid AlunoDTO alunoDTO) {
        Aluno aluno = AlunoMapper.INSTANCE.toModel(alunoDTO);
        service.inserir(aluno);
        return Response.created(URI.create(aluno.getMatricula().toString())).entity(alunoDTO).build();
    }

    @PUT
    @Path("/{matricula}")
    public Response alterar(@PathParam("matricula") Integer matricula, @Valid AlunoDTO alunoDto) {
        if (!matricula.equals(alunoDto.getMatricula()))
            return Response.status(Response.Status.BAD_REQUEST).entity("Matrícula inconsistente!").build();
        Aluno aluno = AlunoMapper.INSTANCE.toModel(alunoDto);
        service.alterar(aluno);
        return Response.ok(alunoDto).build();
    }

    @DELETE
    @Path("/{matricula}")
    public Response remover(@PathParam("matricula") Integer matricula) {
        service.excluir(matricula);
        return Response.noContent().build();
    }

    @GET
    public Response obterAlunos(@QueryParam("nome") String nome) {
        List<Aluno> alunos = service.obter(nome);
        List<AlunoDTO> resp = alunos.stream().map(AlunoMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return Response.ok(resp).build();
    }

    @GET
    @Path("/{matricula}")
    public Response obter(@PathParam("matricula") Integer matricula) {
        Aluno aluno = service.obter(matricula);
        AlunoDTO resp = AlunoMapper.INSTANCE.toDTO(aluno);
        return Response.ok(resp).build();
    }

}
