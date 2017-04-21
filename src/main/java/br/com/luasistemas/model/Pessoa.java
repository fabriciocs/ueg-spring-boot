package br.com.luasistemas.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fabricio on 20/04/17.
 */
@Entity
@Table(name="pessoa")
public class Pessoa extends ResourceSupport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Size(min = 4)
    private String nome;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    @Column(unique = true)
    private String cpf;

    @Column
    private Date dataNascimento;

    @Column
    private String cep;

    @Column
    private String endereco;

    @OneToMany(mappedBy = "pessoa", cascade = {CascadeType.ALL})
    private List<Contato> contatos = new ArrayList<>(0);


    public Pessoa setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Pessoa setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Pessoa setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Pessoa setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public Pessoa setContatos(List<Contato> contatos) {
        this.contatos = contatos;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;

        Pessoa pessoa = (Pessoa) o;

        return getCpf() != null ? getCpf().equals(pessoa.getCpf()) : pessoa.getCpf() == null;
    }

    @Override
    public int hashCode() {
        return getCpf() != null ? getCpf().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", dataNascimento=").append(dataNascimento);
        sb.append(", cep='").append(cep).append('\'');
        sb.append(", endereco='").append(endereco).append('\'');
        sb.append(", contatos=").append(contatos);
        sb.append('}');
        return sb.toString();
    }
}
