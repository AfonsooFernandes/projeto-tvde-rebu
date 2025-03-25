package baseDados.bll;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class TipoVeiculobll {
    public  static List<String> listar(){
        return DbConnection.getEntityManager().createQuery("Select descricao from TipoVeiculoEntity").getResultList();
    }
    public static int getidTipoVeiculo(String descricao){
        // Obtém os resultados da consulta
        List<Integer> resultados = DbConnection.getEntityManager().createQuery("Select id from TipoVeiculoEntity WHERE descricao = :descricao").setParameter("descricao", descricao).getResultList();

        return resultados.get(0);
    }

    public static String getTipoVeiculo(int id){
        List<String> resultados = DbConnection.getEntityManager().createQuery("Select descricao from TipoVeiculoEntity WHERE id = :id").setParameter("id", id).getResultList();
        return resultados.get(0);
    }


    public static BigDecimal getAcrescimo(int id){
        List<BigDecimal> resultados = DbConnection.getEntityManager().createQuery("Select acrescimo from TipoVeiculoEntity WHERE id = :id").setParameter("id", id).getResultList();
        return resultados.get(0);
    }
}
