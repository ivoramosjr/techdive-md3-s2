package org.techdive.dao;

import org.techdive.model.Curso;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class CursosDAO {

    @PersistenceContext(unitName = "EXERCICIOPU")
    EntityManager em;


    public void save(Curso curso) {
        em.persist(curso);
    }

    public List<Curso> findAll() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    public Optional<Curso> find(String codigo) {
        Curso curso = em.find(Curso.class, codigo);
        return curso != null ? Optional.of(curso) : Optional.empty();
    }

    public void delete(String codigo) {
        Curso curso = em.find(Curso.class, codigo);
        em.remove(curso);
    }

    public void update(Curso alterado) {
        Curso curso = em.find(Curso.class, alterado.getCodigo());
        curso.setAssunto(alterado.getAssunto());
        curso.setDuracao(alterado.getDuracao());
        em.merge(curso);
    }

}
