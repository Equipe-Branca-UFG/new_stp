package br.ufg.inf.backend.stp.domain.transferencia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.enums.Classificacao;
import br.ufg.inf.backend.stp.enums.MeioTransporte;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TransferenciaDTOResponse {

    @NotNull
    private Long id;

    @NotNull
    private Paciente paciente;

    @NotNull
    private UnidadeHospitalar origem;

    @NotNull
    private UnidadeHospitalar destino;

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
