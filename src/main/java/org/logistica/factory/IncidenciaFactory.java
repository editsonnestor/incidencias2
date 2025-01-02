package org.logistica.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import java.util.List;
import org.eclipse.persistence.config.QueryHints;
import org.logistica.bean.Equipo;
import org.logistica.bean.Incidencia;
import org.logistica.bean.Oficina;
import org.logistica.services.ManagerFactory;

public class IncidenciaFactory {

    public EntityManager entityManager() {
        return ManagerFactory.getFactoryManager().getFactory().createEntityManager();
    }

    public EntityManager em = entityManager();

    public int RegistrarIncidencia(Incidencia incidencia) {
        int commitRollback;
        try {
            em.getTransaction().begin();
            em.persist(incidencia);
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public int ActulizarEquipo(Incidencia incidencia) {
        int commitRollback;
        try {
            Incidencia t = em.find(Incidencia.class, incidencia.getIdIncidencia());
            em.getTransaction().begin();
            if (em.contains(t)) {
                em.merge(incidencia);
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

    public List ListarIncidencia() {
        try {
            return em.createNativeQuery(" SELECT a.ID_INCIDENCIA,a.FECHA, a.GLOSA, a.PRIORIDAD, a.COMENTARIO, b.NOMBRE "
                    + " FROM INCIDENCIA a JOIN OFICINA b ON a.ID_OFICINA = b.ID_OFICINA "
                    + " WHERE a.ESTADO = 'N' ", Incidencia.class)
                    .setHint(QueryHints.REFRESH, true)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean CambiarEstado(Integer idVariable) {
        try {
            int rowsUpdated = em.createNativeQuery("UPDATE INCIDENCIA "
                    + "SET ESTADO = 'S' "
                    + "WHERE ID_INCIDENCIA = ?")
                    .setParameter(1, idVariable)
                    .executeUpdate();
            return rowsUpdated > 0; 
        } catch (PersistenceException e) {
            return false;
        }
    }

    public List ListarAtencion() {
        try {
            return em.createNativeQuery(" SELECT a.ID_INCIDENCIA,a.FECHA, a.GLOSA, a.PRIORIDAD, a.COMENTARIO, b.NOMBRE, a.ESTADO "
                    + " FROM INCIDENCIA a JOIN OFICINA b ON a.ID_OFICINA = b.ID_OFICINA ", Incidencia.class)
                    .setHint(QueryHints.REFRESH, true)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Incidencia BuscarIncidenciaID(Incidencia incidencia) {
        return em.find(Incidencia.class, incidencia.getIdIncidencia());
    }

    public Equipo BuscarEquipoID(Integer idVariable) {
        return em.find(Equipo.class, idVariable);
    }

    public Oficina BuscarOficinaID(Integer idVariable) {
        return em.find(Oficina.class, idVariable);
    }

    public int EliminarIncidencia(Integer idVariable) {
        int Eliminado;
        try {
            Incidencia t = em.find(Incidencia.class, idVariable);
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
