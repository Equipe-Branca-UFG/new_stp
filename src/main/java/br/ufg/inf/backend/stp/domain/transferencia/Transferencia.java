package br.ufg.inf.backend.stp.domain.transferencia;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "transferencia")
@Table(name = "transferencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull
    private Paciente paciente;

    @NotNull
    @Column(name = "origem_id", nullable = false)
    private Long origemId;

    @NotNull
    @Column(name = "destino_id", nullable = false)
    private Long destinoId;

    @NotNull
    @Column(name = "meio_transporte", nullable = false)
    private Long meioTransporte;

    @NotNull(message = "Hora de saída não pode ser nula.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "horario_saida", nullable = false)
    private LocalDateTime horarioSaida;

    @NotNull(message = "Hora de chegada não pode ser nula.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "horario_chegada", nullable = false)
    private LocalDate horarioChegada;

    public Transferencia(TransferenciaDTO transferenciaDTO, Paciente paciente) {
        this.paciente = paciente;
        this.origemId = transferenciaDTO.getOrigemId();
        this.destinoId = transferenciaDTO.getDestinoId();
        this.meioTransporte = transferenciaDTO.getMeioTransporte();
        this.horarioSaida = transferenciaDTO.getHorarioSaida();
        this.horarioChegada = transferenciaDTO.getHorarioChegada();
    }

    public TransferenciaDTO toTransferenciaDTO() {
        return new TransferenciaDTO(
                this.id,
                this.paciente.getId(),
                this.origemId,
                this.destinoId,
                this.meioTransporte,
                this.horarioSaida,
                this.horarioChegada
        );
    }
}
