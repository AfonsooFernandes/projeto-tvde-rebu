package baseDados.bll;

import java.util.List;

public class EstadoPedidobll {
    public  static List<String> listar(){
        return DbConnection.getEntityManager().createQuery("Select estado from EstadoPedidoEntity ").getResultList();
    }
    public static int getIdEstado(String estado){

        // Obtém os resultados da consulta
        List<Integer> resultados = DbConnection.getEntityManager().createQuery("Select id from EstadoPedidoEntity WHERE estado = :estado").setParameter("estado", estado).getResultList();

        return resultados.get(0);
    }

    public static String getEstado(int id){

        // Obtém os resultados da consulta
        List<String> resultados = DbConnection.getEntityManager().createQuery("Select estado from EstadoPedidoEntity WHERE id = :id").setParameter("id", id).getResultList();

        return resultados.get(0);
    }


}
