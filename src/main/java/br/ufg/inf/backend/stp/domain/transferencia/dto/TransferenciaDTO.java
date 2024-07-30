package br.ufg.inf.backend.stp.domain.transferencia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufg.inf.backend.stp.enums.Classificacao;
import br.ufg.inf.backend.stp.enums.MeioTransporte;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TransferenciaDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long origemId;

    @NotNull
    private Long destinoId;

    @NotNull
    private MeioTransporte meioTransporte;

    @NotNull
    private Classificacao classificacao;

    @NotNull (message = "Hora de saída não pode ser nula.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioSaida;

    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioChegada;
}
