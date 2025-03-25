package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.EntityManager;

import java.util.List;

public class Feedbackbll {
    public static void criar(FeedbackEntity feedback){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(feedback);
        em.getTransaction().commit();
    }

    public static void apagar(FeedbackEntity feedback){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(feedback);
        em.getTransaction().commit();
    }


    public static String listarFeedbackAoCondutor(int idViagem) {
        List<FeedbackEntity> resultList = DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idViagem = :idViagem AND f.descricao LIKE 'Feedback do passageiro sobre o condutor%'",
                        FeedbackEntity.class)
                .setParameter("idViagem", idViagem)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0).getAvaliacao().toString(); // Retorna o feedback encontrado
        } else {
            return "Sem feedback"; // Retorna "Sem feedback" se nenhum feedback for encontrado
        }
    }

    public static String listarFeedbackDoCondutor(int idViagem) {
        List<FeedbackEntity> resultList = DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idViagem = :idViagem AND f.descricao LIKE 'Feedback do condutor sobre o passageiro%'",
                        FeedbackEntity.class)
                .setParameter("idViagem", idViagem)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0).getAvaliacao().toString(); // Retorna o feedback encontrado
        } else {
            return "Sem feedback"; // Retorna "Sem feedback" se nenhum feedback for encontrado
        }
    }

    public static String listarComentarioDoCondutor(int idViagem) {
        List<FeedbackEntity> resultList = DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idViagem = :idViagem AND f.descricao LIKE 'Feedback do condutor sobre o passageiro%'",
                        FeedbackEntity.class)
                .setParameter("idViagem", idViagem)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0).getDescricao(); // Retorna o feedback encontrado
        } else {
            return "Sem Comentario"; // Retorna "Sem feedback" se nenhum feedback for encontrado
        }
    }

    public static String listarComentarioDoPassageiro(int idViagem) {
        List<FeedbackEntity> resultList = DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idViagem = :idViagem AND f.descricao LIKE 'Feedback do passageiro sobre o condutor%'",
                        FeedbackEntity.class)
                .setParameter("idViagem", idViagem)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0).getDescricao(); // Retorna o feedback encontrado
        } else {
            return "Sem Comentario"; // Retorna "Sem feedback" se nenhum feedback for encontrado
        }
    }





}
