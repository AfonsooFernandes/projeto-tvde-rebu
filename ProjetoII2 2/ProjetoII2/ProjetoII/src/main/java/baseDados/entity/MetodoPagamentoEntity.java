package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "metodo_pagamento", schema = "public", catalog = "projetoI_TVDE")
public class MetodoPagamentoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_metodo_pagamento")
    private int idMetodoPagamento;
    @Basic
    @Column(name = "dados_pagamento")
    private String dadosPagamento;

    public int getIdMetodoPagamento() {
        return idMetodoPagamento;
    }

    public void setIdMetodoPagamento(int idMetodoPagamento) {
        this.idMetodoPagamento = idMetodoPagamento;
    }

    public String getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(String dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetodoPagamentoEntity that = (MetodoPagamentoEntity) o;

        if (idMetodoPagamento != that.idMetodoPagamento) return false;
        if (dadosPagamento != null ? !dadosPagamento.equals(that.dadosPagamento) : that.dadosPagamento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMetodoPagamento;
        result = 31 * result + (dadosPagamento != null ? dadosPagamento.hashCode() : 0);
        return result;
    }
}
