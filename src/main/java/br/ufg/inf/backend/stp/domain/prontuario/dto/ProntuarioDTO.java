package br.ufg.inf.backend.stp.domain.prontuario.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.ufg.inf.backend.stp.domain.droga.Droga;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class ProntuarioDTO {
    @NotNull
    private Long id;

    @NotNull(message = "Paciente não pode ser nulo.")
    private Long pacienteId;

    private LocalDateTime ultimoExame;

    private List<Droga> drogas;
}
