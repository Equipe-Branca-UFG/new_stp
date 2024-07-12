package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.transferencia.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    // Define custom query methods if needed
}
