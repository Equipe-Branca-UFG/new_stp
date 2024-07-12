package br.ufg.inf.backend.stp.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CreatePacienteDTO {

    @NotNull
    private Long id;

    @NotNull
    private String nome;

    @NotNull (message = "CPF não pode ser nulo.")
    private String cpf;

    @NotNull (message = "Prontuário não pode ser nulo.")
    private String prontuario;

    @NotNull (message = "Condição não pode ser nula.")
    private String condicao;
}
