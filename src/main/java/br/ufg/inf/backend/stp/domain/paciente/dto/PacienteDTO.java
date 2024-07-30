package br.ufg.inf.backend.stp.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import br.ufg.inf.backend.stp.domain.prontuario.Prontuario;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PacienteDTO {

    @NotNull
    private Long id;

    @NotNull
    private String nome;

    @NotNull (message = "CPF não pode ser nulo.")
    private String cpf;

    @NotNull (message = "Prontuário não pode ser nulo.")
    private Prontuario prontuario;

}
