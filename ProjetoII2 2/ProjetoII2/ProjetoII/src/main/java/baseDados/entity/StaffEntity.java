package baseDados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class StaffEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_staff", nullable = false)
    private Integer id;


    @Column(name = "id_tipo_staff")
    private int idTipoStaff;

    @Column(name = "numero")
    private String numero;

    @Column(name = "password")
    private String password;
    @Column(name = "nome")
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdTipoStaff() {
        return idTipoStaff;
    }

    public void setIdTipoStaff(int idTipoStaff) {
        this.idTipoStaff = idTipoStaff;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String username) {
        this.numero = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}