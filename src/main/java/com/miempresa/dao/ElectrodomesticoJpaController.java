/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.dao;

import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Electrodomestico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.miempresa.entidades.EnvioElectrodomestico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class ElectrodomesticoJpaController implements Serializable {

    public ElectrodomesticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Electrodomestico electrodomestico) {
        if (electrodomestico.getEnvioElectrodomesticoList() == null) {
            electrodomestico.setEnvioElectrodomesticoList(new ArrayList<EnvioElectrodomestico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EnvioElectrodomestico> attachedEnvioElectrodomesticoList = new ArrayList<EnvioElectrodomestico>();
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomesticoToAttach : electrodomestico.getEnvioElectrodomesticoList()) {
                envioElectrodomesticoListEnvioElectrodomesticoToAttach = em.getReference(envioElectrodomesticoListEnvioElectrodomesticoToAttach.getClass(), envioElectrodomesticoListEnvioElectrodomesticoToAttach.getIdEnvioElectrodomestico());
                attachedEnvioElectrodomesticoList.add(envioElectrodomesticoListEnvioElectrodomesticoToAttach);
            }
            electrodomestico.setEnvioElectrodomesticoList(attachedEnvioElectrodomesticoList);
            em.persist(electrodomestico);
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomestico : electrodomestico.getEnvioElectrodomesticoList()) {
                Electrodomestico oldIdElectrodomesticoOfEnvioElectrodomesticoListEnvioElectrodomestico = envioElectrodomesticoListEnvioElectrodomestico.getIdElectrodomestico();
                envioElectrodomesticoListEnvioElectrodomestico.setIdElectrodomestico(electrodomestico);
                envioElectrodomesticoListEnvioElectrodomestico = em.merge(envioElectrodomesticoListEnvioElectrodomestico);
                if (oldIdElectrodomesticoOfEnvioElectrodomesticoListEnvioElectrodomestico != null) {
                    oldIdElectrodomesticoOfEnvioElectrodomesticoListEnvioElectrodomestico.getEnvioElectrodomesticoList().remove(envioElectrodomesticoListEnvioElectrodomestico);
                    oldIdElectrodomesticoOfEnvioElectrodomesticoListEnvioElectrodomestico = em.merge(oldIdElectrodomesticoOfEnvioElectrodomesticoListEnvioElectrodomestico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Electrodomestico electrodomestico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Electrodomestico persistentElectrodomestico = em.find(Electrodomestico.class, electrodomestico.getIdElectrodomestico());
            List<EnvioElectrodomestico> envioElectrodomesticoListOld = persistentElectrodomestico.getEnvioElectrodomesticoList();
            List<EnvioElectrodomestico> envioElectrodomesticoListNew = electrodomestico.getEnvioElectrodomesticoList();
            List<EnvioElectrodomestico> attachedEnvioElectrodomesticoListNew = new ArrayList<EnvioElectrodomestico>();
            for (EnvioElectrodomestico envioElectrodomesticoListNewEnvioElectrodomesticoToAttach : envioElectrodomesticoListNew) {
                envioElectrodomesticoListNewEnvioElectrodomesticoToAttach = em.getReference(envioElectrodomesticoListNewEnvioElectrodomesticoToAttach.getClass(), envioElectrodomesticoListNewEnvioElectrodomesticoToAttach.getIdEnvioElectrodomestico());
                attachedEnvioElectrodomesticoListNew.add(envioElectrodomesticoListNewEnvioElectrodomesticoToAttach);
            }
            envioElectrodomesticoListNew = attachedEnvioElectrodomesticoListNew;
            electrodomestico.setEnvioElectrodomesticoList(envioElectrodomesticoListNew);
            electrodomestico = em.merge(electrodomestico);
            for (EnvioElectrodomestico envioElectrodomesticoListOldEnvioElectrodomestico : envioElectrodomesticoListOld) {
                if (!envioElectrodomesticoListNew.contains(envioElectrodomesticoListOldEnvioElectrodomestico)) {
                    envioElectrodomesticoListOldEnvioElectrodomestico.setIdElectrodomestico(null);
                    envioElectrodomesticoListOldEnvioElectrodomestico = em.merge(envioElectrodomesticoListOldEnvioElectrodomestico);
                }
            }
            for (EnvioElectrodomestico envioElectrodomesticoListNewEnvioElectrodomestico : envioElectrodomesticoListNew) {
                if (!envioElectrodomesticoListOld.contains(envioElectrodomesticoListNewEnvioElectrodomestico)) {
                    Electrodomestico oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico = envioElectrodomesticoListNewEnvioElectrodomestico.getIdElectrodomestico();
                    envioElectrodomesticoListNewEnvioElectrodomestico.setIdElectrodomestico(electrodomestico);
                    envioElectrodomesticoListNewEnvioElectrodomestico = em.merge(envioElectrodomesticoListNewEnvioElectrodomestico);
                    if (oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico != null && !oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico.equals(electrodomestico)) {
                        oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico.getEnvioElectrodomesticoList().remove(envioElectrodomesticoListNewEnvioElectrodomestico);
                        oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico = em.merge(oldIdElectrodomesticoOfEnvioElectrodomesticoListNewEnvioElectrodomestico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = electrodomestico.getIdElectrodomestico();
                if (findElectrodomestico(id) == null) {
                    throw new NonexistentEntityException("The electrodomestico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Electrodomestico electrodomestico;
            try {
                electrodomestico = em.getReference(Electrodomestico.class, id);
                electrodomestico.getIdElectrodomestico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The electrodomestico with id " + id + " no longer exists.", enfe);
            }
            List<EnvioElectrodomestico> envioElectrodomesticoList = electrodomestico.getEnvioElectrodomesticoList();
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomestico : envioElectrodomesticoList) {
                envioElectrodomesticoListEnvioElectrodomestico.setIdElectrodomestico(null);
                envioElectrodomesticoListEnvioElectrodomestico = em.merge(envioElectrodomesticoListEnvioElectrodomestico);
            }
            em.remove(electrodomestico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Electrodomestico> findElectrodomesticoEntities() {
        return findElectrodomesticoEntities(true, -1, -1);
    }

    public List<Electrodomestico> findElectrodomesticoEntities(int maxResults, int firstResult) {
        return findElectrodomesticoEntities(false, maxResults, firstResult);
    }

    private List<Electrodomestico> findElectrodomesticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Electrodomestico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Electrodomestico findElectrodomestico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Electrodomestico.class, id);
        } finally {
            em.close();
        }
    }

    public int getElectrodomesticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Electrodomestico> rt = cq.from(Electrodomestico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
