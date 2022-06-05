package org.techdive.controller;

import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dto.AlunoDTO;
import org.techdive.mapper.AlunoMapper;
import org.techdive.model.Aluno;
import org.techdive.service.AlunosService;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunosControllerTest {

    @Mock
    private AlunosService alunosService;

    @InjectMocks
    private AlunosController alunosController;

    @Test
    @DisplayName("Quando o aluno informado é válido e não existe, Deve retornar um Response Created")
    void inserir_Sucesso() {
        //GIVEN
        Aluno aluno = AlunoMapper.INSTANCE.toModel(criarAlunoDTO());
        //WHEN
        Response result = alunosController.inserir(criarAlunoDTO());
        //THEN
        assertNotNull(result);
        assertEquals(Response.Status.CREATED, result.getStatusInfo());
        assertInstanceOf(Response.class, result);
        assertDoesNotThrow(() -> alunosService.inserir(aluno));
    }

    @Test
    @DisplayName("Quando a matricula do aluno informado existe, Deve retornar um Response OK")
    void obter_Sucesso() {
        //GIVEN
        Integer matricula = 1;
        //WHEN
        Response result = alunosController.obter(matricula);
        //THEN
        assertNotNull(result);
        assertEquals(Response.Status.OK, result.getStatusInfo());
        assertInstanceOf(Response.class, result);
        assertDoesNotThrow(() -> alunosService.obter(matricula));
    }

    private AlunoDTO criarAlunoDTO() {
        return new AlunoDTO(1, "José");
    }
}