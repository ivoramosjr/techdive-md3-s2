package org.techdive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.techdive.dto.AlunoDTO;
import org.techdive.model.Aluno;

@Mapper
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    AlunoDTO toDTO(Aluno model);

    Aluno toModel(AlunoDTO dto);

}
