package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_staff")
public class TipoStaffEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_staff")
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}