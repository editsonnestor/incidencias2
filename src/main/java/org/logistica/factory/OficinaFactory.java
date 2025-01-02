package org.logistica.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.eclipse.persistence.config.QueryHints;
import org.logistica.bean.Oficina;
import org.logistica.services.ManagerFactory;

public class OficinaFactory {

    public EntityManager entityManager() {
        return ManagerFactory.getFactoryManager().getFactory().createEntityManager();
    }

    public EntityManager em = entityManager();

    public int RegistrarOficina(Oficina oficina) {
        int commitRollback;
        try {
            em.getTransaction().begin();
            em.persist(oficina);
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public int ActulizarOficina(Oficina oficina) {
        int commitRollback;
        try {
            Oficina t = em.find(Oficina.class, oficina.getIdOficina());
            em.getTransaction().begin();
            if (em.contains(t)) {
                em.merge(oficina);
            }
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public List ListarOficina() {
        try {
            return em.createNativeQuery(" SELECT a.ID_OFICINA, a.COMENTARIO, a.NOMBRE, a.RESPONSABLE "
                    + " FROM OFICINA a ", Oficina.class)
                    .setHint(QueryHints.REFRESH, true)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Oficina BuscarOficina(Integer idVariable) {
        try {
            return (Oficina) em.createNativeQuery(" SELECT a.ID_OFICINA, a.COMENTARIO, a.NOMBRE, a.RESPONSABLE "
                    + " FROM OFICINA a "
                    + " WHERE a.ID_OFICINA = ? ", Oficina.class)
                    .setParameter(1, idVariable)
                    .setHint(QueryHints.REFRESH, true)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Oficina BuscarOficinaID(Oficina oficina) {
        return em.find(Oficina.class, oficina.getIdOficina());
    }

    public int EliminarOficina(Integer idVariable) {
        int Eliminado;
        try {
            Oficina t = em.find(Oficina.class, idVariable);
            em.getTransaction().begin();
            if (em.contains(t)) {
                em.remove(t);
            }
            em.getTransaction().commit();
            Eliminado = 1;
        } catch (Exception e) {
            Eliminado = 0;
            em.getTransaction().commit();
        }
        return Eliminado;
    }

}
