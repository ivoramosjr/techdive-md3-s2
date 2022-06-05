package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.AlunosDAO;
import org.techdive.dao.CursosDAO;
import org.techdive.dao.InscricoesDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.model.Aluno;
import org.techdive.model.Curso;
import org.techdive.model.Inscricao;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InscricoesServiceTest {

    @Mock
    private InscricoesDAO inscricoesDAO;

    @InjectMocks
    private InscricoesService inscricoesService;

    @Test
    @DisplayName("Quando o id existe, Então exclui o registro")
    void excluir_Sucesso() {
        //GIVEN
        Integer id = 1;
        Inscricao inscricao = createInscricao();
        inscricao.setId(id);
        //WHEN
        Mockito.when(inscricoesDAO.find(Mockito.anyInt())).thenReturn(Optional.of(inscricao));
        //THEN
        assertDoesNotThrow(() -> inscricoesService.excluir(id));
    }

    @Test
    @DisplayName("Quando não existe inscrição com aluno e curso, Então adicionar a inscrição na base de dados")
    void inserir_Sucesso() {
        //GIVEN
        Inscricao inscricao = createInscricao();
        //WHEN
        Mockito.lenient().when(inscricoesDAO.find(Mockito.anyInt())).thenReturn(Optional.empty());
        //THEN
        assertDoesNotThrow(() -> inscricoesService.inserir(inscricao));
    }

    private Inscricao createInscricao(){
        Inscricao inscricao = new Inscricao();
        inscricao.setId(1);
        inscricao.setAluno(new Aluno());
        inscricao.setCurso(new Curso());
        return inscricao;
    }
}