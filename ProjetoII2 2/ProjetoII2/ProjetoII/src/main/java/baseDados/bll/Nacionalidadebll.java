package baseDados.bll;

import java.util.List;

public class Nacionalidadebll {

    public  static List<String> listar(){
        return DbConnection.getEntityManager().createQuery("Select descricao from NacionalidadeEntity").getResultList();
    }
    public static int getidNacionalidade(String descricao){

        // Obtém os resultados da consulta
        List<Integer> resultados = DbConnection.getEntityManager().createQuery("Select idNacionalidade from NacionalidadeEntity WHERE descricao = :descricao").setParameter("descricao", descricao).getResultList();

       return resultados.get(0);
    }

    public static String getNacionalidade(int id){

        // Obtém os resultados da consulta
        List<String> resultados = DbConnection.getEntityManager().createQuery("Select descricao from NacionalidadeEntity WHERE idNacionalidade = :id").setParameter("id", id).getResultList();

        return resultados.get(0);
    }

}
