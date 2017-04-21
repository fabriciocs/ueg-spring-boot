package br.com.luasistemas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabricio on 20/04/17.
 */
@Entity
@Table(name = "contato")
public class Contato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String celular;
    @Column
    @Email
    private String email;
    @Column
    private String telefoneFixo;

    @ManyToOne(targetEntity = Pessoa.class)
    @JsonIgnore
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Contato setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato)) return false;

        Contato contato = (Contato) o;

        return getId() == contato.getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contato{");
        sb.append("id=").append(id);
        sb.append(", celular='").append(celular).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telefoneFixo='").append(telefoneFixo).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public long getId() {
        return id;
    }

    public Contato setId(long id) {
        this.id = id;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public Contato setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contato setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public Contato setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
        return this;
    }
}
