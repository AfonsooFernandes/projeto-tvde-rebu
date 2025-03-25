package baseDados.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_veiculo")
public class TipoVeiculoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_veiculo")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "acrescimo")
    private BigDecimal acrescimo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return descricao;
    }

    public void setTipo(String descricao) {
        this.descricao = descricao;
    }

}