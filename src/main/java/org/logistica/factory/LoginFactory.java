package org.logistica.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.eclipse.persistence.config.QueryHints;
import org.logistica.bean.Usuario;
import org.logistica.services.ManagerFactory;

public class LoginFactory {

    public EntityManager entityManager() {
        return ManagerFactory.getFactoryManager().getFactory().createEntityManager();
    }

    public EntityManager em = entityManager();

    public int RegistrarUsuario(Usuario usuario) {
        int commitRollback;
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            commitRollback = 1;
        } catch (Exception e) {
            commitRollback = 0;
            em.getTransaction().rollback();
        }
        return commitRollback;
    }

    public Usuario ValidarUsuario(String usuario, String nivelAcceso, String clave) {
        try {
            return (Usuario) em.createNativeQuery(" SELECT a.ID_USUARIO, a.CLAVE, a.NIVEL_ACCESO, a.USUARIO "
                    + " FROM USUARIO a "
                    + " WHERE (a.USUARIO = ?)AND(a.NIVEL_ACCESO = ? )AND(a.CLAVE = ?) ", Usuario.class)
                    .setParameter(1, usuario)
                    .setParameter(2, nivelAcceso)
                    .setParameter(3, clave)
                    .setHint(QueryHints.REFRESH, true)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List ListarUsuario() {
        try {
            return em.createNativeQuery(" SELECT a.ID_USUARIO, a.CLAVE, a.NIVEL_ACCESO, a.USUARIO "
                    + " FROM USUARIO a ", Usuario.class)
                    .setHint(QueryHints.REFRESH, true)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public int EliminarUsuario(Integer idVariable) {
        int Eliminado;
        try {
            Usuario t = em.find(Usuario.class, idVariable);
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
