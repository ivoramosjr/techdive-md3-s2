package org.techdive.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dto.CursoDTO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.mapper.CursoMapper;
import org.techdive.model.Curso;
import org.techdive.service.CursosService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CursosControllerTest {

    @Mock
    private CursosService cursosService;

    @InjectMocks
    private CursosController cursosController;

    @Test
    @DisplayName("Quando o curso informado é válido e não existe, Deve retornar um Response OK")
    void inserir_Sucesso() {
        //GIVEN
        Curso curso = CursoMapper.INSTANCE.toModel(criarCursoDTO());
        //WHEN
        Response result = cursosController.inserir(criarCursoDTO());
        //THEN
        assertNotNull(result);
        assertEquals(Response.Status.CREATED, result.getStatusInfo());
        assertInstanceOf(Response.class, result);
        assertDoesNotThrow(() -> cursosService.inserir(curso));
    }

    @Test
    @DisplayName("Quando o id do curso existe, Deve excluir curso e retornar um Response OK")
    void remover_Sucesso() {
        //GIVEN
        String codigo = "1";
        //WHEN
        Response result = cursosController.remover(codigo);
        //THEN
        assertNotNull(result);
        assertEquals(Response.Status.NO_CONTENT, result.getStatusInfo());
        assertInstanceOf(Response.class, result);
        assertDoesNotThrow(() -> cursosService.excluir(codigo));
    }

    private CursoDTO criarCursoDTO(){
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setAssunto("assunto");
        cursoDTO.setDuracaoEmDias(10);
        cursoDTO.setCodigo("CODIGO");
        return cursoDTO;
    }
}