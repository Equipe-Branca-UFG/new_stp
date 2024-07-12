package br.ufg.inf.backend.stp.domain.unidadeHospitalar;

import br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto.UnidadeHospitalarDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "unidade_hospitalar")
@Table(name = "unidade_hospitalar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class UnidadeHospitalar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "Endereço não pode ser nulo.")
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @NotNull(message = "Telefone não pode ser nulo.")
    @Column(name = "telefone", nullable = false)
    private String telefone;

    public UnidadeHospitalar(UnidadeHospitalarDTO unidadeHospitalarDTO) {
        this.nome = unidadeHospitalarDTO.getNome();
        this.endereco = unidadeHospitalarDTO.getEndereco();
        this.telefone = unidadeHospitalarDTO.getTelefone();
    }

    public UnidadeHospitalarDTO toUnidadeHospitalarDTO() {
        return new UnidadeHospitalarDTO(
                this.id,
                this.nome,
                this.endereco,
                this.telefone
        );
    }
}
