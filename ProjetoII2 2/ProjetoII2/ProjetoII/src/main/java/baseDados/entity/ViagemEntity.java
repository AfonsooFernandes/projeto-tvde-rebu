package baseDados.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "viagem", schema = "public", catalog = "projetoI_TVDE")
public class ViagemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_viagem")
    private Integer idViagem;
    @Basic
    @Column(name = "id_veiculo")
    private Integer idVeiculo;
    @Basic
    @Column(name = "id_tipo_veiculo")
    private Integer idTipoVeiculo;
    @Basic
    @Column(name = "id_metodo_pagamento")
    private Integer idMetodoPagamento;
    @Basic
    @Column(name = "id_passageiro")
    private Integer idPassageiro;
    @Basic
    @Column(name = "id_estado_viagem")
    private Integer idEstadoViagem;
    @Basic
    @Column(name = "data_ini")
    private Date dataIni;
    @Basic
    @Column(name = "data_fim")
    private Date dataFim;
    @Basic
    @Column(name = "valor_total")
    private BigDecimal valor;
    @Basic
    @Column(name = "pago")
    private boolean pago;
    @Basic
    @Column(name = "valor_km")
    private BigDecimal valorKm;
    @Basic
    @Column(name = "distancia")
    private BigDecimal distancia;
    @Basic
    @Column(name = "origem")
    private String origem;
    @Basic
    @Column(name = "destino")
    private String destino;

    @Basic
    @Column(name = "percent_condutor")
    private BigDecimal percent_condutor;

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Integer getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Integer idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public Integer getIdMetodoPagamento() {
        return idMetodoPagamento;
    }

    public void setIdMetodoPagamento(Integer idMetodoPagamento) {
        this.idMetodoPagamento = idMetodoPagamento;
    }

    public Integer getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(Integer idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public Integer getIdEstadoViagem() {
        return idEstadoViagem;
    }

    public void setIdEstadoViagem(Integer idEstadoViagem) {
        this.idEstadoViagem = idEstadoViagem;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public BigDecimal getValorKm() {
        return valorKm;
    }

    public void setValorKm(BigDecimal valorKm) {
        this.valorKm = valorKm;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getPercent_condutor() {
        return percent_condutor;
    }

    public void setPercent_condutor(BigDecimal percent_condutor) {
        this.percent_condutor = percent_condutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViagemEntity that = (ViagemEntity) o;

        if (idViagem != that.idViagem) return false;
        if (idVeiculo != that.idVeiculo) return false;
        if (idMetodoPagamento != that.idMetodoPagamento) return false;
        if (idPassageiro != that.idPassageiro) return false;
        if (idEstadoViagem != that.idEstadoViagem) return false;
        if (pago != that.pago) return false;
        if (dataIni != null ? !dataIni.equals(that.dataIni) : that.dataIni != null) return false;
        if (dataFim != null ? !dataFim.equals(that.dataFim) : that.dataFim != null) return false;
        if (valor != null ? !valor.equals(that.valor) : that.valor != null) return false;
        if (valorKm != null ? !valorKm.equals(that.valorKm) : that.valorKm != null) return false;
        if (distancia != null ? !distancia.equals(that.distancia) : that.distancia != null) return false;
        if (origem != null ? !origem.equals(that.origem) : that.origem != null) return false;
        if (destino != null ? !destino.equals(that.destino) : that.destino != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idViagem;
        result = 31 * result + idVeiculo;
        result = 31 * result + idMetodoPagamento;
        result = 31 * result + idPassageiro;
        result = 31 * result + idEstadoViagem;
        result = 31 * result + (dataIni != null ? dataIni.hashCode() : 0);
        result = 31 * result + (dataFim != null ? dataFim.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (pago ? 1 : 0);
        result = 31 * result + (valorKm != null ? valorKm.hashCode() : 0);
        result = 31 * result + (distancia != null ? distancia.hashCode() : 0);
        result = 31 * result + (origem != null ? origem.hashCode() : 0);
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
        return result;
    }
}
