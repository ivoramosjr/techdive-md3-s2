package org.techdive.dao;

import org.techdive.model.Inscricao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class InscricoesDAO {

    @PersistenceContext(unitName = "EXERCICIOPU")
    EntityManager em;


    public void save(Inscricao inscricao) {
        em.persist(inscricao);
    }

    public List<Inscricao> findAll() {
        return em.createQuery("SELECT i FROM Inscricao i", Inscricao.class).getResultList();
    }

    public Optional<Inscricao> find(Integer id) {
        Inscricao inscricao = em.find(Inscricao.class, id);
        return inscricao != null ? Optional.of(inscricao) : Optional.empty();
    }

    public void delete(Integer id) {
        Inscricao insc = em.find(Inscricao.class, id);
        em.remove(insc);
    }

}
