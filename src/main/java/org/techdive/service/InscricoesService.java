package org.techdive.service;

import org.techdive.dao.InscricoesDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.Inscricao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class InscricoesService {

    @Inject
    private InscricoesDAO inscricoesDAO;

    public Inscricao inserir(Inscricao inscricao) {
        boolean existente = inscricoesDAO.findAll().stream()
                .anyMatch(i -> i.getAluno().equals(inscricao.getAluno()) && i.getCurso().equals(inscricao.getCurso()));
        if (existente)
            throw new RegistroExistenteException("Inscrição", inscricao.getId().toString());
        inscricoesDAO.save(inscricao);
        return inscricao;
    }

    public void excluir(Integer id)  {
        boolean existente = inscricoesDAO.find(id).isPresent();
        if (!existente)
            throw new RegistroNaoEncontradoException("Inscrição", id.toString());
        inscricoesDAO.delete(id);
    }

    public List<Inscricao> obter() {
        return inscricoesDAO.findAll();
    }

}
