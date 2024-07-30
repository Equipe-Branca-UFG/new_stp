package br.ufg.inf.backend.stp.domain.droga;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufg.inf.backend.stp.domain.droga.dto.DrogaDTO;
import br.ufg.inf.backend.stp.domain.prontuario.Prontuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "droga")
@Table(name = "droga")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Droga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "Unidade de medida não pode ser nula.")
    @Column(name = "unidade_medida", nullable = false)
    private String unidadeMedida;

    @NotNull(message = "Dosagem não pode ser nula.")
    @Column(name = "dosagem", nullable = false)
    private Double dosagem;

    @ManyToMany(mappedBy = "drogas")
    @JsonIgnore
    private List<Prontuario> prontuarios;

    public Droga(DrogaDTO drogaDTO){
        this.nome = drogaDTO.getNome();
        this.unidadeMedida = drogaDTO.getUnidadeMedida();
        this.dosagem = drogaDTO.getDosagem();        
    }

    public DrogaDTO toDrogaDTO() {
        return new DrogaDTO(
                this.id,
                this.nome,
                this.unidadeMedida,
                this.dosagem
        );
    }
}
