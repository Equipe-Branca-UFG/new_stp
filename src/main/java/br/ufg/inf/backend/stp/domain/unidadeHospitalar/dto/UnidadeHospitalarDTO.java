package br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UnidadeHospitalarDTO {

    @NotNull
    private Long id;

    @NotNull
    private String nome;

    @NotNull (message = "Endereço não pode ser nulo.")
    private String endereco;

    @NotNull (message = "Telefone não pode ser nulo.")
    private String telefone;

    @NotNull (message = "Responsavel não pode ser nulo.")
    private String responsavel;

}
