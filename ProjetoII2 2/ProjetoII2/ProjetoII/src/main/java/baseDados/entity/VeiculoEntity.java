package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo", schema = "public", catalog = "projetoI_TVDE")
public class VeiculoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_veiculo")
    private int idVeiculo;
    @Basic
    @Column(name = "id_condutor")
    private int idCondutor;
    @Basic
    @Column(name = "matricula")
    private String matricula;
    @Basic
    @Column(name = "marca")
    private String marca;
    @Basic
    @Column(name = "modelo")
    private String modelo;
    @Basic
    @Column(name = "ano_fabrico")
    private String anoFabrico;

    @Column(name = "id_estado")
    private int idEstado;

    @Column(name = "id_tipo_veiculo")
    private int idTipoVeiculo;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoFabrico() {
        return anoFabrico;
    }

    public void setAnoFabrico(String anoFabrico) {
        this.anoFabrico = anoFabrico;
    }

    public int getIdTipoVeiculo(){
        return this.idTipoVeiculo;
    }

    public void setIdTipoVeiculo(int idTipoVeiculo){
        this.idTipoVeiculo = idTipoVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeiculoEntity that = (VeiculoEntity) o;

        if (idVeiculo != that.idVeiculo) return false;
        if (idCondutor != that.idCondutor) return false;
        if (anoFabrico != null ? !anoFabrico.equals(that.anoFabrico) : that.anoFabrico != null) return false;
        if (matricula != null ? !matricula.equals(that.matricula) : that.matricula != null) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;
        if (modelo != null ? !modelo.equals(that.modelo) : that.modelo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVeiculo;
        result = 31 * result + idCondutor;
        result = 31 * result + (matricula != null ? matricula.hashCode() : 0);
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (anoFabrico != null ? anoFabrico.hashCode() : 0);
        return result;
    }
}
