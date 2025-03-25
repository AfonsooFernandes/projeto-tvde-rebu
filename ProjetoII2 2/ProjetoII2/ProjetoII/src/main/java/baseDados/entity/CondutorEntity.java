package baseDados.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "condutor", schema = "public", catalog = "projetoI_TVDE")
public class CondutorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_condutor")
    private int idCondutor;

    @Basic
    @Column(name = "id_nacionalidade")
    private int idNacionalidade;

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "idade")
    private int idade;

    @Basic
    @Column(name = "genero")
    private Character genero;

    @Basic
    @Column(name = "n_cc")
    private String nCc;

    @Basic
    @Column(name = "n_certificado_tvde")
    private String nCertificadoTvde;

    @Basic
    @Column(name = "n_conducao")
    private String nConducao;

    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;

    @Column(name = "id_estado")
    private int idEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public int getIdNacionalidade() {
        return idNacionalidade;
    }

    public void setIdNacionalidade(int idNacionalidade) {
        this.idNacionalidade = idNacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getnCc() {
        return nCc;
    }

    public void setnCc(String nCc) {
        this.nCc = nCc;
    }

    public String getnCertificadoTvde() {
        return nCertificadoTvde;
    }

    public void setnCertificadoTvde(String nCertificadoTvde) {
        this.nCertificadoTvde = nCertificadoTvde;
    }

    public String getnConducao() {
        return nConducao;
    }

    public void setnConducao(String nConducao) {
        this.nConducao = nConducao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CondutorEntity condutor = (CondutorEntity) o;

        if (idCondutor != condutor.idCondutor) return false;
        if (idNacionalidade != condutor.idNacionalidade) return false;
        if (idade != condutor.idade) return false;
        if (nCc != condutor.nCc) return false;
        if (nCertificadoTvde != condutor.nCertificadoTvde) return false;
        if (nConducao != condutor.nConducao) return false;
        if (nome != null ? !nome.equals(condutor.nome) : condutor.nome != null) return false;
        if (genero != null ? !genero.equals(condutor.genero) : condutor.genero != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCondutor;
        result = 31 * result + idNacionalidade;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + idade;
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (nCc != null ? nCc.hashCode() : 0);
        result = 31 * result + (nCertificadoTvde != null ? nCertificadoTvde.hashCode() : 0);
        result = 31 * result + (nConducao != null ? nConducao.hashCode() : 0);
        return result;
    }
}
