package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByCpf(String cpf);
}
