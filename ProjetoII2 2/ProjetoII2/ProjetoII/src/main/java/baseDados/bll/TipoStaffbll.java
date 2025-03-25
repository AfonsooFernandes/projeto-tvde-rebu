package baseDados.bll;

import baseDados.entity.PassageiroEntity;
import baseDados.entity.TipoStaffEntity;

import java.util.List;

public class TipoStaffbll {
    public static TipoStaffEntity listarWithId(int id){
        List<TipoStaffEntity> resultList = DbConnection.getEntityManager().createQuery("from TipoStaffEntity WHERE id = :id ").setParameter("id",id).getResultList();
        return resultList.get(0);
    }
    public static List<String> listar(){
        return DbConnection.getEntityManager().createQuery("select tipo from TipoStaffEntity").getResultList();
    }

    public static TipoStaffEntity listarWithTipo(String tipo){
        List<TipoStaffEntity> resultList = DbConnection.getEntityManager().createQuery("from TipoStaffEntity WHERE tipo = :tipo ").setParameter("tipo",tipo).getResultList();
        return resultList.get(0);
    }
}

