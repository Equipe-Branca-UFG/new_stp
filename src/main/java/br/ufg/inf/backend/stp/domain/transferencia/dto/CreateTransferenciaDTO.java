package br.ufg.inf.backend.stp.domain.transferencia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CreateTransferenciaDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long origemId;

    @NotNull
    private Long destinoId;

    @NotNull
    private Long meioTransporte;

    @NotNull (message = "Hora de saída não pode ser nula.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioSaida;

    @NotNull (message = "Hora de chegada não pode ser nula.")
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate horarioChegada;
}
