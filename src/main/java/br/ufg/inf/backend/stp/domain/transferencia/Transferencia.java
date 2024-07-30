package br.ufg.inf.backend.stp.domain.transferencia;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTOResponse;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.enums.Classificacao;
import br.ufg.inf.backend.stp.enums.MeioTransporte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.Random;

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
    @JsonManagedReference
    private Paciente paciente;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "origem_id", nullable = false)
    private UnidadeHospitalar origem;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "destino_id", nullable = false)
    private UnidadeHospitalar destino;

    @NotNull
    @Column(name = "meio_transporte", nullable = false)
    private MeioTransporte meioTransporte;

    @NotNull
    @Column(name = "classificacao", nullable = false)
    private Classificacao classificacao;

    @NotNull(message = "Hora de saída não pode ser nula.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "horario_saida", nullable = false)
    private LocalDateTime horarioSaida;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "horario_chegada")
    private LocalDateTime horarioChegada;

    public Transferencia(TransferenciaDTO transferenciaDTO, Paciente paciente, UnidadeHospitalar origem, UnidadeHospitalar destino) {
        Random random = new Random();
        this.paciente = paciente;
        this.origem = origem;
        this.destino = destino;
        this.meioTransporte = transferenciaDTO.getMeioTransporte();
        this.classificacao = transferenciaDTO.getClassificacao();
        this.horarioSaida = transferenciaDTO.getHorarioSaida();
        if (transferenciaDTO.getMeioTransporte() == MeioTransporte.AMBULANCIA) {
            this.horarioChegada = transferenciaDTO.getHorarioSaida().plusMinutes(random.nextInt(50,120));
        }else if (transferenciaDTO.getMeioTransporte() == MeioTransporte.HELICOPTERO) {
            this.horarioChegada = transferenciaDTO.getHorarioSaida().plusMinutes(random.nextInt(10,50));
        }else {
            this.horarioChegada = transferenciaDTO.getHorarioSaida().plusMinutes(random.nextInt(45,180));
        }
    }

    public TransferenciaDTO toTransferenciaDTO() {
        return new TransferenciaDTO(
                this.id,
                this.paciente.getId(),
                this.origem.getId(),
                this.destino.getId(),
                this.meioTransporte,
                this.classificacao,
                this.horarioSaida,
                this.horarioChegada
        );
    }

    public TransferenciaDTOResponse toTransferenciaDTOResponse() {
        return new TransferenciaDTOResponse(
                this.id,
                this.paciente,
                this.origem,
                this.destino,
                this.meioTransporte,
                this.classificacao,
                this.horarioSaida,
                this.horarioChegada
        );
    }
}
