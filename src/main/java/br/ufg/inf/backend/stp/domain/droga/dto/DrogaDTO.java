package br.ufg.inf.backend.stp.domain.droga.dto;

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
public class DrogaDTO {
    @NotNull
    private Long id;

    @NotNull
    private String nome;

    @NotNull(message = "Unidade de medida não pode ser nula.")
    private String unidadeMedida;

    @NotNull(message = "Dosagem não pode ser nula.")
    private Double dosagem;
}
