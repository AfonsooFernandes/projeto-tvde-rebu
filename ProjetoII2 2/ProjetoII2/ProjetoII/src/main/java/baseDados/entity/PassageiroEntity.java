package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passageiro", schema = "public", catalog = "projetoI_TVDE")
public class PassageiroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_passageiro")
    private int idPassageiro;
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

    @Column(name = "n_cc")
    private String nCc;

    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNCc() {
        return nCc;
    }

    public void setNCc(String nCc) {
        this.nCc = nCc;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassageiroEntity that = (PassageiroEntity) o;

        if (idPassageiro != that.idPassageiro) return false;
        if (idNacionalidade != that.idNacionalidade) return false;
        if (idade != that.idade) return false;
        if (genero != that.genero) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPassageiro;
        result = 31 * result + idNacionalidade;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + idade;
        result = 31 * result + genero;
        return result;
    }
}
