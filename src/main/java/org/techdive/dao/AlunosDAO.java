package org.techdive.dao;

import org.techdive.model.Aluno;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class AlunosDAO implements Serializable {

    @PersistenceContext(unitName = "EXERCICIOPU")
    EntityManager em;

    public void save(Aluno aluno) {
        em.persist(aluno);
    }

    public List<Aluno> findAll() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    public Optional<Aluno> find(Integer matricula) {
        Aluno aluno = em.find(Aluno.class, matricula);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    public void delete(Integer matricula) {
        Aluno aluno = em.find(Aluno.class, matricula);
        em.remove(aluno);
    }

    public void update(Aluno alterado) {
        Aluno aluno = em.find(Aluno.class, alterado.getMatricula());
        aluno.setNome(alterado.getNome());
        em.merge(aluno);
    }

}
