package org.techdive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.techdive.dto.CursoDTO;
import org.techdive.model.Curso;

@Mapper
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    @Mapping(source = "duracao", target = "duracaoEmDias")
    CursoDTO toDTO(Curso model);

    @Mapping(source = "duracaoEmDias", target = "duracao")
    Curso toModel(CursoDTO dto);

}
