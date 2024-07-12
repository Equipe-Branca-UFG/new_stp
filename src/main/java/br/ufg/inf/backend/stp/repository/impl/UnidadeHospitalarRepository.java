package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeHospitalarRepository extends JpaRepository<UnidadeHospitalar, Long> {
    // Define custom query methods if needed
}
