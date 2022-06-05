package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.CursosDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.model.Curso;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CursosServiceTest {

    @Mock
    private CursosDAO dao;

    @InjectMocks
    private CursosService service;

    @Test
    @DisplayName("Quando id existente for válido e existe, Deve retornar um curso instanciado")
    void obter_Sucesso() {
        //GIVEN
        Curso curso = new Curso();
        setParametrosCurso(curso);
        Mockito.when(dao.find(curso.getCodigo())).thenReturn(Optional.of(curso));
        //WHEN
        Curso resultado = service.obter("1");
        assertNotNull(resultado);
        assertInstanceOf(Curso.class, resultado);
    }

    @Test
    @DisplayName("Quando o curso não existe, Deve inserir o curso na base de dados")
    void inserir_Sucesso() {
        //GIVEN
        Curso curso = new Curso();
        setParametrosCurso(curso);
        //WHEN
        Mockito.when(dao.find(Mockito.anyString())).thenReturn(Optional.empty());
        //THEN
        assertDoesNotThrow(() -> service.inserir(curso));
    }

    @Test
    @DisplayName("Quando o curso exite, Deve soltar uma exception")
    void inserir_Falha(){
        //GIVEN
        Curso curso = new Curso();
        setParametrosCurso(curso);
        //WHEN
        Mockito.when(dao.find(Mockito.anyString())).thenReturn(Optional.of(curso));
        //THEN
        assertThrows(RegistroExistenteException.class, () -> service.inserir(curso));
    }

    private void setParametrosCurso(Curso curso){
        curso.setCodigo("1");
        curso.setAssunto("Assunto");
        curso.setDuracao(10);
    }

}