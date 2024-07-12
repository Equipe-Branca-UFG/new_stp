package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByAtivaTrue();

    List<Paciente> findByCpf(String cpf);
}
