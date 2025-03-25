package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.EntityManager;

import java.util.List;

public class Passageirobll {
    public static void criar(PassageiroEntity passageiro){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(passageiro);
        em.getTransaction().commit();
    }

    public static void apagar(PassageiroEntity passageiro){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(passageiro);
        em.getTransaction().commit();
    }

    public  static List<PassageiroEntity> listar(){
        return DbConnection.getEntityManager().createQuery("from PassageiroEntity ").getResultList();
    }

    public  static PassageiroEntity listarWithId(int id){
        List<PassageiroEntity> resultList = DbConnection.getEntityManager().createQuery("from PassageiroEntity WHERE idPassageiro = :id ").setParameter("id",id).getResultList();
        return resultList.get(0);
    }

    public List<FeedbackEntity> listarFeedbacks(int idPassageiro) {
        return DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idPassageiro = :idPassageiro", FeedbackEntity.class)
                .setParameter("idPassageiro", idPassageiro)
                .getResultList();
    }

    public static String getAvaliacaoWithId(int id){
        Double media = (Double) DbConnection.getEntityManager()
                .createQuery("SELECT AVG(avaliacao) FROM FeedbackEntity WHERE idPassageiro = :id AND descricao LIKE 'Feedback do condutor sobre o passageiro%'")
                .setParameter("id", id)
                .getSingleResult();

        if (media != null) {
            return String.valueOf(media);
        } else {
            return "Sem feedbacks";
        }
    }
}

