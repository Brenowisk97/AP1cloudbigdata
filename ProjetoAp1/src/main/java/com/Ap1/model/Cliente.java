package com.Ap1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Validações para o campo nome: não pode ser vazio e deve ter entre 3 e 100 caracteres
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;


    // Validações para o campo email: não pode ser vazio, deve ser válido e único no banco de dados
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(unique = true)
    private String email;

    // Validações para o campo CPF: não pode ser vazio, deve seguir o padrão de formato CPF, e ser único no banco
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve seguir o formato XXX.XXX.XXX-XX")
    @Column(unique = true)
    private String cpf;

    // Validação para o campo data de nascimento: deve ser uma data no passado
    @Past(message = "Data de nascimento deve ser válida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

     // Validação para o campo telefone: deve seguir o padrão (XX) XXXXX-XXXX 
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone deve seguir o padrão (XX) XXXXX-XXXX")
    @Column(unique = true)
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    // Valida se o cliente tem pelo menos 18 anos
    @AssertTrue(message = "O cliente deve ter pelo menos 18 anos")
    public boolean eeAdulto() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears() >= 18;
    }
    // Calcula a idade do cliente com base na data de nascimento
    public int getIdade() {
        return this.dataNascimento == null ? 0 : Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

}