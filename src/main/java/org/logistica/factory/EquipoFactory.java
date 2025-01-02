package org.logistica.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.eclipse.persistence.config.QueryHints;
import org.logistica.bean.Equipo;
import org.logistica.services.ManagerFactory;

public class EquipoFactory {

    public EntityManager entityManager() {
        return ManagerFactory.getFactoryManager().getFactory().createEntityManager();
    }

    public EntityManager em = entityManager();

    public int RegistrarEquipo(Equipo equipo) {
        int commitRollback;
        try {
            em.getTransaction().begin();
            em.persist(equipo);
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public int ActulizarEquipo(Equipo equipo) {
        int commitRollback;
        try {
            Equipo t = em.find(Equipo.class, equipo.getIdEquipo());
            em.getTransaction().begin();
            if (em.contains(t)) {
                em.merge(equipo);
            }
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public List ListarEquipo() {
        try {
            return em.createNativeQuery(" SELECT a.ID_EQUIPO, a.CODIGO, a.COMENTARIO, a.DETALLE "
                    + " FROM EQUIPO a ", Equipo.class)
                    .setHint(QueryHints.REFRESH, true)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Equipo BuscarEquipo(Integer idVariable) {
        try {
            return (Equipo) em.createNativeQuery(" SELECT a.ID_EQUIPO, a.CODIGO, a.COMENTARIO, a.DETALLE "
                    + " FROM EQUIPO a "
                    + " WHERE a.ID_EQUIPO = ? ", Equipo.class)
                    .setParameter(1, idVariable)
                    .setHint(QueryHints.REFRESH, true)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Equipo BuscarOficinaID(Equipo equipo) {
        return em.find(Equipo.class, equipo.getIdEquipo());
    }

    public int EliminarEquipo(Integer idVariable) {
        int Eliminado;
        try {
            Equipo t = em.find(Equipo.class, idVariable);
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
