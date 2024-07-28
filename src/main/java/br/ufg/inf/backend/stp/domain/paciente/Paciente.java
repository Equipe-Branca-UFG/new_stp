package br.ufg.inf.backend.stp.domain.paciente;

import br.ufg.inf.backend.stp.domain.paciente.dto.CreatePacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.domain.transferencia.Transferencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity(name = "paciente")
@Table(name = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "CPF não pode ser nulo.")
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotNull(message = "Prontuário não pode ser nulo.")
    @Column(name = "prontuario", nullable = false)
    private String prontuario;

    @NotNull(message = "Condição não pode ser nula.")
    @Column(name = "condicao", nullable = false)
    private String condicao;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transferencia> transferencias;

    public Paciente(CreatePacienteDTO createPacienteDTO) {
        this.nome = createPacienteDTO.getNome();
        this.cpf = createPacienteDTO.getCpf();
        this.prontuario = createPacienteDTO.getProntuario();
        this.condicao = createPacienteDTO.getCondicao();
    }

    public PacienteDTO toPacienteDTO() {
        return new PacienteDTO(
                this.id,
                this.nome,
                this.cpf,
                this.prontuario,
                this.condicao
        );
    }
}
