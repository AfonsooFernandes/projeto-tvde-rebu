package baseDados.bll;

import baseDados.entity.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Staffbll {
    public static void criar(StaffEntity staff){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(staff);
        em.getTransaction().commit();
    }

    public static void apagar(StaffEntity staff){
        EntityManager em = DbConnection.getEntityManager();
        em.getTransaction().begin();
        em.remove(staff);
        em.getTransaction().commit();
    }

    public  static List<StaffEntity> listar(){
        return DbConnection.getEntityManager().createQuery("from StaffEntity").getResultList();
    }
    public  static List<StaffEntity> listarAdmins(){
        return DbConnection.getEntityManager().createQuery("from StaffEntity WHERE idTipoStaff = 2").getResultList();
    }

    public  static List<StaffEntity> listarResponsaveis(){
        return DbConnection.getEntityManager().createQuery("from StaffEntity WHERE idTipoStaff = 1").getResultList();
    }

    public  static List<StaffEntity> listarPrefix(String prefix){
        return DbConnection.getEntityManager().createQuery("from StaffEntity WHERE numero LIKE :prefix").setParameter("prefix", prefix + "%").getResultList();
    }


}
