package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class Viagembll {
    public static void criar(ViagemEntity viagem){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(viagem);
        em.getTransaction().commit();
    }

    public static void apagar(ViagemEntity viagem){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(viagem);
        em.getTransaction().commit();
    }
    public static ViagemEntity atualizar(ViagemEntity viagem) {
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        ViagemEntity viagemAtualizada = em.merge(viagem);
        em.getTransaction().commit();
        em.refresh(viagemAtualizada); // Atualiza a entidade com os dados do banco de dados
        return viagemAtualizada;
    }

    public static ViagemEntity getViagem(int idViagem){
        EntityManager em = DbConnection.getEntityManager();
        em.clear(); // Limpa o cache de primeiro nível do EntityManager

        List<ViagemEntity> lista = em.createQuery(
                        "SELECT v FROM ViagemEntity v WHERE v.id = :idViagem")
                .setParameter("idViagem", idViagem)
                .getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    public static int getEstadoViagem(int idViagem){
        List<Integer> lista = DbConnection.getEntityManager().createQuery(
                        "SELECT v.idEstadoViagem FROM ViagemEntity v WHERE v.id = :idViagem")
                .setParameter("idViagem", idViagem)
                .getResultList();
        return lista.get(0);

    }
    public static List<ViagemEntity> listarViagensFinalizadas() {
        return DbConnection.getEntityManager().createQuery(
                        "SELECT v FROM ViagemEntity v WHERE v.idEstadoViagem = :idEstadoViagem", ViagemEntity.class)
                .setParameter("idEstadoViagem", 3)
                .getResultList();
    }

    public static List<ViagemEntity> listarViagensPendentesTipoVeiculo(int idTipoVeiculo) {
        return DbConnection.getEntityManager().createQuery(
                        "SELECT v FROM ViagemEntity v WHERE v.idEstadoViagem = :idEstadoViagem AND idTipoVeiculo = :idTipoVeiculo", ViagemEntity.class)
                .setParameter("idEstadoViagem", 1).setParameter("idTipoVeiculo", idTipoVeiculo)
                .getResultList();
    }

    public static List<ViagemEntity> listarViagensPassageiroPorData(int id, Date dataInicio, Date dataFim) {
        return DbConnection.getEntityManager().createQuery(
                        "SELECT v FROM ViagemEntity v WHERE v.idPassageiro = :idPassageiro AND v.dataFim BETWEEN :dataInicio AND :dataFim AND v.dataIni between :dataInicio AND :dataFim", ViagemEntity.class)
                .setParameter("idPassageiro", id).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim)
                .getResultList();
    }



    public static List<ViagemEntity> listarViagensVeiculo(int idVeiculo) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v WHERE v.idVeiculo= :idVeiculo", ViagemEntity.class).setParameter("idVeiculo", idVeiculo).getResultList();
    }

    public static List<ViagemEntity> listarViagensPassageiro(int id) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v WHERE v.idPassageiro= :idPassageiro", ViagemEntity.class).setParameter("idPassageiro", id).getResultList();
    }

    public static List<ViagemEntity> listarViagensCondutor(int idCondutor) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v WHERE v.idVeiculo in (SELECT idVeiculo from VeiculoEntity WHERE idCondutor = :idCondutor)", ViagemEntity.class).setParameter("idCondutor", idCondutor).getResultList();
    }

    public static List<ViagemEntity> listarViagensCondutorPorData(int id, Date dataInicio, Date dataFim) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v WHERE v.idVeiculo in (SELECT idVeiculo from VeiculoEntity WHERE idCondutor = :idCondutor) AND v.dataFim BETWEEN :dataInicio AND :dataFim AND v.dataIni between :dataInicio AND :dataFim", ViagemEntity.class)
                .setParameter("idCondutor", id)
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFim", dataFim).getResultList();
    }

    public static List<ViagemEntity> listarViagensFinalizadasPorData(Date dataInicio, Date dataFim) {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM ViagemEntity v WHERE idEstadoViagem = 3 AND v.dataFim BETWEEN :dataInicio AND :dataFim AND v.dataIni between :dataInicio AND :dataFim", ViagemEntity.class)
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFim", dataFim).getResultList();
    }

}
