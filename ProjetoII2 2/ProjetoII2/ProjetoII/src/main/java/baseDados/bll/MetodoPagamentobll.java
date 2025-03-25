package baseDados.bll;

import baseDados.entity.*;

import jakarta.persistence.EntityManager;

public class MetodoPagamentobll {
    public static void criar(MetodoPagamentoEntity metodoPagamento){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(metodoPagamento);
        em.getTransaction().commit();
    }

    public static void apagar(MetodoPagamentoEntity metodoPagamento){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(metodoPagamento);
        em.getTransaction().commit();
    }
}

