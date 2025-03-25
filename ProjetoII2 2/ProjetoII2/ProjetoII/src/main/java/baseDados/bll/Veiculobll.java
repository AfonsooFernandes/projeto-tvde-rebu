package baseDados.bll;
import baseDados.entity.*;

import jakarta.persistence.EntityManager;

import java.util.List;

public class Veiculobll {
    public static void criar(VeiculoEntity veiculo){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(veiculo);
        em.getTransaction().commit();
    }

    public static void apagar(VeiculoEntity veiculo){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(veiculo);
        em.getTransaction().commit();
    }
    public static void atualizar(VeiculoEntity veiculo) {
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(veiculo);
        em.getTransaction().commit();
    }
    public  static List<VeiculoEntity> listar(){
        return DbConnection.getEntityManager().createQuery("from VeiculoEntity ").getResultList();
    }

    public static List<VeiculoEntity> listarVeiculosPendentes() {
        return DbConnection.getEntityManager().createQuery("SELECT v FROM VeiculoEntity v WHERE v.idEstado = :idEstado", VeiculoEntity.class).setParameter("idEstado", 1).getResultList();
    }

    public static VeiculoEntity getWithId(int id){
        List<VeiculoEntity> result = DbConnection.getEntityManager().createQuery("from VeiculoEntity WHERE idVeiculo = :id").setParameter("id",id).getResultList();
        return result.get(0);
    }
    public  static List<VeiculoEntity> listarMatricula(String matricula){
        return DbConnection.getEntityManager().createQuery("from VeiculoEntity WHERE matricula LIKE :matricula").setParameter("matricula", matricula + "%").getResultList();
    }



    public static void alterarEstadoVeiculo(int idVeiculo, int novoEstado) {
        try {

            DbConnection.getEntityTransaction().begin();

            VeiculoEntity veiculo = DbConnection.getEntityManager().find(VeiculoEntity.class, idVeiculo);

            if (veiculo != null) {
                veiculo.setIdEstado(novoEstado);
                DbConnection.getEntityManager().merge(veiculo);
                DbConnection.getEntityTransaction().commit();
                System.out.println("Estado do veiculo alterado com sucesso.");
            } else {
                System.out.println("veiculo não encontrado.");
            }
        } catch (Exception e) {
            if (DbConnection.getEntityTransaction().isActive()) {
                DbConnection.getEntityTransaction().rollback();
            }
            System.out.println("Erro ao alterar estado do veiculo: " + e.getMessage());
        } finally {
            DbConnection.getEntityManager().close();
        }
    }


    public static List<VeiculoEntity> listarVeiculosCondutor(int idCondutor){
        return DbConnection.getEntityManager().createQuery("SELECT v from VeiculoEntity v where v.idCondutor = :idCondutor", VeiculoEntity.class).setParameter("idCondutor", idCondutor).getResultList();
    }
    public static List<VeiculoEntity> listarVeiculosRegistadosCondutor(int idCondutor){
        return DbConnection.getEntityManager().createQuery("SELECT v from VeiculoEntity v where v.idCondutor = :idCondutor AND v.idEstado = 2", VeiculoEntity.class).setParameter("idCondutor", idCondutor).getResultList();
    }


    public static VeiculoEntity listarVeiculosPendentesMatricula(String matricula){
        List<VeiculoEntity>  resultList =DbConnection.getEntityManager().createQuery("SELECT v from VeiculoEntity v where v.idEstado = 1 AND v.matricula = :matricula", VeiculoEntity.class).setParameter("matricula", matricula).getResultList();
        return resultList.get(0);
    }
    public static VeiculoEntity listarVeiculosMatricula(String matricula){
        List<VeiculoEntity>  resultList =DbConnection.getEntityManager().createQuery("SELECT v from VeiculoEntity v where v.matricula = :matricula", VeiculoEntity.class).setParameter("matricula", matricula).getResultList();
        return resultList.get(0);
    }

    public static List<VeiculoEntity> listarVeiculoID(int idVeiculo){
        return DbConnection.getEntityManager().createQuery("SELECT v from VeiculoEntity v where v.idVeiculo = :idVeiculo", VeiculoEntity.class).setParameter("idVeiculo", idVeiculo).getResultList();
    }


}
