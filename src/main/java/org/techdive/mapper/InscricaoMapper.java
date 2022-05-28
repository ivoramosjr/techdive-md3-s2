package org.techdive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.techdive.dto.InscricaoReqDTO;
import org.techdive.dto.InscricaoRespDTO;
import org.techdive.model.Inscricao;

@Mapper
public interface InscricaoMapper {

    InscricaoMapper INSTANCE = Mappers.getMapper(InscricaoMapper.class);

    @Mapping(source = "aluno.matricula", target = "matriculaAluno")
    @Mapping(source = "curso.codigo", target = "codigoCurso")
    InscricaoReqDTO toRequest(Inscricao model);

    @Mapping(source = "matriculaAluno", target = "aluno.matricula")
    @Mapping(source = "codigoCurso", target = "curso.codigo")
    Inscricao toModel(InscricaoReqDTO request);

    @Mapping(source = "aluno.matricula", target = "matriculaAluno")
    @Mapping(source = "curso.codigo", target = "codigoCurso")
    InscricaoRespDTO toResponse(Inscricao model);

    @Mapping(source = "matriculaAluno", target = "aluno.matricula")
    @Mapping(source = "codigoCurso", target = "curso.codigo")
    Inscricao toModel(InscricaoRespDTO response);

}
