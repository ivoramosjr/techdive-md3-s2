package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.AlunosDAO;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.Aluno;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunosServiceTest {

    @Mock
    private AlunosDAO alunosDAO;

    @InjectMocks
    private AlunosService service;

    @Test
    @DisplayName("Quando o aluno é valido e não existe, Deve inserir o aluno na base de dados")
    void inserir_Sucesso() {
        //GIVEN
        Aluno aluno = criarAluno();
        //WHEN
        Mockito.when(alunosDAO.find(Mockito.anyInt())).thenReturn(Optional.empty());
        //THEN
        assertDoesNotThrow(() -> service.inserir(aluno));
    }

    @Test
    @DisplayName("Quando o aluno é valido e existe, Deve alterar o aluno na base de dados")
    void alterar_Sucesso() {
        //GIVEN
        Aluno aluno = criarAluno();
        //WHEN
        Mockito.when(alunosDAO.find(Mockito.anyInt())).thenReturn(Optional.of(aluno));
        //THEN
        assertDoesNotThrow(() -> service.alterar(aluno));
    }

    @Test
    @DisplayName("Quando o aluno é valido e não existe, Deve soltar uma exceção")
    void alterar_Falha() {
        //GIVEN
        Aluno aluno = criarAluno();
        //WHEN
        Mockito.when(alunosDAO.find(Mockito.anyInt())).thenReturn(Optional.empty());
        //THEN
        assertThrows(RegistroNaoEncontradoException.class,() -> service.alterar(aluno));
    }

    private Aluno criarAluno(){
        Aluno aluno = new Aluno();
        aluno.setMatricula(1);
        aluno.setNome("Jorge");
        return aluno;
    }
}