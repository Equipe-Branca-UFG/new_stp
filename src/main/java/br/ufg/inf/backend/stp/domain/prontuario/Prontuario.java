package br.ufg.inf.backend.stp.domain.prontuario;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.ufg.inf.backend.stp.domain.prontuario.dto.ProntuarioDTO;
import br.ufg.inf.backend.stp.domain.droga.Droga;
import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "prontuario")
@Table(name = "prontuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    @JsonBackReference
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "ultimo_exame")
    private LocalDateTime ultimoExame;

    @ManyToMany
    @JoinTable(
        name = "prontuario_droga",
        joinColumns = @JoinColumn(name = "prontuario_id"),
        inverseJoinColumns = @JoinColumn(name = "droga_id")
    )
    @JsonIgnore
    private List<Droga> drogas;

    public Prontuario(ProntuarioDTO prontuarioDTO, Paciente paciente){
        this.paciente = paciente;
        this.ultimoExame = prontuarioDTO.getUltimoExame();
        this.drogas = prontuarioDTO.getDrogas();
    }

    public ProntuarioDTO toProntuarioDTO() {
        return new ProntuarioDTO(
                this.id,
                this.paciente.getId(),
                this.ultimoExame,
                this.drogas
        );
    }
}
