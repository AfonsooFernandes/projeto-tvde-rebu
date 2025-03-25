package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.EntityManager;

import java.util.List;

public class EstadoViagembll {
    public static void criar(EstadoViagemEntity estadoViagem){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(estadoViagem);
        em.getTransaction().commit();
    }

    public static void apagar(EstadoViagemEntity estadoViagem){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(estadoViagem);
        em.getTransaction().commit();
    }

    public static String getEstado(int id){

        // Obtém os resultados da consulta
        List<String> resultados = DbConnection.getEntityManager().createQuery("Select descricao from EstadoViagemEntity WHERE id = :id").setParameter("id", id).getResultList();

        return resultados.get(0);
    }
}
