package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Condutorbll {
    public static void criar(CondutorEntity condutor){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(condutor);
        em.getTransaction().commit();
    }

    public static void apagar(CondutorEntity condutor){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(condutor);
        em.getTransaction().commit();
    }
    public static void atualizar(CondutorEntity condutor) {
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(condutor);
        em.getTransaction().commit();
    }

    public  static List<CondutorEntity> listar(){
        return DbConnection.getEntityManager().createQuery("from CondutorEntity ").getResultList();
    }

    public static String getAvaliacaoWithId(int id){
        Double media = (Double) DbConnection.getEntityManager()
                .createQuery("SELECT AVG(avaliacao) FROM FeedbackEntity WHERE idCondutor = :id AND descricao LIKE 'Feedback do passageiro sobre o condutor%'")
                .setParameter("id", id)
                .getSingleResult();

        if (media != null) {
            return String.valueOf(media);
        } else {
            return "Sem feedbacks";
        }
    }

    public  static CondutorEntity getWithId(int id){
        List<CondutorEntity> result = DbConnection.getEntityManager().createQuery("from CondutorEntity WHERE idCondutor = :id").setParameter("id",id).getResultList();
        return result.get(0);
    }

    public  static List<CondutorEntity> listarPrefix(String prefix){
        return DbConnection.getEntityManager().createQuery("from CondutorEntity WHERE nCc LIKE :prefix").setParameter("prefix", prefix + "%").getResultList();
    }

    public static List<CondutorEntity> listarCondutoresPendentes() {
        return DbConnection.getEntityManager().createQuery("SELECT c FROM CondutorEntity c WHERE c.idEstado = :idEstado", CondutorEntity.class).setParameter("idEstado", 1).getResultList();
    }

    public  List<CondutorEntity> listarWithName(String name){
        return DbConnection.getEntityManager().createQuery("from CondutorEntity where nome like ?1").setParameter(1, name).getResultList();
    }

    public  List<ViagemEntity> listarViagens(int idCondutor){
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v " +
                "JOIN VeiculoEntity ve ON v.idVeiculo = ve.idVeiculo " +
                "JOIN CondutorEntity c ON ve.idCondutor = c.idCondutor " +
                "WHERE c.idCondutor = :idCondutor", ViagemEntity.class).setParameter("idCondutor", idCondutor).getResultList();
    }

    public  List<FeedbackEntity> listarFeedbacks(int idCondutor) {
        return DbConnection.getEntityManager().createQuery(
                        "SELECT f FROM FeedbackEntity f " +
                                "WHERE f.idCondutor = :idCondutor", FeedbackEntity.class)
                .setParameter("idCondutor", idCondutor)
                .getResultList();
    }



    public  void aceitarViagem(int idViagem){
        try {
            DbConnection.getEntityTransaction().begin();
            ViagemEntity viagem = DbConnection.getEntityManager().find(ViagemEntity.class, idViagem);
            if (viagem != null) {
                viagem.setIdEstadoViagem(4);
                DbConnection.getEntityManager().merge(viagem);
                DbConnection.getEntityTransaction().commit();
                System.out.println("Estado da viagem alterado com sucesso.");
            } else {
                System.out.println("Viagem não encontrada.");
            }
        } catch (Exception e) {
            if (DbConnection.getEntityTransaction().isActive()) {
                DbConnection.getEntityTransaction().rollback();
            }
            System.out.println("Erro ao alterar estado da viagem: " + e.getMessage());
        } finally {
            DbConnection.getEntityManager().close();
        }
    }

    public static void alterarEstadoCondutor(int idCondutor, int novoEstado) {
        try {

            DbConnection.getEntityTransaction().begin();

            CondutorEntity condutor = DbConnection.getEntityManager().find(CondutorEntity.class, idCondutor);

            if (condutor != null) {
                condutor.setIdEstado(novoEstado);
                DbConnection.getEntityManager().merge(condutor);
                DbConnection.getEntityTransaction().commit();
                System.out.println("Estado do condutor alterado com sucesso.");
            } else {
                System.out.println("Condutor não encontrado.");
            }
        } catch (Exception e) {
            if (DbConnection.getEntityTransaction().isActive()) {
                DbConnection.getEntityTransaction().rollback();
            }
            System.out.println("Erro ao alterar estado do condutor: " + e.getMessage());
        } finally {
            DbConnection.getEntityManager().close();
        }
    }



    /*public static List<ViagemEntity> getViagensPendentesPerto(CondutorEntity condutor) {
        //Consultar o banco de dados ou a camada de acesso a dados para obter as viagens pendentes
        List<ViagemEntity> viagensPendentes = Viagembll.listarViagensPendentes();

        //Filtrar as viagens pendentes para aquelas dentro de um raio de 10km da localização do condutor
        List<ViagemEntity> viagensPerto = new ArrayList<>();
        for (ViagemEntity viagem : viagensPendentes) {
            //Calcular a distância entre a viagem e a localização atual do condutor
            double distancia = calcularDistanciaCondutorViagem(condutor.getLatitudeAtual(), condutor.getLongitudeAtual(), viagem.getLatIni(), viagem.getLongIni());
            if (distancia <= 10.0) { // Raio de 10km
                viagensPerto.add(viagem);
            }
        }

        //Retornar a lista de viagens pendentes próximas ao condutor
        return viagensPerto;
    }

     */

    private static double calcularDistanciaCondutorViagem(BigDecimal latCondutor, BigDecimal lonCondutor, BigDecimal latViagem, BigDecimal lonViagem) {
        // Fórmula Haversine para calcular a distância entre duas coordenadas de latitude e longitude
        final double RAIO_TERRA = 6371.0; // Raio médio da Terra em quilômetros

        double dLat = Math.toRadians(latViagem.doubleValue() - latCondutor.doubleValue());
        double dLon = Math.toRadians(lonViagem.doubleValue() - lonCondutor.doubleValue());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latCondutor.doubleValue())) * Math.cos(Math.toRadians(latViagem.doubleValue())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RAIO_TERRA * c;
    }

    public static List<VeiculoEntity> listarVeiculosCondutor(int idCondutor) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM VeiculoEntity v WHERE v.idCondutor= :idCondutor", VeiculoEntity.class).setParameter("idCondutor", idCondutor).getResultList();
    }


}
