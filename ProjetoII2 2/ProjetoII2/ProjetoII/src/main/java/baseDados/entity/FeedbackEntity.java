package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback", schema = "public", catalog = "projetoI_TVDE")
public class FeedbackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_feedback")
    private int idFeedback;
    @Basic
    @Column(name = "id_condutor")
    private int idCondutor;
    @Basic
    @Column(name = "id_viagem")
    private int idViagem;
    @Basic
    @Column(name = "id_passageiro")
    private int idPassageiro;
    @Basic
    @Column(name = "descricao")
    private String descricao;
    @Basic
    @Column(name = "avaliacao")
    private Integer avaliacao;

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;

        if (idFeedback != that.idFeedback) return false;
        if (idCondutor != that.idCondutor) return false;
        if (idViagem != that.idViagem) return false;
        if (idPassageiro != that.idPassageiro) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (avaliacao != null ? !avaliacao.equals(that.avaliacao) : that.avaliacao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFeedback;
        result = 31 * result + idCondutor;
        result = 31 * result + idViagem;
        result = 31 * result + idPassageiro;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (avaliacao != null ? avaliacao.hashCode() : 0);
        return result;
    }
}
