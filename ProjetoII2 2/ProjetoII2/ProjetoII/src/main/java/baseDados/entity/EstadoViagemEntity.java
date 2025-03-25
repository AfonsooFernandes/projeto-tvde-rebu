package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_viagem", schema = "public", catalog = "projetoI_TVDE")
public class EstadoViagemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_estado_viagem")
    private int idEstadoViagem;
    @Basic
    @Column(name = "descricao")
    private String descricao;

    public int getIdEstadoViagem() {
        return idEstadoViagem;
    }

    public void setIdEstadoViagem(int idEstadoViagem) {
        this.idEstadoViagem = idEstadoViagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoViagemEntity that = (EstadoViagemEntity) o;

        if (idEstadoViagem != that.idEstadoViagem) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstadoViagem;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }
}
