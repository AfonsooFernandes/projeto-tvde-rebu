package baseDados.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_pedido")
public class EstadoPedidoEntity {
    @Id
    @Column(name = "id_estado", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = Integer.MAX_VALUE)
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}