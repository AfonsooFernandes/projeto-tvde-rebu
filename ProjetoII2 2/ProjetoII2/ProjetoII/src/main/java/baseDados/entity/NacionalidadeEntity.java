package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nacionalidade", schema = "public", catalog = "projetoI_TVDE")
public class NacionalidadeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_nacionalidade")
    private int idNacionalidade;
    @Basic
    @Column(name = "descricao")
    private String descricao;

    public int getIdNacionalidade() {
        return idNacionalidade;
    }

    public void setIdNacionalidade(int idNacionalidade) {
        this.idNacionalidade = idNacionalidade;
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

        NacionalidadeEntity that = (NacionalidadeEntity) o;

        if (idNacionalidade != that.idNacionalidade) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNacionalidade;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }
}
